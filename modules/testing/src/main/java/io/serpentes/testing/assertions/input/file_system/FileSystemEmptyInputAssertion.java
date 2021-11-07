package io.serpentes.testing.assertions.input.file_system;

import io.serpentes.input.sources.file_system.api.directories.InputDirectory;
import io.serpentes.input.sources.file_system.api.file_properties.InputFileProperties;
import io.serpentes.testing.assertions.input.base.EmptyInputAssertion;
import io.serpentes.testing.assertions.input.content.json.NoContent;
import io.serpentes.testing.cdi.extentions.input.sources.file_system.file_path.CustomInputDirectory;
import io.serpentes.testing.cdi.extentions.input.sources.file_system.file_properties.CustomInputFileProperties;

import java.nio.file.Path;
import java.nio.file.Paths;

public class FileSystemEmptyInputAssertion extends EmptyInputAssertion implements FileSystemInputAssertion<Void> {
    public FileSystemEmptyInputAssertion(NoContent content) {
        super(content);
    }

    @Override
    public Path getInputPath() {
        return null;
    }

    @Override
    public InputDirectory getInputDirectory() {
        return new CustomInputDirectory(Paths.get("src", "test", "resources", "file_system", "json"));
    }

    @Override
    public InputFileProperties getInputFileProperties() {
        return new CustomInputFileProperties("empty.json");
    }
}
