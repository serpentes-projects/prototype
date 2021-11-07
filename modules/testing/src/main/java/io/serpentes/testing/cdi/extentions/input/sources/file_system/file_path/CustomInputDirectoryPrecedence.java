package io.serpentes.testing.cdi.extentions.input.sources.file_system.file_path;

import io.serpentes.api.precedence.NameBasedPrecedence;

import java.util.LinkedHashSet;
import java.util.Set;

public class CustomInputDirectoryPrecedence implements NameBasedPrecedence {
    private final Set<String> precedence;

    public CustomInputDirectoryPrecedence(final Set<String> precedence) {
        this.precedence = precedence;
    }

    @Override
    public LinkedHashSet<String> getPrecedence() {
        return new LinkedHashSet<>(this.precedence);
    }
}
