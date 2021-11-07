package io.serpentes.testing.cdi.extentions.input.parsers;

import io.serpentes.api.precedence.NameBasedPrecedence;

import java.util.LinkedHashSet;
import java.util.Set;

public class CustomInputParsersPrecedence implements NameBasedPrecedence {
    private final Set<String> precedence;

    public CustomInputParsersPrecedence(final Set<String> precedence) {
        this.precedence = precedence;
    }

    @Override
    public LinkedHashSet<String> getPrecedence() {
        return new LinkedHashSet<>(precedence);
    }
}

