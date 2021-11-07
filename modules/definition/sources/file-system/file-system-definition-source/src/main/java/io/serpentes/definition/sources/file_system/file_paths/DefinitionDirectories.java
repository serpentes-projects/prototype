package io.serpentes.definition.sources.file_system.file_paths;

import io.serpentes.api.precedence.NameBasedPrecedence;

import io.serpentes.definition.sources.file_system.annotations.directories.DefaultDefinitionDirectory;
import io.serpentes.definition.sources.file_system.annotations.directories.DefinitionDirectoryPrecedence;
import io.serpentes.definition.sources.file_system.annotations.directories.SupportedDefinitionDirectories;
import io.serpentes.definition.sources.file_system.api.directories.DefinitionDirectory;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DefinitionDirectories {
    private final DefinitionDirectory defaultDefinitionDirectory;
    private final Instance<Set<DefinitionDirectory>> supportedDirectories;
    private final NameBasedPrecedence directoryPrecedence;

    @Inject
    public DefinitionDirectories(@DefaultDefinitionDirectory final DefinitionDirectory defaultDefinitionDirectory,
                                 @SupportedDefinitionDirectories final Instance<Set<DefinitionDirectory>> supportedDirectories,
                                 @DefinitionDirectoryPrecedence final NameBasedPrecedence directoryPrecedence){
        this.defaultDefinitionDirectory = defaultDefinitionDirectory;
        this.supportedDirectories = supportedDirectories;
        this.directoryPrecedence = directoryPrecedence;
    }

    public Path getPath() {
        final List<DefinitionDirectory> definitionDirectories = new ArrayList<>();
        for (Set<DefinitionDirectory> supportedDefinitionDirectory : supportedDirectories) {
            definitionDirectories.addAll(supportedDefinitionDirectory);
        }
        final List<String> retrievableDirectories = new ArrayList<>(directoryPrecedence.getPrecedence());
        for (int i = retrievableDirectories.size() - 1; i >= 0; i--) {
            final var directoryName = retrievableDirectories.get(i);
            for (var definitionDirectory : definitionDirectories) {
                if (directoryName.equals(definitionDirectory.getName())) {
                    return definitionDirectory.getPath();
                }
            }
        }
        return defaultDefinitionDirectory.getPath();
    }
}
