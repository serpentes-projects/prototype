package io.serpentes.boot.se.input.parsers;

import io.serpentes.api.annotations.input.parsers.InputParsersPrecedence;
import io.serpentes.api.precedence.NameBasedPrecedence;

import java.util.LinkedHashSet;
import java.util.List;

@InputParsersPrecedence
public class DefaultInputParserPrecedence implements NameBasedPrecedence {

    @Override
    public LinkedHashSet<String> getPrecedence() {
        return new LinkedHashSet<>(List.of("application/json"));
    }
}
