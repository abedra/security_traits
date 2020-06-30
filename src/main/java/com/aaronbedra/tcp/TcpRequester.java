package com.aaronbedra.tcp;

import com.aaronbedra.Requester;
import com.aaronbedra.tcp.types.Connectable;
import com.jnape.palatable.lambda.adt.hlist.Tuple3;
import com.jnape.palatable.lambda.monad.MonadRec;
import com.jnape.palatable.lambda.monad.transformer.builtin.ReaderT;
import lombok.Value;

import static com.jnape.palatable.lambda.adt.hlist.HList.tuple;
import static com.jnape.palatable.lambda.functions.builtin.fn1.Constantly.constantly;
import static com.jnape.palatable.lambda.monad.transformer.builtin.ReaderT.readerT;

@Value
public class TcpRequester<M extends MonadRec<?, M>, A extends Connectable<M>> implements
        Requester<ReaderT<Integer, M, ?>> {

    A connectable;
    int timeout;

    public TcpRequester(A connectable, int timeout) {
        this.connectable = connectable;
        this.timeout = timeout;
    }

    @Override
    public ReaderT<Integer, M, Tuple3<String, Integer, Boolean>> request() {
        return readerT(port -> connectable.connect(port, timeout)
                .fmap(either -> either
                        .fmap(constantly(tuple(connectable.getHost(), port, true)))
                        .recover(constantly(tuple(connectable.getHost(), port, false)))));
    }
}
