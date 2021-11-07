package io.serpentes.boot.se.definition.sources.file_system.file_properties;

import io.serpentes.definition.sources.file_system.annotations.file_properties.DefaultDefinitionFileProperties;
import io.serpentes.definition.sources.file_system.api.file_properties.DefinitionFileProperties;

@DefaultDefinitionFileProperties
public class DefaultDefinitionFile implements DefinitionFileProperties {
    @Override
    public String getName() {
//        TODO: Instead of returning this, try to determine the project name.
        return "serpentes.schema.json";
    }

    @Override
    public String getContentInformation() {
        return "json";
    }
}
