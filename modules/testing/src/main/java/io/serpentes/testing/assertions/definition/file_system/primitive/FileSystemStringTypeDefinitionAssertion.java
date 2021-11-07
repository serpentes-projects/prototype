package io.serpentes.testing.assertions.definition.file_system.primitive;

import io.serpentes.definition.sources.file_system.api.directories.DefinitionDirectory;
import io.serpentes.definition.sources.file_system.api.file_properties.DefinitionFileProperties;
import io.serpentes.testing.assertions.definition.base.primitive.StringTypeDefinitionAssertion;
import io.serpentes.testing.assertions.definition.content.api.primitive.StringDefinitionContent;
import io.serpentes.testing.assertions.definition.file_system.FileSystemTypeDefinitionAssertion;
import io.serpentes.testing.cdi.extentions.definition.sources.file_system.file_path.CustomDefinitionDirectory;
import io.serpentes.testing.cdi.extentions.definition.sources.file_system.file_properties.CustomDefinitionFileProperties;

import java.nio.file.Paths;

public class FileSystemStringTypeDefinitionAssertion extends StringTypeDefinitionAssertion implements FileSystemTypeDefinitionAssertion<String> {

    public FileSystemStringTypeDefinitionAssertion(final StringDefinitionContent stringDefinitionContent) {
        super(stringDefinitionContent);
    }

    @Override
    public DefinitionDirectory getDefinitionDirectory() {
        return new CustomDefinitionDirectory(Paths.get("src", "test", "resources", "definitions", "json", "primitive"));
    }

    @Override
    public DefinitionFileProperties getDefinitionFileProperties() {
        return new CustomDefinitionFileProperties("string.schema.json");
    }
}
