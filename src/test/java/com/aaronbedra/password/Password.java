package com.aaronbedra.password;

import lombok.Value;

import java.security.SecureRandom;

@Value
public class Password {
    String value;
    SecureRandom secureRandom;

    private Password(String value) {
        this.value = value;
        this.secureRandom = new SecureRandom();
    }

    public static Password password(String password) {
        return new Password(password);
    }

    public static Password generatePassword(int length) {
        return password(randomString(length));
    }

    private static String randomString(int length) {
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder builder = new StringBuilder();

        for(int i = 0; i < length; i++) {
            builder.append((char)secureRandom.nextInt(128));
        }

        return builder.toString();
    }
}
