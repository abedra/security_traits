package com.aaronbedra.password;

import com.aaronbedra.tiny.TinyType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PasswordRequiredSpecialCharacters implements TinyType<Integer> {
    Integer value;

    public static PasswordRequiredSpecialCharacters passwordRequiredSpecialCharacters(int value) {
        return new PasswordRequiredSpecialCharacters(value);
    }
}
