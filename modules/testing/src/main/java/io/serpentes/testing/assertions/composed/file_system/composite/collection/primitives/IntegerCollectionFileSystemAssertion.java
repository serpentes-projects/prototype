package io.serpentes.testing.assertions.composed.file_system.composite.collection.primitives;

import io.serpentes.testing.assertions.composed.file_system.FileSystemAssertion;
import io.serpentes.testing.assertions.input.content.json.composite.collection.primitive.IntegerCollectionJsonContent;
import io.serpentes.testing.assertions.input.file_system.composite.collection.primitive.FileSystemIntegerCollectionInputAssertion;
import io.serpentes.testing.assertions.definition.content.json_schema.composite.collection.primitive.IntegerCollectionJsonSchemaContent;
import io.serpentes.testing.assertions.definition.file_system.composite.collection.primitive.FileSystemIntegerCollectionTypeDefinitionAssertion;

public class IntegerCollectionFileSystemAssertion implements FileSystemAssertion {
    @Override
    public FileSystemIntegerCollectionTypeDefinitionAssertion getTypeDefinitionAssertion() {
        return new FileSystemIntegerCollectionTypeDefinitionAssertion(new IntegerCollectionJsonSchemaContent());
    }

    @Override
    public FileSystemIntegerCollectionInputAssertion getInputAssertion() {
        return new FileSystemIntegerCollectionInputAssertion(new IntegerCollectionJsonContent());
    }
}
