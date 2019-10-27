package com.aaronbedra.password;

import com.aaronbedra.password.traits.*;
import com.jnape.palatable.lambda.functions.Fn0;
import com.jnape.palatable.traitor.annotations.TestTraits;
import com.jnape.palatable.traitor.runners.Traits;
import org.junit.runner.RunWith;

import static com.aaronbedra.password.Password.generatePassword;

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
        return generatePassword(12).getValue();
    }

    @TestTraits(Unique.class)
    public Fn0<String> passwordGeneratorMultipleExecutions() {
        return () -> generatePassword(12).getValue();
    }
}
