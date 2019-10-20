package com.aaronbedra.web.headers;

public interface Header {
    static String getName() {
        throw new IllegalArgumentException();
    }

    String getValue();
    String getExpectedValue();
}
