package io.serpentes.testing.assertions.definition.base.composite.collection;

import io.serpentes.testing.assertions.definition.base.GenericBasedTypeDefinitionAssertion;
import io.serpentes.testing.assertions.definition.base.composite.CompositeTypeDefinitionAssertion;

import java.util.Collection;

public interface GenericBasedCollectionTypeDefinition<T> extends GenericBasedTypeDefinitionAssertion<Collection<T>>, CompositeTypeDefinitionAssertion {
}
