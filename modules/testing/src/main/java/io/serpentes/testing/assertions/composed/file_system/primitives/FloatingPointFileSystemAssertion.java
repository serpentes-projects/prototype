package io.serpentes.testing.assertions.composed.file_system.primitives;

import io.serpentes.testing.assertions.composed.file_system.FileSystemAssertion;
import io.serpentes.testing.assertions.input.content.json.primitive.FloatingPointJsonContent;
import io.serpentes.testing.assertions.input.file_system.primitive.FileSystemFloatingPointInputAssertion;
import io.serpentes.testing.assertions.definition.content.json_schema.primitive.FloatingPointJsonSchemaContent;
import io.serpentes.testing.assertions.definition.file_system.primitive.FileSystemFloatingPointTypeDefinitionAssertion;

public class FloatingPointFileSystemAssertion implements FileSystemAssertion {
    @Override
    public FileSystemFloatingPointTypeDefinitionAssertion getTypeDefinitionAssertion() {
        return new FileSystemFloatingPointTypeDefinitionAssertion(new FloatingPointJsonSchemaContent());
    }

    @Override
    public FileSystemFloatingPointInputAssertion getInputAssertion() {
        return new FileSystemFloatingPointInputAssertion(new FloatingPointJsonContent());
    }
}
