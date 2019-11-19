package com.aaronbedra.password;

import com.aaronbedra.tiny.SensitiveString;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.security.SecureRandom;
import java.util.ArrayList;

import static com.aaronbedra.password.CharacterRange.*;
import static com.jnape.palatable.lambda.functions.builtin.fn3.FoldLeft.foldLeft;
import static java.lang.String.valueOf;
import static java.util.Arrays.asList;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Password implements SensitiveString {
    private final String value;

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
        var numbers = randomChars(secureRandom, NUMBER, configuration.getNumber().getValue());
        var uppers = randomChars(secureRandom, UPPER, configuration.getUpper().getValue());
        var lowers = randomChars(secureRandom, LOWER, configuration.getLower().getValue());
        var specials = randomChars(secureRandom, SPECIAL, configuration.getSpecial().getValue());
        var currentSize = foldLeft((m, v) -> m += v.size(), 0, asList(numbers, uppers, lowers, specials));
        var rest = randomChars(secureRandom, ALL, configuration.getLength().getValue() - currentSize);
        var combined = foldLeft((m, v) -> {
            m.addAll(v);
            return m;
        }, new ArrayList<>(), asList(numbers, uppers, lowers, specials, rest));

        return password(valueOf(combined));
    }

    private static ArrayList<Character> randomChars(
            SecureRandom secureRandom,
            ArrayList<Character> range,
            int length) {

        return new ArrayList<>() {{
            for (int i = 0; i < length; i++) {
                add(range.get(secureRandom.nextInt(range.size())));
            }
        }};
    }
}