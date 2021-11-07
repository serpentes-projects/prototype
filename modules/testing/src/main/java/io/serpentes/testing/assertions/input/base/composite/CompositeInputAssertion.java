package io.serpentes.testing.assertions.input.base.composite;

import java.util.Collection;

public interface CompositeInputAssertion {
    Collection<String> primitiveValues();

    Collection<String> compositeValues();

    Collection<String> allValues();
}
