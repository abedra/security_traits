package com.aaronbedra.web.request;

import com.jnape.palatable.lambda.io.IO;
import com.jnape.palatable.lambda.monad.transformer.builtin.ReaderT;
import okhttp3.*;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

import static com.aaronbedra.web.request.SimpleCookieJar.simpleCookieJar;
import static com.jnape.palatable.lambda.monad.transformer.builtin.ReaderT.readerT;

public class OkHttpRestClient extends RestClient<IO<?>, Request, Response, Cookie> {
    private OkHttpRestClient(OkHttpClient okHttpClient, ReaderT<Request, IO<?>, Response> requestReader) {
        super(requestReader, url -> okHttpClient.cookieJar().loadForRequest(url));
    }

    public static OkHttpRestClient okHttpRestClient() {
        OkHttpClient okHttpClient = new OkHttpClient
                .Builder()
                .followRedirects(false)
                .followSslRedirects(false)
                .cookieJar(simpleCookieJar())
                .build();

        return new OkHttpRestClient(okHttpClient, reader(okHttpClient));
    }

    private static ReaderT<Request, IO<?>, Response> reader(OkHttpClient okHttpClient) {
        CompletableFuture<Response> responseFuture = new CompletableFuture<>();

        return readerT(request -> IO.externallyManaged(() -> {
            okHttpClient.newCall(request).enqueue(responseCallback(responseFuture));
            return responseFuture;
        }));
    }

    private static Callback responseCallback(CompletableFuture<Response> responseFuture) {
        return new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                responseFuture.completeExceptionally(e);
            }

            @Override
            public void onResponse(Call call, Response response) {
                responseFuture.complete(response);
            }
        };
    }
}
