package io.serpentes.testing.assertions.definition.base.composite.collection;

import io.serpentes.testing.assertions.definition.base.DeserializableTypeDefinitionAssertion;
import io.serpentes.testing.assertions.definition.base.composite.CompositeTypeDefinitionAssertion;

public interface CollectionTypeDefinitionAssertion extends DeserializableTypeDefinitionAssertion, CompositeTypeDefinitionAssertion {
    default void assertValue() {
    }
}
