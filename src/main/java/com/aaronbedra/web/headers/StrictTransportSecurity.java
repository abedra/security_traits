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
public class StrictTransportSecurity implements Header {
    HeaderName name;
    HeaderExpectedValue expectedValue;

    public static StrictTransportSecurity strictTransportSecurity(HeaderName name, HeaderExpectedValue expectedValue) {
        return new StrictTransportSecurity(name, expectedValue);
    }

    public static StrictTransportSecurity strictTransportSecurity() {
        return strictTransportSecurity(
                headerName("Strict-Transport-Security"),
                headerExpectedValue("max-age=31536000; includeSubDomains"));
    }
}
