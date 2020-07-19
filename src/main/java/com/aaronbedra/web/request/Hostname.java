package com.aaronbedra.web.request;

import lombok.AllArgsConstructor;
import lombok.Value;

import static lombok.AccessLevel.PRIVATE;

@Value
@AllArgsConstructor(access = PRIVATE)
public class Hostname {
    String value;

    public static Hostname hostname(String value) {
        return new Hostname(value);
    }
}
