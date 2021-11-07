package io.serpentes.testing.assertions.composed.file_system.composite.collection.primitives;

import io.serpentes.testing.assertions.composed.file_system.FileSystemAssertion;
import io.serpentes.testing.assertions.input.content.json.composite.collection.primitive.FloatingPointCollectionJsonContent;
import io.serpentes.testing.assertions.input.file_system.composite.collection.primitive.FileSystemFloatingPointCollectionInputAssertion;
import io.serpentes.testing.assertions.definition.content.json_schema.composite.collection.primitive.FloatingPointCollectionJsonSchemaContent;
import io.serpentes.testing.assertions.definition.file_system.composite.collection.primitive.FileSystemFloatingPointCollectionTypeDefinitionAssertion;

public class FloatingPointCollectionFileSystemAssertion implements FileSystemAssertion {
    @Override
    public FileSystemFloatingPointCollectionTypeDefinitionAssertion getTypeDefinitionAssertion() {
        return new FileSystemFloatingPointCollectionTypeDefinitionAssertion(new FloatingPointCollectionJsonSchemaContent());
    }

    @Override
    public FileSystemFloatingPointCollectionInputAssertion getInputAssertion() {
        return new FileSystemFloatingPointCollectionInputAssertion(new FloatingPointCollectionJsonContent());
    }
}
