package com.aaronbedra.web.headers;

import lombok.Value;

@Value
public class XFrameOptions implements Header {
    String value;

    public static String getName() {
        return "X-Frame-Options";
    }

    @Override
    public String getExpectedValue() {
        return "DENY";
    }
}
