package com.aaronbedra.web.headers;

import lombok.Value;

@Value
public class XDownloadOptions implements Header {
    String value;

    public static String getName() {
        return "X-Download-Options";
    }

    @Override
    public String getExpectedValue() {
        return "noopen";
    }
}
