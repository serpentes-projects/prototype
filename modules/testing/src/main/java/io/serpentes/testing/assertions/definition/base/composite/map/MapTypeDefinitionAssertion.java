package io.serpentes.testing.assertions.definition.base.composite.map;

import io.serpentes.testing.assertions.definition.base.DeserializableTypeDefinitionAssertion;
import io.serpentes.testing.assertions.definition.base.composite.CompositeTypeDefinitionAssertion;

import java.util.Collection;

public interface MapTypeDefinitionAssertion extends DeserializableTypeDefinitionAssertion, CompositeTypeDefinitionAssertion {
    Collection<String> primitiveKeyNames();

    Collection<String> compositeKeyNames();

    Collection<String> allKeyNames();
}
