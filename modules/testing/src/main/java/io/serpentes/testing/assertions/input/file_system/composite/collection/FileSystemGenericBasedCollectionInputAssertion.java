package io.serpentes.testing.assertions.input.file_system.composite.collection;

import io.serpentes.testing.assertions.input.base.DeserializableInputAssertion;
import io.serpentes.testing.assertions.input.file_system.FileSystemInputAssertion;

import java.util.Collection;

public interface FileSystemGenericBasedCollectionInputAssertion<T> extends DeserializableInputAssertion<Collection<T>>, FileSystemInputAssertion<Collection<T>> {
}
