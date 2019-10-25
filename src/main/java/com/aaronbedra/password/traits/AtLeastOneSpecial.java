package com.aaronbedra.password.traits;

import com.jnape.palatable.traitor.traits.Trait;

import static org.junit.Assert.assertTrue;

public class AtLeastOneSpecial implements Trait<String> {
    private static final char[] SPECIAL_CHARACTERS = { '!', '@', '#', '$', '%', '^', '&', '*', '(', ')'};

    @Override
    public void test(String s) {
        boolean hasOne = false;

        for (char c : SPECIAL_CHARACTERS) {
            if (s.indexOf(c) >= 0) {
                hasOne = true;
            }
        }

        assertTrue(s + " does not have at least one special character", hasOne);
    }
}
