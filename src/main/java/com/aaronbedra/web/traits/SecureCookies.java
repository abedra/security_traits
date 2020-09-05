package com.aaronbedra.web.traits;

import com.aaronbedra.web.WebRequester;
import com.jnape.palatable.lambda.functions.specialized.SideEffect;
import com.jnape.palatable.lambda.io.IO;
import com.jnape.palatable.traitor.traits.Trait;
import okhttp3.Cookie;

import java.util.List;

import static com.jnape.palatable.lambda.functions.specialized.SideEffect.sideEffect;
import static org.junit.Assert.assertTrue;

public class SecureCookies implements Trait<WebRequester<IO<?>, Cookie>> {
    @Override
    public void test(WebRequester<IO<?>, Cookie> requester) {
        requester.requestHttps()
                .fmap(response -> assertSecure(requester.getCookies(response.request().url())))
                .<IO<SideEffect>>coerce()
                .unsafePerformIO();
    }

    private SideEffect assertSecure(List<Cookie> cookies) {
        return sideEffect(() -> cookies.forEach(cookie -> {
            assertTrue(cookie.name() + " not marked secure", cookie.secure());
            assertTrue(cookie.name() + " not marked HttpOnly", cookie.httpOnly());
        }));
    }
}
