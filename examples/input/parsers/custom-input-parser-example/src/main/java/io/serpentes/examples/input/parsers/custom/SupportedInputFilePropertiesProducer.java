package io.serpentes.examples.input.parsers.custom;

import io.serpentes.input.sources.file_system.annotations.file_properties.SupportedInputFileProperties;
import io.serpentes.input.sources.file_system.api.file_properties.InputFileProperties;
import jakarta.enterprise.inject.Produces;

import java.util.Set;

public class SupportedInputFilePropertiesProducer {
    @Produces
    @SupportedInputFileProperties
    public Set<InputFileProperties> produceInputFileProperties(){
        return Set.of(new YAMLInputFileProperties());
    }
}
