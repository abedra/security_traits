package com.aaronbedra.web;

import com.aaronbedra.web.headers.Header;
import com.aaronbedra.web.traits.SecureCookies;
import com.aaronbedra.web.traits.SecureHeaders;
import com.aaronbedra.web.traits.SecureRedirect;
import com.jnape.palatable.lambda.adt.hlist.Tuple2;
import com.jnape.palatable.lambda.io.IO;
import com.jnape.palatable.shoki.api.Sequence;
import com.jnape.palatable.traitor.annotations.TestTraits;
import com.jnape.palatable.traitor.runners.Traits;
import okhttp3.Cookie;
import org.junit.runner.RunWith;

import static com.aaronbedra.web.WebRequester.webRequester;
import static com.aaronbedra.web.headers.DefaultSecureHeaders.defaultSecureHeaders;
import static com.aaronbedra.web.request.Hostname.hostname;
import static com.aaronbedra.web.request.OkHttpRestClient.okHttpRestClient;
import static com.jnape.palatable.lambda.adt.hlist.HList.tuple;

@RunWith(Traits.class)
public class GetRepsheetTest {
    @TestTraits(SecureHeaders.class)
    public Tuple2<WebRequester<IO<?>, Cookie>, Sequence<Header>> secureHeaders() {
        return tuple(webRequester(hostname("getrepsheet.com"), okHttpRestClient()), defaultSecureHeaders());
    }

    @TestTraits(SecureRedirect.class)
    public WebRequester<IO<?>, Cookie> secureRedirect() {
        return webRequester(hostname("getrepsheet.com"), okHttpRestClient());
    }

    @TestTraits(SecureCookies.class)
    public WebRequester<IO<?>, Cookie> secureCookies() {
        return webRequester(hostname("getrepsheet.com"), okHttpRestClient());
    }
}
