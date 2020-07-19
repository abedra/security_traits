package com.aaronbedra.web.traits;

import com.aaronbedra.web.WebRequester;
import com.jnape.palatable.lambda.adt.Unit;
import com.jnape.palatable.lambda.functions.specialized.SideEffect;
import com.jnape.palatable.lambda.io.IO;
import com.jnape.palatable.traitor.traits.Trait;
import okhttp3.Cookie;
import okhttp3.Response;
import org.junit.Assert;

import static com.jnape.palatable.lambda.adt.Maybe.maybe;
import static com.jnape.palatable.lambda.io.IO.io;
import static org.junit.Assert.assertEquals;

public class SecureRedirect implements Trait<WebRequester<IO<?>, Cookie>> {
    @Override
    public void test(WebRequester<IO<?>, Cookie> requester) {
        requester.requestHttp()
                .flatMap(response -> assertSecureRedirect(requester, response))
                .<IO<Unit>>coerce()
                .unsafePerformIO();
    }

    private IO<Unit> assertSecureRedirect(WebRequester<IO<?>, Cookie> webRequester, Response response) {
        assertEquals(301, response.code());
        return maybe(response.headers().get("Location")).match(
                __ -> io((SideEffect) Assert::fail),
                location -> io(() -> assertEquals(webRequester.getHttpsUrl().toString(), location))
        );
    }
}
