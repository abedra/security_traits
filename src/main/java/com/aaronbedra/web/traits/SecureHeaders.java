package com.aaronbedra.web.traits;

import com.aaronbedra.web.request.WebRequester;
import com.aaronbedra.web.types.Header;
import com.aaronbedra.web.types.WebRequestTestSubject;
import com.jnape.palatable.lambda.adt.hlist.Tuple2;
import com.jnape.palatable.lambda.functions.specialized.SideEffect;
import com.jnape.palatable.lambda.io.IO;
import com.jnape.palatable.shoki.api.Sequence;
import com.jnape.palatable.traitor.traits.Trait;
import okhttp3.Cookie;
import okhttp3.Headers;

import static com.jnape.palatable.lambda.functions.Fn0.fn0;
import static com.jnape.palatable.lambda.functions.specialized.SideEffect.sideEffect;
import static com.jnape.palatable.lambda.io.IO.io;
import static com.jnape.palatable.lambda.traversable.LambdaIterable.wrap;
import static org.junit.Assert.assertEquals;

public class SecureHeaders implements Trait<WebRequestTestSubject<IO<?>, Cookie>> {
    @Override
    public void test(WebRequestTestSubject<IO<?>, Cookie> testSubject) {
        getResponseHeaders(testSubject.getRequester())
                .fmap(headers -> wrap(testSubject.getVerifiedHeaders()).fmap(header -> assertSecure(headers, header)).unwrap())
                .flatMap(sideEffects -> io(() -> sideEffects.forEach(sideEffect -> sideEffect.toRunnable().run())))
                .unsafePerformIO();
    }

    private IO<Headers> getResponseHeaders(WebRequester<IO<?>, Cookie> requester) {
        return requester.requestHttps()
                .flatMap(response -> io(fn0(response::headers)))
                .coerce();
    }

    private SideEffect assertSecure(Headers headers, Header header) {
        return sideEffect(() -> assertEquals(
                header.getName().getValue(),
                header.getExpectedValue().getValue(),
                headers.get(header.getName().getValue())));
    }
}
