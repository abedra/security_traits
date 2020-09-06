package com.aaronbedra.web.headers;

import com.aaronbedra.web.types.Header;
import com.aaronbedra.web.types.HeaderExpectedValue;
import com.aaronbedra.web.types.HeaderName;
import lombok.AllArgsConstructor;
import lombok.Value;

import static com.aaronbedra.web.types.HeaderExpectedValue.headerExpectedValue;
import static com.aaronbedra.web.types.HeaderName.headerName;
import static lombok.AccessLevel.PRIVATE;

@Value
@AllArgsConstructor(access = PRIVATE)
public class XXSSProtection implements Header {
    HeaderName name;
    HeaderExpectedValue expectedValue;

    public static XXSSProtection xxssProtection(HeaderName name, HeaderExpectedValue expectedValue) {
        return new XXSSProtection(name, expectedValue);
    }

    public static XXSSProtection xxssProtection() {
        return xxssProtection(
                headerName("X-XSS-Protection"),
                headerExpectedValue("1; mode=block"));
    }
}
