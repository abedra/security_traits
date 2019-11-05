package com.aaronbedra.tiny.traits;

import com.aaronbedra.tiny.SensitiveString;
import com.jnape.palatable.traitor.traits.Trait;

import static com.aaronbedra.tiny.SensitiveString.REDACTED_VALUE;
import static org.junit.Assert.assertEquals;

public class HasRedactedDefaultGetters<T extends SensitiveString> implements Trait<T> {
    @Override
    public void test(T testSubject) {
        assertEquals("toString()", REDACTED_VALUE, testSubject.toString());
        assertEquals("getValue()", REDACTED_VALUE, testSubject.getValue());
    }
}
