package com.aaronbedra.web.headers;

import com.aaronbedra.web.headers.types.HeaderExpectedValue;
import com.aaronbedra.web.headers.types.HeaderName;

public interface Header {
    HeaderName getName();

    HeaderExpectedValue getExpectedValue();
}
