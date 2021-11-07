package io.serpentes.boot.se.definition.sources.file_system.file_paths;



import io.serpentes.definition.sources.file_system.api.directories.RelativeDefinitionDirectory;

import java.nio.file.Path;
import java.nio.file.Paths;

@io.serpentes.definition.sources.file_system.annotations.directories.DefaultDefinitionDirectory
public class DefaultDefinitionDirectory implements RelativeDefinitionDirectory {
    public static final String NAME = "default-relative-windows-input-directory";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public Path getPath() {
        return Paths.get("src", "main", "resources", "configuration", "definitions");
    }
}
