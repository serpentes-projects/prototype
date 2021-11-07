package io.serpentes.definition.sources.file_system.file_paths.types.windows;

import io.serpentes.definition.sources.file_system.api.directories.AbsoluteDefinitionDirectory;
import io.serpentes.definition.sources.file_system.api.directories.os.windows.WindowsDefinitionDirectory;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.nio.file.Path;

@AllArgsConstructor
@Getter
public class WindowsAbsoluteDefinitionDirectory implements WindowsDefinitionDirectory, AbsoluteDefinitionDirectory {
    private final String name;
    private final Path path;
}
