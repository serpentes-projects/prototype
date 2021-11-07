package io.serpentes.testing.assertions.composed.file_system.composite.map;


import io.serpentes.testing.assertions.composed.file_system.FileSystemAssertion;
import io.serpentes.testing.assertions.input.content.json.composite.map.values.MapWithPrimitiveValueTypesJsonContent;
import io.serpentes.testing.assertions.input.file_system.composite.map.FileSystemMapWithPrimitiveValueTypesInputAssertion;
import io.serpentes.testing.assertions.definition.content.json_schema.composite.map.MapWithPrimitiveValueTypesJsonSchemaContent;
import io.serpentes.testing.assertions.definition.file_system.composite.map.FileSystemMapWithPrimitiveValueTypesTypeDefinitionAssertion;

public class MapWithPrimitiveValueTypesFileSystemAssertion implements FileSystemAssertion {
    @Override
    public FileSystemMapWithPrimitiveValueTypesTypeDefinitionAssertion getTypeDefinitionAssertion() {
        return new FileSystemMapWithPrimitiveValueTypesTypeDefinitionAssertion(new MapWithPrimitiveValueTypesJsonSchemaContent());
    }

    @Override
    public FileSystemMapWithPrimitiveValueTypesInputAssertion getInputAssertion() {
        return new FileSystemMapWithPrimitiveValueTypesInputAssertion(new MapWithPrimitiveValueTypesJsonContent());
    }
}
