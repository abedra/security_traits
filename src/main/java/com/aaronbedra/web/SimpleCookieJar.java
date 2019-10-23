package com.aaronbedra.web;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

import java.util.ArrayList;
import java.util.List;

public class SimpleCookieJar implements CookieJar {
    private List<Cookie> cookies;

    @Override
    public List<Cookie> loadForRequest(HttpUrl httpUrl) {
        return cookies != null ? cookies : new ArrayList<>();
    }

    @Override
    public void saveFromResponse(HttpUrl httpUrl, List<Cookie> cookies) {
        this.cookies = cookies;
    }
}
