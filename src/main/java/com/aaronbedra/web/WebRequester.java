package com.aaronbedra.web;

import com.aaronbedra.web.request.Hostname;
import com.aaronbedra.web.request.RestClient;
import com.jnape.palatable.lambda.monad.MonadRec;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;

import java.util.List;

public final class WebRequester<M extends MonadRec<?, M>, CookieType> {
    private final Hostname hostname;
    private final RestClient<M, Request, Response, CookieType> restClient;

    private WebRequester(
            Hostname hostname,
            RestClient<M, Request, Response, CookieType> restClient) {

        this.hostname = hostname;
        this.restClient = restClient;
    }

    public static <M extends MonadRec<?, M>, CookieType> WebRequester<M, CookieType> webRequester2(
            Hostname hostname,
            RestClient<M, Request, Response, CookieType> restClient) {

        return new WebRequester<>(hostname, restClient);
    }

    public MonadRec<Response, M> requestHttp() {
        return restClient.request(buildRequest(getHttpUrl()));
    }

    public MonadRec<Response, M> requestHttps() {
        return restClient.request(buildRequest(getHttpsUrl()));
    }

    public List<CookieType> getCookies(HttpUrl url) {
        return restClient.getCookies(url);
    }

    public HttpUrl getHttpUrl() {
        return new HttpUrl
                .Builder()
                .scheme("http")
                .host(hostname.getValue())
                .build();
    }

    public HttpUrl getHttpsUrl() {
        return new HttpUrl
                .Builder()
                .scheme("https")
                .host(hostname.getValue())
                .build();
    }

    private Request buildRequest(HttpUrl url) {
        return new Request.Builder().url(url).build();
    }
}
