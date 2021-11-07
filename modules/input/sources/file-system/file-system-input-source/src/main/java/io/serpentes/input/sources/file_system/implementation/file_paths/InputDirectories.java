package io.serpentes.input.sources.file_system.implementation.file_paths;

import io.serpentes.api.precedence.NameBasedPrecedence;
import io.serpentes.input.sources.file_system.annotations.directories.DefaultInputDirectory;
import io.serpentes.input.sources.file_system.annotations.directories.InputDirectoryPrecedence;
import io.serpentes.input.sources.file_system.annotations.directories.SupportedInputDirectories;
import io.serpentes.input.sources.file_system.api.directories.InputDirectory;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class InputDirectories {
    private final InputDirectory defaultDirectory;
    private final Instance<Set<InputDirectory>> supportedDirectories;
    private final NameBasedPrecedence directoryPrecedence;

    @Inject
    public InputDirectories(@DefaultInputDirectory InputDirectory defaultDirectory,
                            @SupportedInputDirectories Instance<Set<InputDirectory>> supportedDirectories,
                            @InputDirectoryPrecedence NameBasedPrecedence directoryPrecedence) {
        this.supportedDirectories = supportedDirectories;
        this.defaultDirectory = defaultDirectory;
        this.directoryPrecedence = directoryPrecedence;
    }

    public List<Path> getPaths() {
        final List<Path> inputDirectoryPaths = new ArrayList<>();
        inputDirectoryPaths.add(defaultDirectory.getPath());

        final List<InputDirectory> inputDirectories = new ArrayList<>();
        for (final Set<InputDirectory> supportedInputDirectory : supportedDirectories) {
            inputDirectories.addAll(supportedInputDirectory);
        }
        final List<String> nameBasedPrecedence = new ArrayList<>(directoryPrecedence.getPrecedence());
        for (int i = nameBasedPrecedence.size() - 1; i >= 0; i--) {
            final var filePathName = nameBasedPrecedence.get(i);
            for (final var inputDirectory : inputDirectories) {
                if (filePathName.equals(inputDirectory.getName())) {
                    inputDirectoryPaths.add(i, inputDirectory.getPath());
                }
            }
        }
        return inputDirectoryPaths;
    }
}
