package com.aaronbedra.network.traits;

import com.aaronbedra.network.Requester;
import com.jnape.palatable.lambda.adt.hlist.Tuple2;
import com.jnape.palatable.lambda.adt.hlist.Tuple3;
import com.jnape.palatable.lambda.io.IO;
import com.jnape.palatable.traitor.traits.Trait;

import java.util.List;

import static com.jnape.palatable.lambda.functions.builtin.fn2.Map.map;
import static com.jnape.palatable.lambda.io.IO.io;
import static org.junit.Assert.assertTrue;

public class HasOpenPorts implements Trait<Tuple2<IO<Requester>, List<Integer>>> {
    @Override
    public void test(Tuple2<IO<Requester>, List<Integer>> testSubject) {
        Iterable<IO<Tuple3<String, Integer, Boolean>>> results = testSubject._1().flatMap(requester ->
                io(map(requester::isAvailable, testSubject._2())))
                .unsafePerformIO();

        results.forEach(responseIO ->
                responseIO.flatMap(response ->
                        io(() -> assertPortAvailable(response))).unsafePerformIO()
        );
    }

    private void assertPortAvailable(Tuple3<String, Integer, Boolean> response) {
        assertTrue(failureMessage(response._1(), response._2()), response._3());
    }

    private String failureMessage(String host, int port) {
        return host + ":" + port + " is not available";
    }
}
