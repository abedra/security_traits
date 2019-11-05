package com.aaronbedra.password;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PasswordConfiguration {
    PasswordRequiredLength length;
    PasswordRequiredLowerCaseCharacters lower;
    PasswordRequiredUpperCaseCharacters upper;
    PasswordRequiredNumberCharacters number;
    PasswordRequiredSpecialCharacters special;

    public static PasswordConfiguration passwordConfiguration(
            PasswordRequiredLength length,
            PasswordRequiredLowerCaseCharacters lower,
            PasswordRequiredUpperCaseCharacters upper,
            PasswordRequiredNumberCharacters number,
            PasswordRequiredSpecialCharacters special) {

        return new PasswordConfiguration(length, lower, upper, number, special);
    }
}
