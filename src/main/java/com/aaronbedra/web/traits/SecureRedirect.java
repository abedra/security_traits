package com.aaronbedra.web.traits;

import com.aaronbedra.web.Requester;
import com.jnape.palatable.lambda.adt.Unit;
import com.jnape.palatable.lambda.functions.specialized.SideEffect;
import com.jnape.palatable.lambda.io.IO;
import com.jnape.palatable.traitor.traits.Trait;
import okhttp3.Response;
import org.junit.Assert;

import static com.jnape.palatable.lambda.adt.Maybe.maybe;
import static com.jnape.palatable.lambda.io.IO.io;
import static org.junit.Assert.assertEquals;

public class SecureRedirect implements Trait<IO<Requester>> {
    @Override
    public void test(IO<Requester> requester) {
        requester.flatMap(instance -> instance.getResponse(instance.getHttpUrl())
                .flatMap(response -> assertSecureRedirect(instance, response)))
                .unsafePerformIO();
    }

    private String calculateExpected(Requester requester, Response response) {
        return requester.getHttpsUrl() + response.request().url().encodedPath();
    }

    private IO<Unit> assertSecureRedirect(Requester requester, Response response) {
        assertEquals(301, response.code());
        return maybe(response.headers().get("Location")).match(
                __ -> io((SideEffect) Assert::fail),
                location -> io(() -> assertEquals(calculateExpected(requester, response), location))
        );
    }
}
