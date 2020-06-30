package com.aaronbedra.web;

import com.jnape.palatable.lambda.io.IO;
import lombok.Value;
import okhttp3.*;

import static com.aaronbedra.web.SimpleCookieJar.simpleCookieJar;
import static com.jnape.palatable.lambda.io.IO.io;

@Value
public class WebRequester {
    String hostname;
    OkHttpClient okHttpClient;

    private WebRequester(String hostname, OkHttpClient okHttpClient) {
        this.hostname = hostname;
        this.okHttpClient = okHttpClient;
    }

    public static IO<WebRequester> requester(String hostname){
        return io(() -> new WebRequester(
                hostname,
                new OkHttpClient()
                        .newBuilder()
                        .followRedirects(false)
                        .followSslRedirects(false)
                        .cookieJar(simpleCookieJar())
                        .build()));
    }

    public IO<Response> getResponse(String url) {
        return buildRequest(url).flatMap(request -> io(() -> okHttpClient.newCall(request).execute()));
    }

    public IO<Headers> getHeaders(String url) {
        return getResponse(url).fmap(Response::headers);
    }

    public IO<CookieJar> getCookieJar() {
        return io(okHttpClient::cookieJar);
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
