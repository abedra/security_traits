package com.aaronbedra.tcp.types;

import com.jnape.palatable.lambda.adt.Either;
import com.jnape.palatable.lambda.adt.Unit;
import com.jnape.palatable.lambda.io.IO;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.net.InetSocketAddress;
import java.net.Socket;

import static com.jnape.palatable.lambda.adt.Either.left;
import static com.jnape.palatable.lambda.adt.Unit.UNIT;
import static com.jnape.palatable.lambda.functions.Fn0.fn0;
import static com.jnape.palatable.lambda.io.IO.io;
import static lombok.AccessLevel.PRIVATE;

@Value
@AllArgsConstructor(access = PRIVATE)
public class TcpSocket implements Connectable<IO<?>> {
    String host;

    public static TcpSocket tcpSocket(String host) {
        return new TcpSocket(host);
    }

    @Override
    public IO<Either<Throwable, Unit>> connect(int port, int timeout) {
        return io(fn0(Socket::new))
                .flatMap(socket -> io(() -> socket.connect(new InetSocketAddress(host, port), timeout)))
                .discardL(io(Either.<Throwable, Unit>right(UNIT)))
                .catchError(throwable -> io(left(throwable)));
    }
}
