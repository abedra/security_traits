package com.aaronbedra.web.traits;

import com.aaronbedra.web.headers.*;
import com.jnape.palatable.lambda.io.IO;
import com.jnape.palatable.traitor.traits.Trait;
import okhttp3.Headers;

import static com.jnape.palatable.lambda.io.IO.io;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class SecureHeaders implements Trait<IO<Headers>> {
    @Override
    public void test(IO<Headers> headersIO) {
        var headerList = asList(
                XFrameOptions.class,
                XContentTypeOptions.class,
                XXSSProtection.class,
                StrictTransportSecurity.class
        );


        headerList.forEach(headerClass -> getAndAssertSecure(headersIO, headerClass));
    }

    private <T extends Header> void getAndAssertSecure(IO<Headers> headers, Class<T> headerClass) {
        getHeaderIO(headers, headerClass).flatMap(this::assertSecureHeader).unsafePerformIO();
    }

    private <T extends Header> IO<T> getHeaderIO(IO<Headers> headersIO, Class<T> headerClass) {
        return headersIO.flatMap(headers -> io(() -> {
            String name = (String) headerClass.getMethod("getName").invoke(null);
            return headerClass
                    .getConstructor(new Class[]{String.class})
                    .newInstance(headers.get(name));
        }));
    }

    private <T extends Header> IO<T> assertSecureHeader(T header) {
        return io(() -> assertEquals(header.getExpectedValue(), header.getValue())).discardL(io(header));
    }
}
