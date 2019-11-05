package com.aaronbedra.password;

import com.aaronbedra.tiny.TinyType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PasswordRequiredNumberCharacters implements TinyType<Integer> {
    Integer value;

    public static PasswordRequiredNumberCharacters passwordRequiredNumberCharacters(int value) {
        return new PasswordRequiredNumberCharacters(value);
    }
}
