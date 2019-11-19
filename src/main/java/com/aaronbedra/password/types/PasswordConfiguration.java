package com.aaronbedra.password.types;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PasswordConfiguration {
    RequiredLength length;
    RequiredLowerCaseCharacters lower;
    RequiredUpperCaseCharacters upper;
    RequiredNumberCharacters number;
    RequiredSpecialCharacters special;

    public static PasswordConfiguration passwordConfiguration(
            RequiredLength length,
            RequiredLowerCaseCharacters lower,
            RequiredUpperCaseCharacters upper,
            RequiredNumberCharacters number,
            RequiredSpecialCharacters special) {

        return new PasswordConfiguration(length, lower, upper, number, special);
    }
}
