package io.serpentes.testing.cdi.extentions.definition.sources.file_system.file_path;

import io.serpentes.api.precedence.NameBasedPrecedence;

import java.util.LinkedHashSet;
import java.util.Set;

public class CustomDefinitionDirectoryPrecedence implements NameBasedPrecedence {
    private final Set<String> precedence;

    public CustomDefinitionDirectoryPrecedence(final Set<String> precedence) {
        this.precedence = precedence;
    }

    @Override
    public LinkedHashSet<String> getPrecedence() {
        return new LinkedHashSet<>(this.precedence);
    }
}
