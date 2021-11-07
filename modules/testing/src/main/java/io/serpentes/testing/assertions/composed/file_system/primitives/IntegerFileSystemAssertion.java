package io.serpentes.testing.assertions.composed.file_system.primitives;

import io.serpentes.testing.assertions.composed.file_system.FileSystemAssertion;
import io.serpentes.testing.assertions.input.content.json.primitive.IntegerJsonContent;
import io.serpentes.testing.assertions.input.file_system.primitive.FileSystemIntegerInputAssertion;
import io.serpentes.testing.assertions.definition.content.json_schema.primitive.IntegerJsonSchemaContent;
import io.serpentes.testing.assertions.definition.file_system.primitive.FileSystemIntegerTypeDefinitionAssertion;

public class IntegerFileSystemAssertion implements FileSystemAssertion {
    @Override
    public FileSystemIntegerTypeDefinitionAssertion getTypeDefinitionAssertion() {
        return new FileSystemIntegerTypeDefinitionAssertion(new IntegerJsonSchemaContent());
    }

    @Override
    public FileSystemIntegerInputAssertion getInputAssertion() {
        return new FileSystemIntegerInputAssertion(new IntegerJsonContent());
    }
}
