package io.serpentes.input.sources.file_system.api.directories;

import java.nio.file.Path;

public interface InputDirectory {
    String getName();
    Path getPath();
}
