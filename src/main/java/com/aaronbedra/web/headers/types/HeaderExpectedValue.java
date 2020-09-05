package com.aaronbedra.web.headers.types;

import lombok.AllArgsConstructor;
import lombok.Value;

import static lombok.AccessLevel.PRIVATE;

@Value
@AllArgsConstructor(access = PRIVATE)
public class HeaderExpectedValue {
    String value;

    public static HeaderExpectedValue headerExpectedValue(String value) {
        return new HeaderExpectedValue(value);
    }
}
