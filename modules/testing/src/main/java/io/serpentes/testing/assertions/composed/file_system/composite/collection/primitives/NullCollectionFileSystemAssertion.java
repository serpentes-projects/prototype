package io.serpentes.testing.assertions.composed.file_system.composite.collection.primitives;

import io.serpentes.testing.assertions.composed.file_system.FileSystemAssertion;
import io.serpentes.testing.assertions.input.content.json.composite.collection.primitive.NullCollectionJsonContent;
import io.serpentes.testing.assertions.input.file_system.composite.collection.primitive.FileSystemNullCollectionInputAssertion;
import io.serpentes.testing.assertions.definition.content.json_schema.composite.collection.primitive.NullCollectionJsonSchemaContent;
import io.serpentes.testing.assertions.definition.file_system.composite.collection.primitive.FileSystemNullCollectionTypeDefinitionAssertion;

public class NullCollectionFileSystemAssertion implements FileSystemAssertion {
    @Override
    public FileSystemNullCollectionTypeDefinitionAssertion getTypeDefinitionAssertion() {
        return new FileSystemNullCollectionTypeDefinitionAssertion(new NullCollectionJsonSchemaContent());
    }

    @Override
    public FileSystemNullCollectionInputAssertion getInputAssertion() {
        return new FileSystemNullCollectionInputAssertion(new NullCollectionJsonContent());
    }
}
