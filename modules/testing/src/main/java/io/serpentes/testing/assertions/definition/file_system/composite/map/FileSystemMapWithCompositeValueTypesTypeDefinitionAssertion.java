package io.serpentes.testing.assertions.definition.file_system.composite.map;

import io.serpentes.definition.sources.file_system.api.directories.DefinitionDirectory;
import io.serpentes.definition.sources.file_system.api.file_properties.DefinitionFileProperties;
import io.serpentes.testing.assertions.definition.base.composite.map.MapWithCompositeValueTypesTypeDefinitionAssertion;
import io.serpentes.testing.assertions.definition.content.api.composite.MapDefinitionContent;
import io.serpentes.testing.assertions.definition.file_system.FileSystemTypeDefinitionAssertion;
import io.serpentes.testing.cdi.extentions.definition.sources.file_system.file_path.CustomDefinitionDirectory;
import io.serpentes.testing.cdi.extentions.definition.sources.file_system.file_properties.CustomDefinitionFileProperties;
import io.serpentes.testing.pojos.ClassWithCompositeValueTypes;

import java.nio.file.Paths;

public class FileSystemMapWithCompositeValueTypesTypeDefinitionAssertion extends MapWithCompositeValueTypesTypeDefinitionAssertion implements FileSystemTypeDefinitionAssertion<ClassWithCompositeValueTypes> {

    public FileSystemMapWithCompositeValueTypesTypeDefinitionAssertion(final MapDefinitionContent mapDefinitionContent) {
        super(mapDefinitionContent);
    }

    @Override
    public DefinitionDirectory getDefinitionDirectory() {
        return new CustomDefinitionDirectory(Paths.get("src", "test", "resources", "definitions", "json", "composite", "map", "properties"));
    }

    @Override
    public DefinitionFileProperties getDefinitionFileProperties() {
        return new CustomDefinitionFileProperties("composite.schema.json");
    }

}
