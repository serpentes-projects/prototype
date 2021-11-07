package io.serpentes.testing.assertions.definition.file_system;

import io.serpentes.definition.sources.file_system.api.directories.DefinitionDirectory;
import io.serpentes.definition.sources.file_system.api.file_properties.DefinitionFileProperties;
import io.serpentes.testing.assertions.definition.base.EmptyTypeDefinitionAssertion;
import io.serpentes.testing.assertions.definition.content.api.DefinitionContent;
import io.serpentes.testing.cdi.extentions.definition.sources.file_system.file_path.CustomDefinitionDirectory;
import io.serpentes.testing.cdi.extentions.definition.sources.file_system.file_properties.CustomDefinitionFileProperties;

import java.nio.file.Paths;

public class FileSystemEmptyTypeDefinitionAssertion extends EmptyTypeDefinitionAssertion implements FileSystemTypeDefinitionAssertion<Object> {
    public FileSystemEmptyTypeDefinitionAssertion(DefinitionContent definitionContent) {
        super(definitionContent);
    }

    @Override
    public DefinitionDirectory getDefinitionDirectory() {
        return new CustomDefinitionDirectory(Paths.get("src", "test", "resources", "definitions", "json"));
    }

    @Override
    public DefinitionFileProperties getDefinitionFileProperties() {
        // TODO: Rethink this, it implies a dependency on json schemas.
        return new CustomDefinitionFileProperties("empty.schema.json");
    }
}
