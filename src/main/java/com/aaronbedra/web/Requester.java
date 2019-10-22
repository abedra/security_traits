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

    private Requester(String hostname, OkHttpClient okHttpClient) {
        this.hostname = hostname;
        this.okHttpClient = okHttpClient;
    }

    public static IO<Requester> requester(String hostname){
        return io(() -> new Requester(
                hostname,
                new OkHttpClient()
                        .newBuilder()
                        .followRedirects(false)
                        .followSslRedirects(false)
                        .build())
        );
    }

    public IO<Response> getResponse(String url) {
        return buildRequest(url).flatMap(request -> io(() -> okHttpClient.newCall(request).execute()));
    }

    public IO<Headers> getHeaders(String url) {
        return getResponse(url).fmap(Response::headers);
    }

    private IO<Request> buildRequest(String url) {
        return io(() -> new Request.Builder().url(url).build());
    }

    public String getHttpUrl() {
        return "http://" + hostname;
    }

    public String getHttpsUrl() {
        return "https://" + hostname;
    }
}
