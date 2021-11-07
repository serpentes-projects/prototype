package io.serpentes.testing.assertions.composed.file_system.primitives;

import io.serpentes.testing.assertions.composed.file_system.FileSystemAssertion;
import io.serpentes.testing.assertions.input.content.json.primitive.NullJsonContent;
import io.serpentes.testing.assertions.input.file_system.primitive.FileSystemNullInputAssertion;
import io.serpentes.testing.assertions.definition.content.json_schema.primitive.NullJsonSchemaContent;
import io.serpentes.testing.assertions.definition.file_system.primitive.FileSystemNullTypeDefinitionAssertion;

public class NullFileSystemAssertion implements FileSystemAssertion {
    @Override
    public FileSystemNullTypeDefinitionAssertion getTypeDefinitionAssertion() {
        return new FileSystemNullTypeDefinitionAssertion(new NullJsonSchemaContent());
    }

    @Override
    public FileSystemNullInputAssertion getInputAssertion() {
        return new FileSystemNullInputAssertion(new NullJsonContent());
    }
}
