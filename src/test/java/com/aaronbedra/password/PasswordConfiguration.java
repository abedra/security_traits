package com.aaronbedra.password;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PasswordConfiguration {
    int length;
    int lower;
    int upper;
    int number;
    int special;

    public static PasswordConfiguration passwordConfiguration(
            int length,
            int lower,
            int upper,
            int number,
            int special) {

        return new PasswordConfiguration(length, lower, upper, number, special);
    }
}
