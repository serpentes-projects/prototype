package io.serpentes.testing.assertions.definition.file_system.composite.collection.primitive;

import io.serpentes.definition.sources.file_system.api.directories.DefinitionDirectory;
import io.serpentes.definition.sources.file_system.api.file_properties.DefinitionFileProperties;
import io.serpentes.testing.assertions.definition.file_system.composite.collection.FileSystemGenericBasedCollectionTypeDefinitionAssertion;
import io.serpentes.testing.assertions.definition.base.composite.collection.primitive.StringCollectionTypeDefinitionAssertion;
import io.serpentes.testing.assertions.definition.content.api.composite.CollectionDefinitionContent;
import io.serpentes.testing.cdi.extentions.definition.sources.file_system.file_path.CustomDefinitionDirectory;
import io.serpentes.testing.cdi.extentions.definition.sources.file_system.file_properties.CustomDefinitionFileProperties;

import java.nio.file.Paths;

public class FileSystemStringCollectionTypeDefinitionAssertion extends StringCollectionTypeDefinitionAssertion implements FileSystemGenericBasedCollectionTypeDefinitionAssertion<String> {

    public FileSystemStringCollectionTypeDefinitionAssertion(CollectionDefinitionContent collectionDefinitionContent) {
        super(collectionDefinitionContent);
    }

    @Override
    public DefinitionDirectory getDefinitionDirectory() {
        return new CustomDefinitionDirectory(Paths.get("src", "test", "resources", "definitions", "json", "composite", "collection", "primitive"));
    }

    @Override
    public DefinitionFileProperties getDefinitionFileProperties() {
        return new CustomDefinitionFileProperties("string.schema.json");
    }
}
