package com.aaronbedra.password.traits;

import com.jnape.palatable.traitor.traits.Trait;

import static org.junit.Assert.assertTrue;

public class AtLeastOneLower implements Trait<String> {
    @Override
    public void test(String s) {
        boolean hasOne = false;
        for (char c : s.toCharArray()) {
            if (Character.isLowerCase(c)) {
                hasOne = true;
            }
        }

        assertTrue(s + " does not have at least one lower case character", hasOne);
    }
}
