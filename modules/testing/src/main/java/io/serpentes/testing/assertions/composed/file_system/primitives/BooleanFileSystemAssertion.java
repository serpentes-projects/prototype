package io.serpentes.testing.assertions.composed.file_system.primitives;

import io.serpentes.testing.assertions.composed.file_system.FileSystemAssertion;
import io.serpentes.testing.assertions.input.content.json.primitive.BooleanJsonContent;
import io.serpentes.testing.assertions.input.file_system.primitive.FileSystemBooleanInputAssertion;
import io.serpentes.testing.assertions.definition.content.json_schema.primitive.BooleanJsonSchemaContent;
import io.serpentes.testing.assertions.definition.file_system.primitive.FileSystemBooleanTypeDefinitionAssertion;

public class BooleanFileSystemAssertion implements FileSystemAssertion {
    @Override
    public FileSystemBooleanTypeDefinitionAssertion getTypeDefinitionAssertion() {
        return new FileSystemBooleanTypeDefinitionAssertion(new BooleanJsonSchemaContent());
    }

    @Override
    public FileSystemBooleanInputAssertion getInputAssertion() {
        return new FileSystemBooleanInputAssertion(new BooleanJsonContent());
    }
}
