package com.aaronbedra.tcp;

import com.aaronbedra.tcp.types.Connectable;
import com.jnape.palatable.lambda.adt.Either;
import com.jnape.palatable.lambda.adt.Unit;
import com.jnape.palatable.lambda.adt.hlist.Tuple2;
import com.jnape.palatable.lambda.adt.hlist.Tuple3;
import com.jnape.palatable.lambda.adt.hmap.HMap;
import com.jnape.palatable.lambda.adt.hmap.TypeSafeKey.Simple;
import com.jnape.palatable.lambda.functor.builtin.Writer;
import com.jnape.palatable.lambda.monoid.Monoid;
import com.jnape.palatable.shoki.impl.StrictQueue;
import org.junit.Test;

import static com.aaronbedra.tcp.TcpRequester.tcpRequester;
import static com.jnape.palatable.lambda.adt.Either.left;
import static com.jnape.palatable.lambda.adt.Either.right;
import static com.jnape.palatable.lambda.adt.Unit.UNIT;
import static com.jnape.palatable.lambda.adt.hlist.HList.tuple;
import static com.jnape.palatable.lambda.adt.hmap.HMap.singletonHMap;
import static com.jnape.palatable.lambda.adt.hmap.TypeSafeKey.typeSafeKey;
import static com.jnape.palatable.lambda.functions.builtin.fn1.Constantly.constantly;
import static com.jnape.palatable.lambda.functor.builtin.Writer.tell;
import static com.jnape.palatable.lambda.monoid.Monoid.monoid;
import static com.jnape.palatable.lambda.monoid.builtin.MergeHMaps.mergeHMaps;
import static com.jnape.palatable.shoki.impl.StrictQueue.strictQueue;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class TcpRequesterTest {
    private TcpRequester<Writer<HMap, ?>> tcpRequester;

    private static final Simple<StrictQueue<Either<Throwable, Unit>>> connectionInteractions = typeSafeKey();
    private static final Throwable throwable = new RuntimeException();

    @Test
    public void successfulConnection() {
        tcpRequester = tcpRequester(new SuccessfulTcpSocket(), 2000);
        Tuple2<Tuple3<String, Integer, Boolean>, HMap> actual = tcpRequester
                .request()
                .<Writer<HMap, Tuple3<String, Integer, Boolean>>>runReaderT(80)
                .runWriter(MERGE);

        assertThat(actual._1(), equalTo(tuple("localhost", 80, true)));
        assertThat(actual._2(), equalTo(singletonHMap(connectionInteractions, strictQueue(right(UNIT)))));
    }

    @Test
    public void failedConnection() {
        tcpRequester = tcpRequester(new FailedTcpSocket(), 2000);
        Tuple2<Tuple3<String, Integer, Boolean>, HMap> actual = tcpRequester
                .request()
                .<Writer<HMap, Tuple3<String, Integer, Boolean>>>runReaderT(80)
                .runWriter(MERGE);


        assertThat(actual._1(), equalTo(tuple("localhost", 80, false)));
        assertThat(actual._2(), equalTo(singletonHMap(connectionInteractions, strictQueue(left(throwable)))));
    }

    private static class SuccessfulTcpSocket implements Connectable<Writer<HMap, ?>> {
        @Override
        public String getHost() {
            return "localhost";
        }

        @Override
        public Writer<HMap, Either<Throwable, Unit>> connect(int port, int timeout) {
            return tell(singletonHMap(connectionInteractions, strictQueue(right(UNIT))))
                    .fmap(Either::right);
        }
    }

    private static class FailedTcpSocket implements Connectable<Writer<HMap, ?>> {
        @Override
        public String getHost() {
            return "localhost";
        }

        @Override
        public Writer<HMap, Either<Throwable, Unit>> connect(int port, int timeout) {
            return tell(singletonHMap(connectionInteractions, strictQueue(left(throwable))))
                    .fmap(constantly(left(throwable)));
        }
    }

    private static final Monoid<HMap> MERGE = mergeHMaps()
            .key(connectionInteractions, snocAll());

    public static <A> Monoid<StrictQueue<A>> snocAll() {
        return monoid(StrictQueue::snocAll, strictQueue());
    }
}
