package com.aaronbedra.network;

import com.jnape.palatable.lambda.adt.hlist.Tuple3;
import com.jnape.palatable.lambda.functions.Fn0;
import com.jnape.palatable.lambda.io.IO;
import lombok.Value;

import java.net.InetSocketAddress;
import java.net.Socket;

import static com.jnape.palatable.lambda.adt.hlist.HList.tuple;
import static com.jnape.palatable.lambda.functions.builtin.fn2.AutoBracket.autoBracket;
import static com.jnape.palatable.lambda.io.IO.io;

@Value
public class Requester {
    String host;

    private Requester(String host) {
        this.host = host;
    }

    public static IO<Requester> requester(String host) {
        return io(() -> new Requester(host));
    }

    public IO<Tuple3<String, Integer, Boolean>> isAvailable(int port) {
        return autoBracket(
                io((Fn0<Socket>) Socket::new),
                socket -> {
                    socket.connect(new InetSocketAddress(host, port), 2000);
                    return io(tuple(getHost(), port, true));
                }
        ).catchError(__ -> io(tuple(getHost(), port, false)));
    }
}
