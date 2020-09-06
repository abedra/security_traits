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
public class XContentTypeOptions implements Header {
    HeaderName name;
    HeaderExpectedValue expectedValue;

    public static XContentTypeOptions xContentTypeOptions(HeaderName name, HeaderExpectedValue expectedValue) {
        return new XContentTypeOptions(name, expectedValue);
    }

    public static XContentTypeOptions xContentTypeOptions() {
        return xContentTypeOptions(
                headerName("X-Content-Type-Options"),
                headerExpectedValue("nosniff"));
    }
}
