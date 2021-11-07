package io.serpentes.testing.assertions.input.base.composite;

import org.junit.jupiter.api.Assertions;

import java.util.Collection;

public class AssertionUtils {
    private AssertionUtils() {
    }

    public static <T> void assertEquals(final Collection<T> expected, final Collection<T> actual) {
        Assertions.assertEquals(expected.size(), actual.size());
        Assertions.assertTrue(expected.containsAll(actual));
        Assertions.assertTrue(actual.containsAll(expected));
    }
}
