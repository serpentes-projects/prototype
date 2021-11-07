package io.serpentes.boot.se.definition.parsers;

import io.serpentes.api.annotations.definition.parsers.DefinitionParsersPrecedence;
import io.serpentes.api.precedence.NameBasedPrecedence;

import java.util.LinkedHashSet;
import java.util.List;

@DefinitionParsersPrecedence
public class DefaultDefinitionParserPrecedence implements NameBasedPrecedence {

    @Override
    public LinkedHashSet<String> getPrecedence() {
        return new LinkedHashSet<>(List.of("application/json"));
    }
}
