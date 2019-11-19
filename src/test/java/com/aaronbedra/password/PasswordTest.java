package com.aaronbedra.password;

import com.aaronbedra.password.traits.*;
import com.aaronbedra.password.types.PasswordConfiguration;
import com.aaronbedra.tiny.traits.HasRedactedDefaultGetters;
import com.aaronbedra.tiny.traits.HasUnsafeToString;
import com.jnape.palatable.lambda.adt.hlist.Tuple2;
import com.jnape.palatable.lambda.functions.Fn0;
import com.jnape.palatable.traitor.annotations.TestTraits;
import com.jnape.palatable.traitor.runners.Traits;
import org.junit.runner.RunWith;

import static com.aaronbedra.password.Password.generatePassword;
import static com.aaronbedra.password.Password.password;
import static com.aaronbedra.password.types.PasswordConfiguration.passwordConfiguration;
import static com.aaronbedra.password.types.RequiredLength.passwordRequiredLength;
import static com.aaronbedra.password.types.RequiredLowerCaseCharacters.passwordRequiredLowerCaseCharacters;
import static com.aaronbedra.password.types.RequiredNumberCharacters.passwordRequiredNumberCharacters;
import static com.aaronbedra.password.types.RequiredSpecialCharacters.passwordRequiredSpecialCharacters;
import static com.aaronbedra.password.types.RequiredUpperCaseCharacters.passwordRequiredUpperCaseCharacters;
import static com.jnape.palatable.lambda.adt.hlist.HList.tuple;

@RunWith(Traits.class)
public class PasswordTest {
    @TestTraits({
            AtLeastOneNumber.class,
            AtLeastOneUpper.class,
            AtLeastOneLower.class,
            AtLeastOneSpecial.class
    })
    public String passwordGeneratorSingleExecution() {
        return generatePassword(getConfiguration()).unsafeToString();
    }

    @TestTraits(AtLeastNCharacters.class)
    public Tuple2<String, Integer> length() {
        PasswordConfiguration configuration = getConfiguration();
        return tuple(generatePassword(configuration).unsafeToString(), configuration.getLength().getValue());
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
