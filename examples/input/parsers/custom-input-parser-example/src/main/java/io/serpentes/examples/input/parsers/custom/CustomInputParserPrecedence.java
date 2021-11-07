package io.serpentes.examples.input.parsers.custom;

import io.serpentes.api.annotations.input.parsers.InputParsersPrecedence;
import io.serpentes.api.precedence.NameBasedPrecedence;
import jakarta.annotation.Priority;
import jakarta.enterprise.inject.Alternative;

import java.util.LinkedHashSet;
import java.util.List;

@InputParsersPrecedence
@Alternative
@Priority(100)
public class CustomInputParserPrecedence implements NameBasedPrecedence {
    @Override
    public LinkedHashSet<String> getPrecedence() {
        return new LinkedHashSet<>(List.of("yaml", "json"));
    }
}
