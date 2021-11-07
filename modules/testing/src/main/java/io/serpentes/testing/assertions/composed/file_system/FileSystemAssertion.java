package io.serpentes.testing.assertions.composed.file_system;

import io.serpentes.testing.assertions.composed.ComposedAssertion;
import io.serpentes.testing.assertions.input.file_system.FileSystemInputAssertion;
import io.serpentes.testing.assertions.definition.file_system.FileSystemTypeDefinitionAssertion;

public interface FileSystemAssertion extends ComposedAssertion {

    FileSystemTypeDefinitionAssertion<?> getTypeDefinitionAssertion();

    FileSystemInputAssertion<?> getInputAssertion();
}
