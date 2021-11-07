package io.serpentes.boot.se.definition.sources.file_system.file_paths;

import io.serpentes.api.precedence.NameBasedPrecedence;
import io.serpentes.definition.sources.file_system.annotations.directories.DefinitionDirectoryPrecedence;

import java.util.LinkedHashSet;
import java.util.List;

@DefinitionDirectoryPrecedence
public class DefaultDefinitionDirectoryPrecedence implements NameBasedPrecedence {

    @Override
    public LinkedHashSet<String> getPrecedence() {
        return new LinkedHashSet<>(List.of(DefaultDefinitionDirectory.NAME));
    }
}
