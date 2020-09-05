package com.aaronbedra.web.traits;

import com.aaronbedra.web.WebRequester;
import com.jnape.palatable.lambda.functions.specialized.SideEffect;
import com.jnape.palatable.lambda.io.IO;
import com.jnape.palatable.traitor.traits.Trait;
import okhttp3.Cookie;
import okhttp3.HttpUrl;
import okhttp3.Response;
import org.junit.Assert;

import static com.jnape.palatable.lambda.adt.Maybe.maybe;
import static com.jnape.palatable.lambda.functions.specialized.SideEffect.sideEffect;
import static org.junit.Assert.assertEquals;

public class SecureRedirect implements Trait<WebRequester<IO<?>, Cookie>> {
    @Override
    public void test(WebRequester<IO<?>, Cookie> requester) {
        requester.requestHttp()
                .fmap(response -> assertSecureRedirect(requester.getHttpsUrl(), response))
                .<IO<SideEffect>>coerce()
                .unsafePerformIO();
    }

    private SideEffect assertSecureRedirect(HttpUrl url, Response response) {
        assertEquals(301, response.code());
        return maybe(response.headers().get("Location"))
                .fmap(location -> sideEffect(() -> assertEquals(url.toString(), location)))
                .orElse(Assert::fail);
    }
}
