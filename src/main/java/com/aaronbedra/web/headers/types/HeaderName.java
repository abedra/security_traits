package com.aaronbedra.web.headers.types;

import lombok.AllArgsConstructor;
import lombok.Value;

import static lombok.AccessLevel.PRIVATE;

@Value
@AllArgsConstructor(access = PRIVATE)
public class HeaderName {
    String value;

    public static HeaderName headerName(String value) {
        return new HeaderName(value);
    }
}
