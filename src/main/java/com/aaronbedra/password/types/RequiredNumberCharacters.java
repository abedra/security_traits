package com.aaronbedra.password.types;

import com.aaronbedra.tiny.TinyType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RequiredNumberCharacters implements TinyType<Integer> {
    Integer value;

    public static RequiredNumberCharacters passwordRequiredNumberCharacters(int value) {
        return new RequiredNumberCharacters(value);
    }
}
