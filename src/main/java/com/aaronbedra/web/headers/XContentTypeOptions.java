package com.aaronbedra.web.headers;

import lombok.Value;

@Value
public class XContentTypeOptions implements Header {
    String value;

    public static String getName() {
        return "X-Content-Type-Options";
    }

    @Override
    public String getExpectedValue() {
        return "nosniff";
    }
}
