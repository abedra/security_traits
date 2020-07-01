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
import static org.junit.Assert.assertTrue;

public class OpenPorts implements Trait<Tuple2<TcpRequester<IO<?>>, List<Integer>>> {
    @Override
    public void test(Tuple2<TcpRequester<IO<?>>, List<Integer>> testSubject) {
        testSubject
                .pure(testSubject)
                .fmap(into((requester, ports) -> map(port -> requester
                        .request()
                        .<IO<Tuple3<String, Integer, Boolean>>>runReaderT(port), ports)))
                ._2()
                .forEach(responseIO -> responseIO
                        .flatMap(response -> io(() -> assertPortAvailable(response)))
                        .unsafePerformIO());
    }

    private void assertPortAvailable(Tuple3<String, Integer, Boolean> response) {
        assertTrue(failureMessage(response._1(), response._2()), response._3());
    }

    private String failureMessage(String host, int port) {
        return host + ":" + port + " is not available";
    }
}
