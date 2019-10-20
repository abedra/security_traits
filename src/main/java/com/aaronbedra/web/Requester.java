package com.aaronbedra.web;

import com.aaronbedra.web.headers.Header;
import com.jnape.palatable.lambda.io.IO;
import lombok.Value;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import static com.jnape.palatable.lambda.io.IO.io;

@Value
public class Requester {
    String hostname;
    OkHttpClient okHttpClient;

    private Requester(String hostname) {
        this.hostname = hostname;
        this.okHttpClient = new OkHttpClient();
    }

    public static Requester requester(String hostname) {
        return new Requester(hostname);
    }

    public IO<Headers> getHeadersIO() {
        return buildRequestIO().flatMap(request -> io(() -> {
            var response = okHttpClient.newCall(request).execute();
            return response.headers();
        }));
    }

    public <T extends Header> IO<T> getHeaderIO(Headers headers, Class<T> headerClass) {
        return io(() -> {
            String name = (String) headerClass.getMethod("getName").invoke(null);
            return headerClass
                    .getConstructor(new Class[]{String.class})
                    .newInstance(headers.get(name));
        });
    }

    private IO<Request> buildRequestIO() {
        return io(() -> new Request.Builder().url(hostname).build());
    }
}
