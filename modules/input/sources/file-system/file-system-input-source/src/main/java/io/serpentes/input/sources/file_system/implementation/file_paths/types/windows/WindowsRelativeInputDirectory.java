package io.serpentes.input.sources.file_system.implementation.file_paths.types.windows;

import io.serpentes.input.sources.file_system.api.directories.RelativeInputDirectory;
import io.serpentes.input.sources.file_system.api.directories.os.windows.WindowsInputDirectory;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.nio.file.Path;

@AllArgsConstructor
@Getter
public class WindowsRelativeInputDirectory implements WindowsInputDirectory, RelativeInputDirectory {
    private final String name;
    private final Path path;
}
