package com.aaronbedra.web.headers;

import lombok.Value;

@Value
public class XPermittedCrossDomainPolicy implements Header {
    String value;

    public static String getName() {
        return "X-Permitted-Cross-Domain-Policy";
    }

    @Override
    public String getExpectedValue() {
        return "none";
    }
}
