package io.serpentes.testing.assertions.definition.file_system.composite.map;

import io.serpentes.definition.sources.file_system.api.directories.DefinitionDirectory;
import io.serpentes.definition.sources.file_system.api.file_properties.DefinitionFileProperties;
import io.serpentes.testing.assertions.definition.base.composite.map.EmptyMapTypeDefinitionAssertion;
import io.serpentes.testing.assertions.definition.content.api.composite.MapDefinitionContent;
import io.serpentes.testing.assertions.definition.file_system.FileSystemTypeDefinitionAssertion;
import io.serpentes.testing.cdi.extentions.definition.sources.file_system.file_path.CustomDefinitionDirectory;
import io.serpentes.testing.cdi.extentions.definition.sources.file_system.file_properties.CustomDefinitionFileProperties;
import io.serpentes.testing.pojos.ClassWithAllValueTypes;

import java.nio.file.Paths;

public class FileSystemEmptyMapTypeDefinitionAssertion extends EmptyMapTypeDefinitionAssertion implements FileSystemTypeDefinitionAssertion<ClassWithAllValueTypes> {

    public FileSystemEmptyMapTypeDefinitionAssertion(final MapDefinitionContent mapDefinitionContent) {
        super(mapDefinitionContent);
    }

    @Override
    public DefinitionDirectory getDefinitionDirectory() {
        return new CustomDefinitionDirectory(Paths.get("src", "test", "resources", "definitions", "json", "composite", "map"));
    }

    @Override
    public DefinitionFileProperties getDefinitionFileProperties() {
        return new CustomDefinitionFileProperties("empty.schema.json");
    }
}
