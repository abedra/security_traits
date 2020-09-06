package com.aaronbedra.web.headers;

import com.aaronbedra.web.types.Header;
import com.jnape.palatable.shoki.api.Sequence;

import static com.aaronbedra.web.headers.StrictTransportSecurity.strictTransportSecurity;
import static com.aaronbedra.web.headers.XContentTypeOptions.xContentTypeOptions;
import static com.aaronbedra.web.headers.XDownloadOptions.xDownloadOptions;
import static com.aaronbedra.web.headers.XFrameOptions.xFrameOptions;
import static com.aaronbedra.web.headers.XPermittedCrossDomainPolicy.xPermittedCrossDomainPolicy;
import static com.aaronbedra.web.headers.XXSSProtection.xxssProtection;
import static com.jnape.palatable.shoki.impl.StrictQueue.strictQueue;

public class DefaultSecureHeaders {
    public static Sequence<Header> defaultSecureHeaders() {
        return strictQueue(
                strictTransportSecurity(),
                xContentTypeOptions(),
                xDownloadOptions(),
                xFrameOptions(),
                xPermittedCrossDomainPolicy(),
                xxssProtection());
    }
}
