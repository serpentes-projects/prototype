package io.serpentes.boot.se.input.sources.file_system.file_properties;

import io.serpentes.api.precedence.NameBasedPrecedence;
import io.serpentes.input.sources.file_system.annotations.file_properties.InputFilePropertiesPrecedence;

import java.util.LinkedHashSet;
import java.util.List;

@InputFilePropertiesPrecedence
public class DefaultInputFilePrecedence implements NameBasedPrecedence {

    @Override
    public LinkedHashSet<String> getPrecedence() {
        return new LinkedHashSet<>(List.of(DefaultInputFile.NAME));
    }
}
