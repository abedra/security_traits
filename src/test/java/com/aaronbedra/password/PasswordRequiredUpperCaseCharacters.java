package com.aaronbedra.password;

import com.aaronbedra.tiny.TinyType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PasswordRequiredUpperCaseCharacters implements TinyType<Integer> {
    Integer value;

    public static PasswordRequiredUpperCaseCharacters passwordRequiredUpperCaseCharacters(int value) {
        return new PasswordRequiredUpperCaseCharacters(value);
    }
}
