package com.aaronbedra.tcp.types;

import com.jnape.palatable.lambda.adt.Either;
import com.jnape.palatable.lambda.adt.Unit;
import com.jnape.palatable.lambda.monad.MonadRec;

public interface Connectable<M extends MonadRec<?, M>> {
    String getHost();

    MonadRec<Either<Throwable, Unit>, M> connect(int port, int timeout);
}
