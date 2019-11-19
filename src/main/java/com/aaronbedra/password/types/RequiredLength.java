package com.aaronbedra.password.types;

import com.aaronbedra.tiny.TinyType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RequiredLength implements TinyType<Integer> {
    Integer value;

    public static RequiredLength passwordRequiredLength(int value) {
        return new RequiredLength(value);
    }
}
