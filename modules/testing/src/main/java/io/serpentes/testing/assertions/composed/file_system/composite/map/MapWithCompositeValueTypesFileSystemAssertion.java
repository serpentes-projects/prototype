package io.serpentes.testing.assertions.composed.file_system.composite.map;

import io.serpentes.testing.assertions.composed.file_system.FileSystemAssertion;
import io.serpentes.testing.assertions.input.content.json.composite.map.values.MapWithCompositeValueTypesJsonContent;
import io.serpentes.testing.assertions.input.file_system.composite.map.FileSystemMapWithCompositeValueTypesInputAssertion;
import io.serpentes.testing.assertions.definition.content.json_schema.composite.map.MapWithCompositeValueTypesJsonSchemaContent;
import io.serpentes.testing.assertions.definition.file_system.composite.map.FileSystemMapWithCompositeValueTypesTypeDefinitionAssertion;

public class MapWithCompositeValueTypesFileSystemAssertion implements FileSystemAssertion {
    @Override
    public FileSystemMapWithCompositeValueTypesTypeDefinitionAssertion getTypeDefinitionAssertion() {
        return new FileSystemMapWithCompositeValueTypesTypeDefinitionAssertion(new MapWithCompositeValueTypesJsonSchemaContent());
    }

    @Override
    public FileSystemMapWithCompositeValueTypesInputAssertion getInputAssertion() {
        return new FileSystemMapWithCompositeValueTypesInputAssertion(new MapWithCompositeValueTypesJsonContent());
    }
}
