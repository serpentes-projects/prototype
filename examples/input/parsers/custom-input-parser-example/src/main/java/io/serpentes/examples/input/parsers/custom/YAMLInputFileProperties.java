package io.serpentes.examples.input.parsers.custom;

import io.serpentes.input.sources.file_system.api.file_properties.InputFileProperties;

@io.serpentes.input.sources.file_system.annotations.file_properties.InputFileProperties
public class YAMLInputFileProperties implements InputFileProperties {

    public static final String NAME = "serpentes.yaml";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public String getContentInformation() {
        return "yaml";
    }
}
