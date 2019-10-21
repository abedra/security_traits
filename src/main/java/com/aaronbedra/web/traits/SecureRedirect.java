package com.aaronbedra.web.traits;

import com.jnape.palatable.lambda.functions.specialized.SideEffect;
import com.jnape.palatable.lambda.io.IO;
import com.jnape.palatable.traitor.traits.Trait;
import okhttp3.Response;
import org.junit.Assert;

import static com.jnape.palatable.lambda.adt.Maybe.maybe;
import static com.jnape.palatable.lambda.io.IO.io;
import static org.junit.Assert.assertEquals;

public class SecureRedirect implements Trait<IO<Response>> {
    @Override
    public void test(IO<Response> responseIO) {
        responseIO.flatMap(response -> {
            assertEquals(301, response.code());

            var expected = getHttpsUrl(response);
            return maybe(response.headers().get("Location")).match(
                    __ -> io((SideEffect) Assert::fail),
                    location -> io(() -> assertEquals(expected, location))
            );
        }).unsafePerformIO();
    }

    private String getHttpsUrl(Response response) {
        var request = response.request();
        return "https://" + request.url().host() + request.url().encodedPath();
    }
}
