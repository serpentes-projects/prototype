package io.serpentes.testing.assertions.definition.content.json_schema;

import io.serpentes.testing.assertions.definition.content.api.DefinitionContent;

public class EmptyJsonSchemaContent implements DefinitionContent {
    public static final String JSON = "";

    @Override
    public String beforeParsing() {
        return JSON;
    }

    @Override
    public String afterParsing() {
        return null;
    }
}
