package com.aaronbedra.web.headers;

import lombok.Value;

@Value
public class StrictTransportSecurity implements Header {
    String value;

    public static String getName() {
        return "Strict-Transport-Security";
    }

    @Override
    public String getExpectedValue() {
        return "max-age=31536000; includeSubDomains";
    }
}
