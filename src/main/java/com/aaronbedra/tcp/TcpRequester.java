package com.aaronbedra.tcp;

import com.aaronbedra.Requester;
import com.aaronbedra.tcp.types.Connectable;
import com.jnape.palatable.lambda.adt.hlist.Tuple3;
import com.jnape.palatable.lambda.monad.MonadRec;
import com.jnape.palatable.lambda.monad.transformer.builtin.ReaderT;
import lombok.AllArgsConstructor;
import lombok.Value;

import static com.jnape.palatable.lambda.adt.hlist.HList.tuple;
import static com.jnape.palatable.lambda.functions.builtin.fn1.Constantly.constantly;
import static com.jnape.palatable.lambda.monad.transformer.builtin.ReaderT.readerT;
import static lombok.AccessLevel.PRIVATE;

@Value
@AllArgsConstructor(access = PRIVATE)
public class TcpRequester<M extends MonadRec<?, M>> implements Requester<ReaderT<Integer, M, ?>> {
    Connectable<M> connectable;
    int timeout;

    public static <M extends MonadRec<?, M>> TcpRequester<M> tcpRequester(Connectable<M> connectable, int timeout) {
        return new TcpRequester<>(connectable, timeout);
    }

    @Override
    public ReaderT<Integer, M, Tuple3<String, Integer, Boolean>> request() {
        return readerT(port -> connectable.connect(port, timeout)
                .fmap(either -> either
                        .fmap(constantly(true))
                        .recover(constantly(false)))
                .fmap(success -> tuple(connectable.getHost(), port, success)));
    }
}
