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
public class StrictTransportSecurity implements Header {
    HeaderName name;
    HeaderExpectedValue expectedValue;

    public static StrictTransportSecurity strictTransportSecurity() {
        return new StrictTransportSecurity(
                headerName("Strict-Transport-Security"),
                headerExpectedValue("max-age=31536000; includeSubDomains"));
    }
}
