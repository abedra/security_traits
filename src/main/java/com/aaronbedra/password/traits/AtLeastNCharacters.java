package com.aaronbedra.password.traits;

import com.jnape.palatable.lambda.adt.hlist.Tuple2;
import com.jnape.palatable.traitor.traits.Trait;

import static com.jnape.palatable.lambda.functions.builtin.fn2.GTE.gte;
import static java.lang.String.format;
import static org.junit.Assert.assertTrue;

public class AtLeastNCharacters implements Trait<Tuple2<String, Integer>> {
    @Override
    public void test(Tuple2<String, Integer> testSubject) {
        assertTrue(format("Minimum password length less than %s", testSubject._2()),
                gte(testSubject._2(), testSubject._1().length())
        );
    }
}
