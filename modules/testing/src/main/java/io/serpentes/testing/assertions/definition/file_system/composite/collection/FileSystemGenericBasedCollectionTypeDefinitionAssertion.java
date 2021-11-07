package io.serpentes.testing.assertions.definition.file_system.composite.collection;

import io.serpentes.testing.assertions.definition.base.GenericBasedTypeDefinitionAssertion;
import io.serpentes.testing.assertions.definition.file_system.FileSystemTypeDefinitionAssertion;

import java.util.Collection;

public interface FileSystemGenericBasedCollectionTypeDefinitionAssertion<T> extends GenericBasedTypeDefinitionAssertion<Collection<T>>, FileSystemTypeDefinitionAssertion<Collection<T>> {
}
