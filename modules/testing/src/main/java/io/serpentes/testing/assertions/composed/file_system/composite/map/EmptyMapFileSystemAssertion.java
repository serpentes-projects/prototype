package io.serpentes.testing.assertions.composed.file_system.composite.map;

import io.serpentes.testing.assertions.composed.file_system.FileSystemAssertion;
import io.serpentes.testing.assertions.input.content.json.composite.map.EmptyMapJsonContent;
import io.serpentes.testing.assertions.input.file_system.composite.map.FileSystemEmptyMapInputAssertion;
import io.serpentes.testing.assertions.definition.content.json_schema.composite.map.EmptyMapJsonSchemaContent;
import io.serpentes.testing.assertions.definition.file_system.composite.map.FileSystemEmptyMapTypeDefinitionAssertion;

public class EmptyMapFileSystemAssertion implements FileSystemAssertion {
    @Override
    public FileSystemEmptyMapTypeDefinitionAssertion getTypeDefinitionAssertion() {
        return new FileSystemEmptyMapTypeDefinitionAssertion(new EmptyMapJsonSchemaContent());
    }

    @Override
    public FileSystemEmptyMapInputAssertion getInputAssertion() {
        return new FileSystemEmptyMapInputAssertion(new EmptyMapJsonContent());
    }
}
