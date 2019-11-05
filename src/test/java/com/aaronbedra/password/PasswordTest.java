package com.aaronbedra.password;

import com.aaronbedra.password.traits.*;
import com.aaronbedra.tiny.traits.HasRedactedDefaultGetters;
import com.aaronbedra.tiny.traits.HasUnsafeToString;
import com.jnape.palatable.lambda.functions.Fn0;
import com.jnape.palatable.traitor.annotations.TestTraits;
import com.jnape.palatable.traitor.runners.Traits;
import org.junit.runner.RunWith;

import static com.aaronbedra.password.Password.generatePassword;
import static com.aaronbedra.password.Password.password;
import static com.aaronbedra.password.PasswordConfiguration.passwordConfiguration;
import static com.aaronbedra.password.PasswordRequiredLength.passwordRequiredLength;
import static com.aaronbedra.password.PasswordRequiredLowerCaseCharacters.passwordRequiredLowerCaseCharacters;
import static com.aaronbedra.password.PasswordRequiredNumberCharacters.passwordRequiredNumberCharacters;
import static com.aaronbedra.password.PasswordRequiredSpecialCharacters.passwordRequiredSpecialCharacters;
import static com.aaronbedra.password.PasswordRequiredUpperCaseCharacters.passwordRequiredUpperCaseCharacters;

@RunWith(Traits.class)
public class PasswordTest {
    @TestTraits({
            AtLeastTwelveCharacters.class,
            AtLeastOneNumber.class,
            AtLeastOneUpper.class,
            AtLeastOneLower.class,
            AtLeastOneSpecial.class
    })
    public String passwordGeneratorSingleExecution() {
        return generatePassword(getConfiguration()).unsafeToString();
    }

    @TestTraits(Unique.class)
    public Fn0<String> passwordGeneratorMultipleExecutions() {
        return () -> generatePassword(getConfiguration()).unsafeToString();
    }

    @TestTraits({
            HasRedactedDefaultGetters.class,
            HasUnsafeToString.class
    })
    public Password redactedToString() {
        return password("testing");
    }

    private PasswordConfiguration getConfiguration() {
        return passwordConfiguration(
                passwordRequiredLength(12),
                passwordRequiredLowerCaseCharacters(1),
                passwordRequiredUpperCaseCharacters(1),
                passwordRequiredNumberCharacters(1),
                passwordRequiredSpecialCharacters(1)
        );
    }
}
