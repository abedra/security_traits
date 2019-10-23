package com.aaronbedra.web;

import lombok.Value;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.jnape.palatable.lambda.adt.Maybe.maybe;

@Value
public class SimpleCookieJar implements CookieJar {
    Map<HttpUrl, List<Cookie>> cookieMap;

    private SimpleCookieJar(Map<HttpUrl, List<Cookie>> cookieMap) {
        this.cookieMap = cookieMap;
    }

    public static SimpleCookieJar simpleCookieJar() {
        return new SimpleCookieJar(new HashMap<>());
    }

    @NotNull
    @Override
    public List<Cookie> loadForRequest(@NotNull HttpUrl httpUrl) {
        return maybe(httpUrl).flatMap(url -> maybe(cookieMap.get(url))).orElse(new ArrayList<>());
    }

    @Override
    public void saveFromResponse(@NotNull HttpUrl httpUrl, @NotNull List<Cookie> cookies) {
        this.cookieMap.put(httpUrl, cookies);
    }
}
