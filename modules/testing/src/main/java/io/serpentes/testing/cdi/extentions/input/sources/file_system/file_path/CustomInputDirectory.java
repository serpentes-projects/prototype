package io.serpentes.testing.cdi.extentions.input.sources.file_system.file_path;


import io.serpentes.input.sources.file_system.api.directories.InputDirectory;

import java.nio.file.Path;

public class CustomInputDirectory implements InputDirectory {
    private final Path customPath;

    public CustomInputDirectory(final Path customPath) {
        this.customPath = customPath;
    }

    @Override
    public String getName() {
        return "custom";
    }

    @Override
    public Path getPath() {
        return customPath;
    }
}
