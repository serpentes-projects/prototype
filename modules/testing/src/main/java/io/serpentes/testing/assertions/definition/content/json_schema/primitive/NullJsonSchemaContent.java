package io.serpentes.testing.assertions.definition.content.json_schema.primitive;

import io.serpentes.testing.assertions.definition.content.api.primitive.NullDefinitionContent;

import java.lang.reflect.Type;

public class NullJsonSchemaContent implements NullDefinitionContent {
    public static final String JSON = "{\n" +
            "  \"$schema\": \"http://json-schema.org/draft-04/schema#\",\n" +
            "  \"type\": \"null\"\n" +
            "}";

    @Override
    public String beforeParsing() {
        return JSON;
    }

    @Override
    public String afterParsing() {
        return null;
    }

    @Override
    public Type asType() {
        return null;
    }
}
