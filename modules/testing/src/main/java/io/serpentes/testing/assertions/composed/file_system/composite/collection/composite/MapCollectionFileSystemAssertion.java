package io.serpentes.testing.assertions.composed.file_system.composite.collection.composite;

import io.serpentes.testing.assertions.composed.file_system.FileSystemAssertion;
import io.serpentes.testing.assertions.input.content.json.composite.collection.composite.MapCollectionJsonContent;
import io.serpentes.testing.assertions.input.file_system.composite.collection.composite.FileSystemMapCollectionInputAssertion;
import io.serpentes.testing.assertions.definition.content.json_schema.composite.collection.composite.MapCollectionJsonSchemaContent;
import io.serpentes.testing.assertions.definition.file_system.composite.collection.composite.FileSystemMapCollectionTypeDefinitionAssertion;

public class MapCollectionFileSystemAssertion implements FileSystemAssertion {
    @Override
    public FileSystemMapCollectionTypeDefinitionAssertion getTypeDefinitionAssertion() {
        return new FileSystemMapCollectionTypeDefinitionAssertion(new MapCollectionJsonSchemaContent());
    }

    @Override
    public FileSystemMapCollectionInputAssertion getInputAssertion() {
        return new FileSystemMapCollectionInputAssertion(new MapCollectionJsonContent());
    }
}
