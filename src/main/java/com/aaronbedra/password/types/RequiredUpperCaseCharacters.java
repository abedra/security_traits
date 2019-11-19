package com.aaronbedra.password.types;

import com.aaronbedra.tiny.TinyType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RequiredUpperCaseCharacters implements TinyType<Integer> {
    Integer value;

    public static RequiredUpperCaseCharacters passwordRequiredUpperCaseCharacters(int value) {
        return new RequiredUpperCaseCharacters(value);
    }
}
