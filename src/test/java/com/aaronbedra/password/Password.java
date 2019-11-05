package com.aaronbedra.password;

import com.aaronbedra.tiny.SensitiveString;

import java.security.SecureRandom;
import java.util.ArrayList;

import static com.aaronbedra.password.CharacterRange.*;
import static java.lang.String.valueOf;

public class Password implements SensitiveString {
    private final String value;

    private Password(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return SensitiveString.REDACTED_VALUE;
    }

    @Override
    public String getValue() {
        return SensitiveString.REDACTED_VALUE;
    }

    @Override
    public String unsafeToString() {
        return value;
    }

    public static Password password(String password) {
        return new Password(password);
    }

    public static Password generatePassword(PasswordConfiguration configuration) {
        var secureRandom = new SecureRandom();
        var numbers = randomChars(secureRandom, NUMBER, configuration.getNumber());
        var uppers = randomChars(secureRandom, UPPER, configuration.getUpper());
        var lowers = randomChars(secureRandom, LOWER, configuration.getLower());
        var specials = randomChars(secureRandom, SPECIAL, configuration.getSpecial());

        var currentSize = numbers.size() + uppers.size() + lowers.size() + specials.size();
        var filler = randomChars(secureRandom, ALL, configuration.getLength() - currentSize);

        ArrayList<Character> combined = new ArrayList<>() {{
            addAll(numbers);
            addAll(uppers);
            addAll(lowers);
            addAll(specials);
            addAll(filler);
        }};

        return password(valueOf(combined));
    }

    private static ArrayList<Character> randomChars(
            SecureRandom secureRandom,
            ArrayList<Character> range,
            int length) {

        ArrayList<Character> characters = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            characters.add(range.get(secureRandom.nextInt(range.size())));
        }

        return characters;
    }
}