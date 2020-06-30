package com.aaronbedra;

import com.jnape.palatable.lambda.monad.MonadRec;

public interface Requester<M extends MonadRec<?, M>> {
     MonadRec<?, M> request();
}
