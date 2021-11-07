package io.serpentes.testing.assertions.composed.file_system.composite.collection;

import io.serpentes.testing.assertions.composed.file_system.FileSystemAssertion;
import io.serpentes.testing.assertions.input.content.json.composite.collection.EmptyCollectionJsonContent;
import io.serpentes.testing.assertions.input.file_system.composite.collection.FileSystemEmptyCollectionInputAssertion;
import io.serpentes.testing.assertions.definition.content.json_schema.composite.collection.EmptyCollectionJsonSchemaContent;
import io.serpentes.testing.assertions.definition.file_system.composite.collection.FileSystemEmptyCollectionTypeDefinitionAssertion;

public class EmptyCollectionFileSystemAssertion implements FileSystemAssertion {
    @Override
    public FileSystemEmptyCollectionTypeDefinitionAssertion getTypeDefinitionAssertion() {
        return new FileSystemEmptyCollectionTypeDefinitionAssertion(new EmptyCollectionJsonSchemaContent());
    }

    @Override
    public FileSystemEmptyCollectionInputAssertion getInputAssertion() {
        return new FileSystemEmptyCollectionInputAssertion(new EmptyCollectionJsonContent());
    }
}
