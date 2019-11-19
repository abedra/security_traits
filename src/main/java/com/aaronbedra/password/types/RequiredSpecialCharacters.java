package com.aaronbedra.password.types;

import com.aaronbedra.tiny.TinyType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RequiredSpecialCharacters implements TinyType<Integer> {
    Integer value;

    public static RequiredSpecialCharacters passwordRequiredSpecialCharacters(int value) {
        return new RequiredSpecialCharacters(value);
    }
}
