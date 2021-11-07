package io.serpentes.boot.se.input.sources.file_system.file_properties;

import io.serpentes.input.sources.file_system.annotations.file_properties.DefaultInputFileProperties;
import io.serpentes.input.sources.file_system.api.file_properties.InputFileProperties;

@DefaultInputFileProperties
public class DefaultInputFile implements InputFileProperties {
    public static final String NAME = "serpentes.json";
    @Override
    public String getName() {
//        TODO: Instead of returning this, try to determine the project name.
        return NAME;
    }

    @Override
    public String getContentInformation() {
        return "json";
    }
}
