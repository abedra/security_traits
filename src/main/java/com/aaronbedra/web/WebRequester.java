package com.aaronbedra.web;

import com.aaronbedra.Requester;
import com.jnape.palatable.lambda.io.IO;
import com.jnape.palatable.lambda.monad.transformer.builtin.ReaderT;
import lombok.Value;
import okhttp3.*;

import static com.aaronbedra.web.SimpleCookieJar.simpleCookieJar;
import static com.jnape.palatable.lambda.io.IO.io;
import static com.jnape.palatable.lambda.monad.transformer.builtin.ReaderT.readerT;

@Value
public class WebRequester implements Requester<ReaderT<String, IO<?>, ?>> {
    String hostname;
    IO<OkHttpClient> okHttpClient;

    public WebRequester(String hostname) {
        this.hostname = hostname;
        this.okHttpClient = IO.memoize(io(() -> new OkHttpClient()
                .newBuilder()
                .followRedirects(false)
                .followSslRedirects(false)
                .cookieJar(simpleCookieJar())
                .build()));
    }

    @Override
    public ReaderT<String, IO<?>, Response> request() {
        return readerT(url -> io(() -> new Request.Builder().url(url).build())
                .flatMap(request -> okHttpClient.flatMap(client -> io(() -> client.newCall(request)
                        .execute()))));
    }

    public IO<CookieJar> getCookieJar() {
        return okHttpClient.flatMap(client -> io(client::cookieJar));
    }

    public String getHttpUrl() {
        return "http://" + hostname;
    }

    public String getHttpsUrl() {
        return "https://" + hostname;
    }
}
