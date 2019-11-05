package com.aaronbedra.tiny;

public interface SensitiveString extends TinyType<String> {
    String REDACTED_VALUE = "REDACTED";
    String unsafeToString();
}
