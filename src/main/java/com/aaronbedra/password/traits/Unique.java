package com.aaronbedra.password.traits;

import com.jnape.palatable.lambda.functions.Fn0;
import com.jnape.palatable.traitor.traits.Trait;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Unique implements Trait<Fn0<String>> {
    @Override
    public void test(Fn0<String> stringFn) {
        List<String> generatedValues = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            generatedValues.add(stringFn.apply());
        }

        assertEquals(
                generatedValues + " are not unique",
                generatedValues.size(),
                new HashSet<>(generatedValues).size()
        );
    }
}
