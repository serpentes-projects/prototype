package io.serpentes.testing.cdi.extentions.definition.sources.file_system.file_properties;


import io.serpentes.definition.sources.file_system.api.file_properties.DefinitionFileProperties;

public class CustomDefinitionFileProperties implements DefinitionFileProperties {
    private final String definitionFileName;

    public CustomDefinitionFileProperties(final String definitionFileName) {
        this.definitionFileName = definitionFileName;
    }

    @Override
    public String getName() {
        return definitionFileName;
    }

    @Override
    public String getContentInformation() {
        return "json"; //TODO: Remove when adding more parsers.
    }

}
