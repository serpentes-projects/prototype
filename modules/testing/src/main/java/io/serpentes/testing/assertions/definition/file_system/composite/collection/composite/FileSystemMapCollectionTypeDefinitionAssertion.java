package io.serpentes.testing.assertions.definition.file_system.composite.collection.composite;

import io.serpentes.definition.sources.file_system.api.directories.DefinitionDirectory;
import io.serpentes.definition.sources.file_system.api.file_properties.DefinitionFileProperties;
import io.serpentes.testing.assertions.definition.file_system.FileSystemTypeDefinitionAssertion;
import io.serpentes.testing.assertions.definition.base.composite.collection.GenericBasedCollectionTypeDefinition;
import io.serpentes.testing.assertions.definition.base.composite.collection.composite.MapCollectionTypeDefinitionAssertion;
import io.serpentes.testing.assertions.definition.content.api.composite.CollectionDefinitionContent;
import io.serpentes.testing.cdi.extentions.definition.sources.file_system.file_path.CustomDefinitionDirectory;
import io.serpentes.testing.cdi.extentions.definition.sources.file_system.file_properties.CustomDefinitionFileProperties;
import io.serpentes.testing.pojos.ClassWithAllValueTypes;

import java.nio.file.Paths;
import java.util.Collection;

public class FileSystemMapCollectionTypeDefinitionAssertion extends MapCollectionTypeDefinitionAssertion implements GenericBasedCollectionTypeDefinition<ClassWithAllValueTypes>, FileSystemTypeDefinitionAssertion<Collection<ClassWithAllValueTypes>> {

    public FileSystemMapCollectionTypeDefinitionAssertion(CollectionDefinitionContent collectionDefinitionContent) {
        super(collectionDefinitionContent);
    }

    @Override
    public DefinitionDirectory getDefinitionDirectory() {
        return new CustomDefinitionDirectory(Paths.get("src", "test", "resources", "definitions", "json", "composite", "collection", "composite"));
    }

    @Override
    public DefinitionFileProperties getDefinitionFileProperties() {
        return new CustomDefinitionFileProperties("map.schema.json");
    }
}
