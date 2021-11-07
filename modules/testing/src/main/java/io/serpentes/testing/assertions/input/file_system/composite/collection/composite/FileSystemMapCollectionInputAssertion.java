package io.serpentes.testing.assertions.input.file_system.composite.collection.composite;

import io.serpentes.input.sources.file_system.api.directories.InputDirectory;
import io.serpentes.input.sources.file_system.api.file_properties.InputFileProperties;
import io.serpentes.testing.assertions.input.base.composite.collection.composite.MapCollectionInputAssertion;
import io.serpentes.testing.assertions.input.content.api.composite.CollectionContent;
import io.serpentes.testing.assertions.input.file_system.FileSystemInputAssertion;
import io.serpentes.testing.assertions.input.file_system.composite.collection.FileSystemGenericBasedCollectionInputAssertion;
import io.serpentes.testing.cdi.extentions.input.sources.file_system.file_path.CustomInputDirectory;
import io.serpentes.testing.cdi.extentions.input.sources.file_system.file_properties.CustomInputFileProperties;
import io.serpentes.testing.pojos.ClassWithAllValueTypes;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;

public class FileSystemMapCollectionInputAssertion extends MapCollectionInputAssertion implements FileSystemGenericBasedCollectionInputAssertion<ClassWithAllValueTypes>, FileSystemInputAssertion<Collection<ClassWithAllValueTypes>> {
    public FileSystemMapCollectionInputAssertion(CollectionContent<ClassWithAllValueTypes> content) {
        super(content);
    }

    @Override
    public Path getInputPath() {
        return Paths.get("src", "test", "resources", "file_system", "json", "composite", "collection", "composite", "map.json");
    }

    @Override
    public InputDirectory getInputDirectory() {
        return new CustomInputDirectory(Paths.get("src", "test", "resources", "file_system", "json", "composite", "collection", "composite"));
    }

    @Override
    public InputFileProperties getInputFileProperties() {
        return new CustomInputFileProperties("map.json");
    }
}
