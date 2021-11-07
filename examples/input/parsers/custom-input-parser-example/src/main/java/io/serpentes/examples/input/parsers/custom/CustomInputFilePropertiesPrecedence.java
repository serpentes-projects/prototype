package io.serpentes.examples.input.parsers.custom;

import io.serpentes.boot.se.input.sources.file_system.file_properties.DefaultInputFile;
import io.serpentes.api.precedence.NameBasedPrecedence;
import io.serpentes.input.sources.file_system.annotations.file_properties.InputFilePropertiesPrecedence;
import jakarta.annotation.Priority;
import jakarta.enterprise.inject.Alternative;

import java.util.LinkedHashSet;
import java.util.List;

@InputFilePropertiesPrecedence
@Alternative
@Priority(100)
public class CustomInputFilePropertiesPrecedence implements NameBasedPrecedence {
    @Override
    public LinkedHashSet<String> getPrecedence() {
        return new LinkedHashSet<>(List.of(YAMLInputFileProperties.NAME, DefaultInputFile.NAME));
    }
}
