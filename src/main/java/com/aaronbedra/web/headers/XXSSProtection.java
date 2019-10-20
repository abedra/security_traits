package com.aaronbedra.web.headers;

import lombok.Value;

@Value
public class XXSSProtection implements Header {
    String value;

    public static String getName() {
        return "X-XSS-Protection";
    }

    @Override
    public String getExpectedValue() {
        return "1; mode=block";
    }
}
