package com.aaronbedra.web.traits;

import com.aaronbedra.web.WebRequester;
import com.aaronbedra.web.headers.*;
import com.jnape.palatable.lambda.functions.Fn0;
import com.jnape.palatable.lambda.io.IO;
import com.jnape.palatable.traitor.traits.Trait;
import okhttp3.Cookie;
import okhttp3.Headers;

import java.lang.reflect.InvocationTargetException;

import static com.jnape.palatable.lambda.io.IO.io;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class SecureHeaders implements Trait<WebRequester<IO<?>, Cookie>> {
    @Override
    public void test(WebRequester<IO<?>, Cookie> requester) {
        var headerList = asList(
                XFrameOptions.class,
                XContentTypeOptions.class,
                XXSSProtection.class,
                StrictTransportSecurity.class,
                XDownloadOptions.class,
                XPermittedCrossDomainPolicy.class
        );

        headerList.forEach(headerClass -> getAndAssertSecure(requester, headerClass));
    }

    private <T extends Header> void getAndAssertSecure(WebRequester<IO<?>, Cookie> requester, Class<T> headerClass) {
        requester.requestHttps()
                .flatMap(response -> io((Fn0<Headers>) response::headers))
                .flatMap(headers -> getHeader(headers, headerClass)
                        .flatMap(header -> assertSecureHeader(header, headerClass)))
                .<IO<T>>coerce()
                .unsafePerformIO();
    }

    private <T extends Header> IO<T> getHeader(Headers headers, Class<T> headerClass) {
        return io(() -> headerClass
                .getConstructor(new Class[]{String.class})
                .newInstance(headers.get(invokeStaticGetName(headerClass))));
    }

    private <T extends Header> IO<T> assertSecureHeader(T header, Class<T> headerClass) {
        return io(() -> assertEquals(invokeStaticGetName(headerClass), header.getExpectedValue(), header.getValue()))
                .discardL(io(header));
    }

    private <T extends Header> String invokeStaticGetName(Class<T> headerClass)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        return (String) headerClass.getMethod("getName").invoke(null);
    }
}
