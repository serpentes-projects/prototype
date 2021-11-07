package io.serpentes.testing.assertions.input.file_system.primitive;

import io.serpentes.input.sources.file_system.api.directories.InputDirectory;
import io.serpentes.input.sources.file_system.api.file_properties.InputFileProperties;
import io.serpentes.testing.assertions.input.base.primitive.NullInputAssertion;
import io.serpentes.testing.assertions.input.content.api.primitive.NullInputContent;
import io.serpentes.testing.assertions.input.file_system.FileSystemInputAssertion;
import io.serpentes.testing.cdi.extentions.input.sources.file_system.file_path.CustomInputDirectory;
import io.serpentes.testing.cdi.extentions.input.sources.file_system.file_properties.CustomInputFileProperties;

import java.nio.file.Path;
import java.nio.file.Paths;

public class FileSystemNullInputAssertion extends NullInputAssertion implements FileSystemInputAssertion<Object> {
    public FileSystemNullInputAssertion(NullInputContent content) {
        super(content);
    }

    @Override
    public Path getInputPath() {
        return Paths.get("src", "test", "resources", "file_system", "json", "primitive", "null.json");
    }

    @Override
    public InputDirectory getInputDirectory() {
        return new CustomInputDirectory(Paths.get("src", "test", "resources", "file_system", "json", "primitive"));
    }

    @Override
    public InputFileProperties getInputFileProperties() {
        return new CustomInputFileProperties("null.json");
    }
}
