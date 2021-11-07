package io.serpentes.testing.assertions.composed.file_system.composite.collection.primitives;

import io.serpentes.testing.assertions.composed.file_system.FileSystemAssertion;
import io.serpentes.testing.assertions.input.content.json.composite.collection.primitive.StringCollectionJsonContent;
import io.serpentes.testing.assertions.input.file_system.composite.collection.primitive.FileSystemStringCollectionInputAssertion;
import io.serpentes.testing.assertions.definition.content.json_schema.composite.collection.primitive.StringCollectionJsonSchemaContent;
import io.serpentes.testing.assertions.definition.file_system.composite.collection.primitive.FileSystemStringCollectionTypeDefinitionAssertion;

public class StringCollectionFileSystemAssertion implements FileSystemAssertion {
    @Override
    public FileSystemStringCollectionTypeDefinitionAssertion getTypeDefinitionAssertion() {
        return new FileSystemStringCollectionTypeDefinitionAssertion(new StringCollectionJsonSchemaContent());
    }

    @Override
    public FileSystemStringCollectionInputAssertion getInputAssertion() {
        return new FileSystemStringCollectionInputAssertion(new StringCollectionJsonContent());
    }
}
