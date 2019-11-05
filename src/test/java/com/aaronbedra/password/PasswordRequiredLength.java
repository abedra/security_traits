package com.aaronbedra.password;

import com.aaronbedra.tiny.TinyType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PasswordRequiredLength implements TinyType<Integer> {
    Integer value;

    public static PasswordRequiredLength passwordRequiredLength(int value) {
        return new PasswordRequiredLength(value);
    }
}
