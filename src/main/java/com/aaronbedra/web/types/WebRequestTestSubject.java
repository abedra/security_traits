package com.aaronbedra.web.types;

import com.aaronbedra.web.request.WebRequester;
import com.jnape.palatable.lambda.io.IO;
import com.jnape.palatable.lambda.monad.MonadRec;
import com.jnape.palatable.shoki.api.Sequence;
import lombok.AllArgsConstructor;
import lombok.Value;
import okhttp3.Cookie;


import static com.aaronbedra.web.headers.DefaultSecureHeaders.defaultSecureHeaders;
import static com.aaronbedra.web.request.OkHttpRestClient.okHttpRestClient;
import static com.aaronbedra.web.request.WebRequester.webRequester;
import static lombok.AccessLevel.PRIVATE;

@Value
@AllArgsConstructor(access = PRIVATE)
public class WebRequestTestSubject<M extends MonadRec<?, M>, CookieType> {
    WebRequester<M, CookieType> requester;
    Sequence<Header> verifiedHeaders;

    public static <M extends MonadRec<?, M>, CookieType> WebRequestTestSubject<M, CookieType> webRequestTestSubject(
            WebRequester<M, CookieType> requester,
            Sequence<Header> verifiedHeaders) {

        return new WebRequestTestSubject<>(requester, verifiedHeaders);
    }

    public static WebRequestTestSubject<IO<?>, Cookie> okHttpWebRequestTestSubject(Hostname hostname, Sequence<Header> verifiedHeaders) {
        return webRequestTestSubject(webRequester(hostname, okHttpRestClient()), verifiedHeaders);
    }

    public static WebRequestTestSubject<IO<?>, Cookie> okHttpWebRequestTestSubject(Hostname hostname) {
        return okHttpWebRequestTestSubject(hostname, defaultSecureHeaders());
    }
}
