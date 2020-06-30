package com.aaronbedra.tcp;

import com.aaronbedra.tcp.traits.HasClosedPorts;
import com.aaronbedra.tcp.traits.HasOpenPorts;
import com.aaronbedra.tcp.types.TcpSocket;
import com.jnape.palatable.lambda.adt.hlist.Tuple2;
import com.jnape.palatable.lambda.io.IO;
import com.jnape.palatable.traitor.annotations.TestTraits;
import com.jnape.palatable.traitor.runners.Traits;
import org.junit.runner.RunWith;

import java.util.List;

import static com.aaronbedra.tcp.types.TcpSocket.tcpSocket;
import static com.jnape.palatable.lambda.adt.hlist.HList.tuple;
import static java.util.Arrays.asList;

@RunWith(Traits.class)
public class GetRepsheetTest {
    private static final TcpSocket tcpSocket = tcpSocket("getrepsheet.com");
    private static final int timeout = 2000;

    @TestTraits(HasOpenPorts.class)
    public Tuple2<TcpRequester<IO<?>, TcpSocket>, List<Integer>> foo() {
        return tuple(new TcpRequester<>(tcpSocket, timeout), asList(80, 443));
    }

    @TestTraits(HasClosedPorts.class)
    public Tuple2<TcpRequester<IO<?>, TcpSocket>, List<Integer>> closedPorts() {
        return tuple(new TcpRequester<>(tcpSocket, timeout), asList(8080, 8888));
    }
}
