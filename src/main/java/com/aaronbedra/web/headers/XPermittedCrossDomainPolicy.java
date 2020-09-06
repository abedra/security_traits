package com.aaronbedra.web.headers;

import com.aaronbedra.web.types.Header;
import com.aaronbedra.web.types.HeaderExpectedValue;
import com.aaronbedra.web.types.HeaderName;
import lombok.AllArgsConstructor;
import lombok.Value;

import static com.aaronbedra.web.types.HeaderExpectedValue.headerExpectedValue;
import static com.aaronbedra.web.types.HeaderName.headerName;
import static lombok.AccessLevel.PRIVATE;

@Value
@AllArgsConstructor(access = PRIVATE)
public class XPermittedCrossDomainPolicy implements Header {
    HeaderName name;
    HeaderExpectedValue expectedValue;

    public static XPermittedCrossDomainPolicy xPermittedCrossDomainPolicy(HeaderName name, HeaderExpectedValue expectedValue) {
        return new XPermittedCrossDomainPolicy(name, expectedValue);
    }

    public static XPermittedCrossDomainPolicy xPermittedCrossDomainPolicy() {
        return xPermittedCrossDomainPolicy(
                headerName("X-Permitted-Cross-Domain-Policy"),
                headerExpectedValue("none"));
    }
}
