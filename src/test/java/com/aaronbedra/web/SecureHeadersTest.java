package com.aaronbedra.web;

import com.aaronbedra.web.headers.Header;
import com.aaronbedra.web.headers.XContentTypeOptions;
import com.aaronbedra.web.headers.XFrameOptions;
import com.aaronbedra.web.headers.XXSSProtection;
import com.jnape.palatable.lambda.io.IO;
import org.junit.Before;
import org.junit.Test;

import static com.aaronbedra.web.Requester.requester;
import static com.jnape.palatable.lambda.io.IO.io;
import static org.junit.Assert.assertEquals;

public class SecureHeadersTest {
    private Requester requester;

    private <T extends Header> IO<T> assertSecureHeader(T header) {
        return io(() -> assertEquals(header.getExpectedValue(), header.getValue())).discardL(io(header));
    }

    private <T extends Header> void testHeader(Class<T> headerClass) {
        requester.getHeadersIO()
                .flatMap(headers -> requester.getHeaderIO(headers, headerClass))
                .flatMap(this::assertSecureHeader)
                .unsafePerformIO();
    }

    @Before
    public void before() {
        requester = requester("https://getrepsheet.com");
    }

    @Test
    public void xFrameOptions() {
        testHeader(XFrameOptions.class);
    }

    @Test
    public void xXssProtection() {
        testHeader(XXSSProtection.class);
    }

    @Test
    public void xContentTypeOptions() {
        testHeader(XContentTypeOptions.class);
    }
}
