package io.serpentes.testing.assertions.input.file_system.composite.map;

import io.serpentes.input.sources.file_system.api.directories.InputDirectory;
import io.serpentes.input.sources.file_system.api.file_properties.InputFileProperties;
import io.serpentes.testing.assertions.input.base.composite.map.MapWithAllValueTypesInputAssertion;
import io.serpentes.testing.assertions.input.content.api.composite.MapContent;
import io.serpentes.testing.assertions.input.file_system.FileSystemInputAssertion;
import io.serpentes.testing.cdi.extentions.input.sources.file_system.file_path.CustomInputDirectory;
import io.serpentes.testing.cdi.extentions.input.sources.file_system.file_properties.CustomInputFileProperties;
import io.serpentes.testing.pojos.ClassWithAllValueTypes;

import java.nio.file.Path;
import java.nio.file.Paths;

public class FileSystemMapWithAllValueTypesInputAssertion extends MapWithAllValueTypesInputAssertion implements FileSystemInputAssertion<ClassWithAllValueTypes> {
    public FileSystemMapWithAllValueTypesInputAssertion(MapContent<ClassWithAllValueTypes> content) {
        super(content);
    }

    @Override
    public Path getInputPath() {
        return Paths.get("src", "test", "resources", "file_system", "json", "composite", "map", "properties", "all.json");
    }

    @Override
    public InputDirectory getInputDirectory() {
        return new CustomInputDirectory(Paths.get("src", "test", "resources", "file_system", "json", "composite", "map", "properties"));
    }

    @Override
    public InputFileProperties getInputFileProperties() {
        return new CustomInputFileProperties("all.json");
    }
}
