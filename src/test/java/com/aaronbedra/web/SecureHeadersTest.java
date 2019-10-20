package com.aaronbedra.web;

import com.aaronbedra.web.headers.Header;
import com.aaronbedra.web.headers.XFrameOptions;
import com.jnape.palatable.lambda.io.IO;
import org.junit.Test;

import static com.aaronbedra.web.Requester.requester;
import static com.jnape.palatable.lambda.io.IO.io;
import static org.junit.Assert.assertEquals;

public class SecureHeadersTest {
    private <T extends Header> IO<T> assertSecureHeader(T header) {
        return io(() -> assertEquals(header.getExpectedValue(), header.getValue())).discardL(io(header));
    }

    @Test
    public void xFrameOptions() {
        var requester = requester("https://getrepsheet.com");
        requester
                .getHeadersIO()
                .flatMap(headers -> requester.getHeaderIO(headers, XFrameOptions.class))
                .flatMap(this::assertSecureHeader)
                .unsafePerformIO();
    }
}
