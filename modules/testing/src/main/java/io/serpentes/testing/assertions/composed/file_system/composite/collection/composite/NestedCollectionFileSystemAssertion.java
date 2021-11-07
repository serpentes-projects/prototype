package io.serpentes.testing.assertions.composed.file_system.composite.collection.composite;

import io.serpentes.testing.assertions.composed.file_system.FileSystemAssertion;
import io.serpentes.testing.assertions.input.content.json.composite.collection.composite.NestedCollectionJsonContent;
import io.serpentes.testing.assertions.input.file_system.composite.collection.composite.FileSystemNestedCollectionInputAssertion;
import io.serpentes.testing.assertions.definition.content.json_schema.composite.collection.composite.NestedCollectionJsonSchemaContent;
import io.serpentes.testing.assertions.definition.file_system.composite.collection.composite.FileSystemNestedCollectionTypeDefinitionAssertion;

public class NestedCollectionFileSystemAssertion implements FileSystemAssertion {
    @Override
    public FileSystemNestedCollectionTypeDefinitionAssertion getTypeDefinitionAssertion() {
        return new FileSystemNestedCollectionTypeDefinitionAssertion(new NestedCollectionJsonSchemaContent());
    }

    @Override
    public FileSystemNestedCollectionInputAssertion getInputAssertion() {
        return new FileSystemNestedCollectionInputAssertion(new NestedCollectionJsonContent());
    }
}
