package com.aaronbedra.password.traits;

import com.jnape.palatable.traitor.traits.Trait;

import static com.jnape.palatable.lambda.functions.builtin.fn2.GTE.gte;
import static org.junit.Assert.assertTrue;

public class SecureMinimumLength implements Trait<String> {
    @Override
    public void test(String s) {
        assertTrue("Minimum password length less than 12", gte(12, s.length()));
    }
}
