package io.serpentes.testing.assertions.definition.base.composite;

import java.util.Collection;

public interface CompositeTypeDefinitionAssertion {
    Collection<String> primitiveValues();

    Collection<String> compositeValues();

    Collection<String> allValues();
}
