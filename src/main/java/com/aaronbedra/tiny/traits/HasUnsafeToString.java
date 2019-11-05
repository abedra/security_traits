package com.aaronbedra.tiny.traits;

import com.aaronbedra.tiny.SensitiveString;
import com.jnape.palatable.traitor.traits.Trait;

import static com.aaronbedra.tiny.SensitiveString.REDACTED_VALUE;
import static com.jnape.palatable.lambda.functions.builtin.fn2.CmpEq.cmpEq;
import static org.junit.Assert.assertFalse;

public class HasUnsafeToString <T extends SensitiveString> implements Trait<T> {
    @Override
    public void test(T testSubject) {
        assertFalse(cmpEq(REDACTED_VALUE, testSubject.unsafeToString()));
    }
}
