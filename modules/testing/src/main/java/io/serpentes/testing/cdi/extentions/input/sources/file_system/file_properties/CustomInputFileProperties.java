package io.serpentes.testing.cdi.extentions.input.sources.file_system.file_properties;

import io.serpentes.input.sources.file_system.api.file_properties.InputFileProperties;

public class CustomInputFileProperties implements InputFileProperties {
    private final String name;

    public CustomInputFileProperties(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getContentInformation() {
        return null;
    }
}
