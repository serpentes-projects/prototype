package io.serpentes.testing.assertions.composed.file_system.composite.map;

import io.serpentes.testing.assertions.composed.file_system.FileSystemAssertion;
import io.serpentes.testing.assertions.input.content.json.composite.map.values.MapWithAllValueTypesJsonContent;
import io.serpentes.testing.assertions.input.file_system.composite.map.FileSystemMapWithAllValueTypesInputAssertion;
import io.serpentes.testing.assertions.definition.content.json_schema.composite.map.MapWithAllValueTypesJsonSchemaContent;
import io.serpentes.testing.assertions.definition.file_system.composite.map.FileSystemMapWithAllValueTypesTypeDefinitionAssertion;

public class MapWithAllValueTypesFileSystemAssertion implements FileSystemAssertion {
    @Override
    public FileSystemMapWithAllValueTypesTypeDefinitionAssertion getTypeDefinitionAssertion() {
        return new FileSystemMapWithAllValueTypesTypeDefinitionAssertion(new MapWithAllValueTypesJsonSchemaContent());
    }

    @Override
    public FileSystemMapWithAllValueTypesInputAssertion getInputAssertion() {
        return new FileSystemMapWithAllValueTypesInputAssertion(new MapWithAllValueTypesJsonContent());
    }
}
