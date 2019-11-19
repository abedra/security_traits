package com.aaronbedra.password.types;

import com.aaronbedra.tiny.TinyType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RequiredLowerCaseCharacters implements TinyType<Integer> {
    Integer value;

    public static RequiredLowerCaseCharacters passwordRequiredLowerCaseCharacters(int value) {
        return new RequiredLowerCaseCharacters(value);
    }
}
