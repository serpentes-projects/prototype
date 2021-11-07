package io.serpentes.testing.assertions.definition.file_system.primitive;

import io.serpentes.definition.sources.file_system.api.directories.DefinitionDirectory;
import io.serpentes.definition.sources.file_system.api.file_properties.DefinitionFileProperties;
import io.serpentes.testing.assertions.definition.file_system.FileSystemTypeDefinitionAssertion;
import io.serpentes.testing.assertions.definition.base.primitive.NullTypeDefinitionAssertion;
import io.serpentes.testing.assertions.definition.content.api.primitive.NullDefinitionContent;
import io.serpentes.testing.cdi.extentions.definition.sources.file_system.file_path.CustomDefinitionDirectory;
import io.serpentes.testing.cdi.extentions.definition.sources.file_system.file_properties.CustomDefinitionFileProperties;

import java.nio.file.Paths;

public class FileSystemNullTypeDefinitionAssertion extends NullTypeDefinitionAssertion implements FileSystemTypeDefinitionAssertion<Object> {

    public FileSystemNullTypeDefinitionAssertion(final NullDefinitionContent nullDefinitionContent) {
        super(nullDefinitionContent);
    }

    @Override
    public DefinitionDirectory getDefinitionDirectory() {
        return new CustomDefinitionDirectory(Paths.get("src", "test", "resources", "definitions", "json", "primitive"));
    }

    @Override
    public DefinitionFileProperties getDefinitionFileProperties() {
        return new CustomDefinitionFileProperties("null.schema.json");
    }
}
