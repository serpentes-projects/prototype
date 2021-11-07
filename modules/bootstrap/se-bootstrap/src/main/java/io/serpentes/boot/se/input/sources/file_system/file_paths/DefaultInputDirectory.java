package io.serpentes.boot.se.input.sources.file_system.file_paths;


import io.serpentes.input.sources.file_system.api.directories.RelativeInputDirectory;

import java.nio.file.Path;
import java.nio.file.Paths;

@io.serpentes.input.sources.file_system.annotations.directories.DefaultInputDirectory
public class DefaultInputDirectory implements RelativeInputDirectory {
    public static final String NAME = "default-relative-windows-input-directory";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public Path getPath() {
        //TODO: Change this to a path that makes more sense.
        return Paths.get("src", "main", "resources", "configuration", "file_system").toAbsolutePath();
    }
}
