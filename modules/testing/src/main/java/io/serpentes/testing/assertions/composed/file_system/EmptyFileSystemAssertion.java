package io.serpentes.testing.assertions.composed.file_system;

import io.serpentes.testing.assertions.input.content.json.NoContent;
import io.serpentes.testing.assertions.input.file_system.FileSystemEmptyInputAssertion;
import io.serpentes.testing.assertions.definition.content.json_schema.EmptyJsonSchemaContent;
import io.serpentes.testing.assertions.definition.file_system.FileSystemEmptyTypeDefinitionAssertion;

public class EmptyFileSystemAssertion implements FileSystemAssertion {

    @Override
    public FileSystemEmptyTypeDefinitionAssertion getTypeDefinitionAssertion() {
        return new FileSystemEmptyTypeDefinitionAssertion(new EmptyJsonSchemaContent());
    }

    @Override
    public FileSystemEmptyInputAssertion getInputAssertion() {
        return new FileSystemEmptyInputAssertion(new NoContent());
    }
}
