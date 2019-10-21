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

    public static IO<Requester> requesterIO(String hostname){
        return io(() -> new Requester(hostname));
    }

    public IO<Response> getResponseIO(String url) {
        return buildRequestIO(url).flatMap(request -> io(() -> okHttpClient.newCall(request).execute()));
    }

    public IO<Headers> getHeadersIO(String url) {
        return getResponseIO(url).fmap(Response::headers);
    }

    private IO<Request> buildRequestIO(String url) {
        return io(() -> new Request.Builder().url(url).build());
    }

    public String getHttpUrl() {
        return "http://" + hostname;
    }

    public String getHttpsUrl() {
        return "https://" + hostname;
    }
}
