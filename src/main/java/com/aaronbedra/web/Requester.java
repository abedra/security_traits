package com.aaronbedra.web;

import com.jnape.palatable.lambda.io.IO;
import lombok.Value;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.jnape.palatable.lambda.io.IO.io;

@Value
public class Requester {
    String hostname;
    OkHttpClient okHttpClient;

    private Requester(String hostname) {
        this.hostname = hostname;
        this.okHttpClient = new OkHttpClient()
                .newBuilder()
                .followRedirects(false)
                .followSslRedirects(false)
                .build();
    }

    public static Requester requester(String hostname) {
        return new Requester(hostname);
    }

    public IO<Response> getResponseIO() {
        return buildRequestIO().flatMap(request -> io(() -> okHttpClient.newCall(request).execute()));
    }

    public IO<Headers> getHeadersIO() {
        return getResponseIO().fmap(Response::headers);
    }

    private IO<Request> buildRequestIO() {
        return io(() -> new Request.Builder().url(hostname).build());
    }
}
