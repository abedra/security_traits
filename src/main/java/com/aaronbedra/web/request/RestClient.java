package com.aaronbedra.web.request;

import com.jnape.palatable.lambda.functions.Fn1;
import com.jnape.palatable.lambda.monad.MonadRec;
import com.jnape.palatable.lambda.monad.transformer.builtin.ReaderT;
import okhttp3.HttpUrl;

import java.util.List;

public class RestClient<M extends MonadRec<?, M>, RequestType, ResponseType, CookieType> {
    private final ReaderT<RequestType, M, ResponseType> requestReader;
    private final Fn1<HttpUrl, List<CookieType>> cookieFn;

    public RestClient(ReaderT<RequestType, M, ResponseType> requestReader, Fn1<HttpUrl, List<CookieType>> cookieFn) {
        this.requestReader = requestReader;
        this.cookieFn = cookieFn;
    }

    public MonadRec<ResponseType, M> request(RequestType request) {
        return requestReader.runReaderT(request);
    }

    public List<CookieType> getCookies(HttpUrl url) {
        return cookieFn.apply(url);
    }
}
