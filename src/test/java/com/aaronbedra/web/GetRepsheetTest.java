package com.aaronbedra.web;

import com.aaronbedra.web.traits.SecureCookies;
import com.aaronbedra.web.traits.SecureHeaders;
import com.aaronbedra.web.traits.SecureRedirect;
import com.jnape.palatable.lambda.io.IO;
import com.jnape.palatable.traitor.annotations.TestTraits;
import com.jnape.palatable.traitor.runners.Traits;
import okhttp3.Cookie;
import org.junit.runner.RunWith;

import static com.aaronbedra.web.request.Hostname.hostname;
import static com.aaronbedra.web.request.OkHttpRestClient.okHttpRestClient;
import static com.aaronbedra.web.WebRequester.webRequester2;

@RunWith(Traits.class)
public class GetRepsheetTest {
    @TestTraits(SecureHeaders.class)
    public WebRequester<IO<?>, Cookie> secureHeaders() {
        return webRequester2(hostname("getrepsheet.com"), okHttpRestClient());
    }

    @TestTraits(SecureRedirect.class)
    public WebRequester<IO<?>, Cookie> secureRedirect() {
        return webRequester2(hostname("getrepsheet.com"), okHttpRestClient());
    }

    @TestTraits(SecureCookies.class)
    public WebRequester<IO<?>, Cookie> secureCookies() {
        return webRequester2(hostname("getrepsheet.com"), okHttpRestClient());
    }
}
