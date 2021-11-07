package io.serpentes.testing.assertions.input.file_system.composite.collection;


import io.serpentes.input.sources.file_system.api.directories.InputDirectory;
import io.serpentes.input.sources.file_system.api.file_properties.InputFileProperties;
import io.serpentes.testing.assertions.input.base.composite.collection.EmptyCollectionInputAssertion;
import io.serpentes.testing.assertions.input.content.json.composite.collection.EmptyCollectionJsonContent;
import io.serpentes.testing.cdi.extentions.input.sources.file_system.file_path.CustomInputDirectory;
import io.serpentes.testing.cdi.extentions.input.sources.file_system.file_properties.CustomInputFileProperties;

import java.nio.file.Path;
import java.nio.file.Paths;

public class FileSystemEmptyCollectionInputAssertion extends EmptyCollectionInputAssertion implements FileSystemGenericBasedCollectionInputAssertion<Object> {
    private EmptyCollectionJsonContent content;

    public FileSystemEmptyCollectionInputAssertion(EmptyCollectionJsonContent content) {
        super(content);
    }

    @Override
    public Path getInputPath() {
        return Paths.get("src", "test", "resources", "file_system", "json", "composite", "collection", "empty.json");
    }

    @Override
    public InputDirectory getInputDirectory() {
        return new CustomInputDirectory(Paths.get("src", "test", "resources", "file_system", "json", "composite", "collection"));
    }

    @Override
    public InputFileProperties getInputFileProperties() {
        return new CustomInputFileProperties("empty.json");
    }
}
