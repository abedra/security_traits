package com.aaronbedra.tcp.traits;

import com.aaronbedra.tcp.TcpRequester;
import com.aaronbedra.tcp.types.TcpSocket;
import com.jnape.palatable.lambda.adt.hlist.Tuple2;
import com.jnape.palatable.lambda.adt.hlist.Tuple3;
import com.jnape.palatable.lambda.io.IO;
import com.jnape.palatable.traitor.traits.Trait;

import java.util.List;

import static com.jnape.palatable.lambda.functions.builtin.fn2.Into.into;
import static com.jnape.palatable.lambda.functions.builtin.fn2.Map.map;
import static com.jnape.palatable.lambda.io.IO.io;
import static org.junit.Assert.assertFalse;

public class HasClosedPorts implements Trait<Tuple2<TcpRequester<IO<?>, TcpSocket>, List<Integer>>> {
    @Override
    public void test(Tuple2<TcpRequester<IO<?>, TcpSocket>, List<Integer>> testSubject) {
        testSubject
                .pure(testSubject)
                .fmap(into((requester, ports) -> map(port -> requester
                        .request()
                        .<IO<Tuple3<String, Integer, Boolean>>>runReaderT(port), ports)))
                ._2()
                .forEach(responseIO -> responseIO
                        .flatMap(response -> io(() -> assertPortClosed(response)))
                        .unsafePerformIO());
    }

    private void assertPortClosed(Tuple3<String, Integer, Boolean> response) {
        assertFalse(failureMessage(response._1(), response._2()), response._3());
    }

    private String failureMessage(String host, int port) {
        return host + ":" + port + " is available";
    }
}
