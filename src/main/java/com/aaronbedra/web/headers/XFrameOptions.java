package com.aaronbedra.web.headers;

import com.aaronbedra.web.headers.types.HeaderExpectedValue;
import com.aaronbedra.web.headers.types.HeaderName;
import lombok.AllArgsConstructor;
import lombok.Value;

import static com.aaronbedra.web.headers.types.HeaderExpectedValue.headerExpectedValue;
import static com.aaronbedra.web.headers.types.HeaderName.headerName;
import static lombok.AccessLevel.PRIVATE;

@Value
@AllArgsConstructor(access = PRIVATE)
public class XFrameOptions implements Header {
    HeaderName name;
    HeaderExpectedValue expectedValue;

    public static XFrameOptions xFrameOptions() {
        return new XFrameOptions(
                headerName("X-Frame-Options"),
                headerExpectedValue("DENY"));
    }
}
