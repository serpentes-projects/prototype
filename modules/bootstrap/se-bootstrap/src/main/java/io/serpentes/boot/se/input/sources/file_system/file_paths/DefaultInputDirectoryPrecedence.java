package io.serpentes.boot.se.input.sources.file_system.file_paths;

import io.serpentes.api.precedence.NameBasedPrecedence;
import io.serpentes.input.sources.file_system.annotations.directories.InputDirectoryPrecedence;

import java.util.LinkedHashSet;
import java.util.List;

@InputDirectoryPrecedence
public class DefaultInputDirectoryPrecedence implements NameBasedPrecedence {

    @Override
    public LinkedHashSet<String> getPrecedence() {
        return new LinkedHashSet<>(List.of(DefaultInputDirectory.NAME));
    }
}
