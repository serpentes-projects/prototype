package io.serpentes.testing.cdi.extentions.definition.sources.file_system.file_path;


import io.serpentes.definition.sources.file_system.api.directories.DefinitionDirectory;

import java.nio.file.Path;

public class CustomDefinitionDirectory implements DefinitionDirectory {
    private final Path definitionDirectory;

    public CustomDefinitionDirectory(final Path definitionDirectory) {
        this.definitionDirectory = definitionDirectory;
    }

    @Override
    public String getName() {
        return "custom";
    }

    @Override
    public Path getPath() {
        return definitionDirectory;
    }
}
