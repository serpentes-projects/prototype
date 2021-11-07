package io.serpentes.testing.assertions.composed.file_system.primitives;

import io.serpentes.testing.assertions.composed.file_system.FileSystemAssertion;
import io.serpentes.testing.assertions.input.content.json.primitive.StringJsonContent;
import io.serpentes.testing.assertions.input.file_system.primitive.FileSystemStringInputAssertion;
import io.serpentes.testing.assertions.definition.content.json_schema.primitive.StringJsonSchemaContent;
import io.serpentes.testing.assertions.definition.file_system.primitive.FileSystemStringTypeDefinitionAssertion;

public class StringFileSystemAssertion implements FileSystemAssertion {
    @Override
    public FileSystemStringTypeDefinitionAssertion getTypeDefinitionAssertion() {
        return new FileSystemStringTypeDefinitionAssertion(new StringJsonSchemaContent());
    }

    @Override
    public FileSystemStringInputAssertion getInputAssertion() {
        return new FileSystemStringInputAssertion(new StringJsonContent());
    }
}
