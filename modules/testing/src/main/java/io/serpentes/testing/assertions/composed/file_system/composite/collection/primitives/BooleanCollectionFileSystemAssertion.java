package io.serpentes.testing.assertions.composed.file_system.composite.collection.primitives;

import io.serpentes.testing.assertions.composed.file_system.FileSystemAssertion;
import io.serpentes.testing.assertions.input.content.json.composite.collection.primitive.BooleanCollectionJsonContent;
import io.serpentes.testing.assertions.input.file_system.composite.collection.primitive.FileSystemBooleanCollectionInputAssertion;
import io.serpentes.testing.assertions.definition.content.json_schema.composite.collection.primitive.BooleanCollectionJsonSchemaContent;
import io.serpentes.testing.assertions.definition.file_system.composite.collection.primitive.FileSystemBooleanCollectionTypeDefinitionAssertion;

public class BooleanCollectionFileSystemAssertion implements FileSystemAssertion {
    @Override
    public FileSystemBooleanCollectionTypeDefinitionAssertion getTypeDefinitionAssertion() {
        return new FileSystemBooleanCollectionTypeDefinitionAssertion(new BooleanCollectionJsonSchemaContent());
    }

    @Override
    public FileSystemBooleanCollectionInputAssertion getInputAssertion() {
        return new FileSystemBooleanCollectionInputAssertion(new BooleanCollectionJsonContent());
    }
}
