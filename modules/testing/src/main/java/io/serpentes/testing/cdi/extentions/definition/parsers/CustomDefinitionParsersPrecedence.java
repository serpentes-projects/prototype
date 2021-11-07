package io.serpentes.testing.cdi.extentions.definition.parsers;

import io.serpentes.api.precedence.NameBasedPrecedence;

import java.util.LinkedHashSet;
import java.util.Set;

public class CustomDefinitionParsersPrecedence implements NameBasedPrecedence {
    private final Set<String> precedence;

    public CustomDefinitionParsersPrecedence(final Set<String> precedence) {
        this.precedence = precedence;
    }

    @Override
    public LinkedHashSet<String> getPrecedence() {
        return new LinkedHashSet<>(precedence);
    }
}

