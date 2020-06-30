package com.aaronbedra.web.traits;

import com.aaronbedra.web.WebRequester;
import com.jnape.palatable.lambda.adt.Unit;
import com.jnape.palatable.lambda.functions.specialized.SideEffect;
import com.jnape.palatable.lambda.io.IO;
import com.jnape.palatable.traitor.traits.Trait;
import okhttp3.Response;
import org.junit.Assert;

import static com.jnape.palatable.lambda.adt.Maybe.maybe;
import static com.jnape.palatable.lambda.io.IO.io;
import static org.junit.Assert.assertEquals;

public class SecureRedirect implements Trait<WebRequester> {
    @Override
    public void test(WebRequester requester) {
        requester.request().<IO<Response>>runReaderT(requester.getHttpUrl())
                .flatMap(response -> assertSecureRedirect(requester, response))
                .unsafePerformIO();
    }

    private String calculateExpected(WebRequester webRequester, Response response) {
        return webRequester.getHttpsUrl() + response.request().url().encodedPath();
    }

    private IO<Unit> assertSecureRedirect(WebRequester webRequester, Response response) {
        assertEquals(301, response.code());
        return maybe(response.headers().get("Location")).match(
                __ -> io((SideEffect) Assert::fail),
                location -> io(() -> assertEquals(calculateExpected(webRequester, response), location))
        );
    }
}
