package io.serpentes.testing.assertions.input.file_system.primitive;

import io.serpentes.input.sources.file_system.api.directories.InputDirectory;
import io.serpentes.input.sources.file_system.api.file_properties.InputFileProperties;
import io.serpentes.testing.assertions.input.base.primitive.BooleanInputAssertion;
import io.serpentes.testing.assertions.input.content.api.primitive.BooleanInputContent;
import io.serpentes.testing.assertions.input.file_system.FileSystemInputAssertion;
import io.serpentes.testing.cdi.extentions.input.sources.file_system.file_path.CustomInputDirectory;
import io.serpentes.testing.cdi.extentions.input.sources.file_system.file_properties.CustomInputFileProperties;

import java.nio.file.Path;
import java.nio.file.Paths;

public class FileSystemBooleanInputAssertion extends BooleanInputAssertion implements FileSystemInputAssertion<Boolean> {
    public FileSystemBooleanInputAssertion(BooleanInputContent content) {
        super(content);
    }

    @Override
    public Path getInputPath() {
        return Paths.get("src", "test", "resources", "file_system", "json", "primitive", "boolean.json");
    }

    @Override
    public InputDirectory getInputDirectory() {
        return new CustomInputDirectory(Paths.get("src", "test", "resources", "file_system", "json", "primitive"));
    }

    @Override
    public InputFileProperties getInputFileProperties() {
        return new CustomInputFileProperties("boolean.json");
    }
}
