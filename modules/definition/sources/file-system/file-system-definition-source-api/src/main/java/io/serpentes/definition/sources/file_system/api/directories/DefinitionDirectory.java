package io.serpentes.definition.sources.file_system.api.directories;

import java.nio.file.Path;

public interface DefinitionDirectory {
    String getName();
    Path getPath();
}
