package io.serpentes.testing.assertions.input.file_system.composite.collection.primitive;

import io.serpentes.input.sources.file_system.api.directories.InputDirectory;
import io.serpentes.input.sources.file_system.api.file_properties.InputFileProperties;
import io.serpentes.testing.assertions.input.base.composite.collection.primitive.IntegerCollectionInputAssertion;
import io.serpentes.testing.assertions.input.content.api.composite.CollectionContent;
import io.serpentes.testing.assertions.input.file_system.composite.collection.FileSystemGenericBasedCollectionInputAssertion;
import io.serpentes.testing.cdi.extentions.input.sources.file_system.file_path.CustomInputDirectory;
import io.serpentes.testing.cdi.extentions.input.sources.file_system.file_properties.CustomInputFileProperties;

import java.nio.file.Path;
import java.nio.file.Paths;

public class FileSystemIntegerCollectionInputAssertion extends IntegerCollectionInputAssertion implements FileSystemGenericBasedCollectionInputAssertion<Integer> {

    public FileSystemIntegerCollectionInputAssertion(CollectionContent<Integer> content) {
        super(content);
    }

    @Override
    public Path getInputPath() {
        return Paths.get("src", "test", "resources", "file_system", "json", "composite", "collection", "primitive", "integer.json");
    }

    @Override
    public InputDirectory getInputDirectory() {
        return new CustomInputDirectory(Paths.get("src", "test", "resources", "file_system", "json", "composite", "collection", "primitive"));
    }

    @Override
    public InputFileProperties getInputFileProperties() {
        return new CustomInputFileProperties("integer.json");
    }
}