package com.aaronbedra.password;

import com.aaronbedra.password.traits.*;
import com.jnape.palatable.lambda.functions.Fn0;
import com.jnape.palatable.traitor.annotations.TestTraits;
import com.jnape.palatable.traitor.runners.Traits;
import org.junit.runner.RunWith;

import static com.aaronbedra.password.Password.generatePassword;
import static com.aaronbedra.password.PasswordConfiguration.passwordConfiguration;

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
        return generatePassword(getConfiguration()).getValue();
    }

    @TestTraits(Unique.class)
    public Fn0<String> passwordGeneratorMultipleExecutions() {
        return () -> generatePassword(getConfiguration()).getValue();
    }

    private PasswordConfiguration getConfiguration() {
        return passwordConfiguration(12, 1, 1, 1, 1);
    }
}
