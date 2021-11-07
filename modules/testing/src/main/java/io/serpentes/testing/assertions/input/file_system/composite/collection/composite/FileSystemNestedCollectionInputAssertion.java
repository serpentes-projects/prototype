package io.serpentes.testing.assertions.input.file_system.composite.collection.composite;

import io.serpentes.input.sources.file_system.api.directories.InputDirectory;
import io.serpentes.input.sources.file_system.api.file_properties.InputFileProperties;
import io.serpentes.testing.assertions.input.base.composite.collection.composite.NestedCollectionInputAssertion;
import io.serpentes.testing.assertions.input.content.api.composite.CollectionContent;
import io.serpentes.testing.assertions.input.file_system.composite.collection.FileSystemGenericBasedCollectionInputAssertion;
import io.serpentes.testing.cdi.extentions.input.sources.file_system.file_path.CustomInputDirectory;
import io.serpentes.testing.cdi.extentions.input.sources.file_system.file_properties.CustomInputFileProperties;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;

public class FileSystemNestedCollectionInputAssertion extends NestedCollectionInputAssertion implements FileSystemGenericBasedCollectionInputAssertion<Collection<?>> {

    public FileSystemNestedCollectionInputAssertion(CollectionContent<Collection<?>> content) {
        super(content);
    }

    @Override
    public Path getInputPath() {
        return Paths.get("src", "test", "resources", "file_system", "json", "composite", "collection", "composite", "collection.json");
    }

    @Override
    public InputDirectory getInputDirectory() {
        return new CustomInputDirectory(Paths.get("src", "test", "resources", "file_system", "json", "composite", "collection", "composite"));
    }

    @Override
    public InputFileProperties getInputFileProperties() {
        return new CustomInputFileProperties("collection.json");
    }

}
