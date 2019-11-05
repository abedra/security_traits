package com.aaronbedra.password;

import com.aaronbedra.tiny.TinyType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PasswordRequiredLowerCaseCharacters implements TinyType<Integer> {
    Integer value;

    public static PasswordRequiredLowerCaseCharacters passwordRequiredLowerCaseCharacters(int value) {
        return new PasswordRequiredLowerCaseCharacters(value);
    }
}
