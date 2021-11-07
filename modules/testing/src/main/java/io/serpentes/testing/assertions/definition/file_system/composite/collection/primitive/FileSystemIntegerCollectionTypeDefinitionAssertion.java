package io.serpentes.testing.assertions.definition.file_system.composite.collection.primitive;

import io.serpentes.definition.sources.file_system.api.directories.DefinitionDirectory;
import io.serpentes.definition.sources.file_system.api.file_properties.DefinitionFileProperties;
import io.serpentes.testing.assertions.definition.file_system.composite.collection.FileSystemGenericBasedCollectionTypeDefinitionAssertion;
import io.serpentes.testing.assertions.definition.base.composite.collection.primitive.IntegerCollectionTypeDefinitionAssertion;
import io.serpentes.testing.assertions.definition.content.api.composite.CollectionDefinitionContent;
import io.serpentes.testing.cdi.extentions.definition.sources.file_system.file_path.CustomDefinitionDirectory;
import io.serpentes.testing.cdi.extentions.definition.sources.file_system.file_properties.CustomDefinitionFileProperties;

import java.nio.file.Paths;

public class FileSystemIntegerCollectionTypeDefinitionAssertion extends IntegerCollectionTypeDefinitionAssertion implements FileSystemGenericBasedCollectionTypeDefinitionAssertion<Integer> {

    public FileSystemIntegerCollectionTypeDefinitionAssertion(CollectionDefinitionContent collectionDefinitionContent) {
        super(collectionDefinitionContent);
    }

    @Override
    public DefinitionDirectory getDefinitionDirectory() {
        return new CustomDefinitionDirectory(Paths.get("src", "test", "resources", "definitions", "json", "composite", "collection", "primitive"));
    }

    @Override
    public DefinitionFileProperties getDefinitionFileProperties() {
        return new CustomDefinitionFileProperties("integer.schema.json");
    }
}
