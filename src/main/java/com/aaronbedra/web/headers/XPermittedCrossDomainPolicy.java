package com.aaronbedra.web.headers;

import com.aaronbedra.web.headers.types.HeaderExpectedValue;
import com.aaronbedra.web.headers.types.HeaderName;
import lombok.AllArgsConstructor;
import lombok.Value;

import static com.aaronbedra.web.headers.types.HeaderExpectedValue.headerExpectedValue;
import static com.aaronbedra.web.headers.types.HeaderName.headerName;
import static lombok.AccessLevel.PRIVATE;

@Value
@AllArgsConstructor(access = PRIVATE)
public class XPermittedCrossDomainPolicy implements Header {
    HeaderName name;
    HeaderExpectedValue expectedValue;

    public static XPermittedCrossDomainPolicy xPermittedCrossDomainPolicy() {
        return new XPermittedCrossDomainPolicy(
                headerName("X-Permitted-Cross-Domain-Policy"),
                headerExpectedValue("none"));
    }
}
