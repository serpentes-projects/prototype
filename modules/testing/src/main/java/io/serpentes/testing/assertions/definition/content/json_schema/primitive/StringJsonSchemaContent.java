package io.serpentes.testing.assertions.definition.content.json_schema.primitive;

import io.serpentes.testing.assertions.definition.content.api.primitive.StringDefinitionContent;

import java.lang.reflect.Type;

public class StringJsonSchemaContent implements StringDefinitionContent {
    public static final String JSON = "{\n" +
            "  \"$schema\": \"http://json-schema.org/draft-04/schema#\",\n" +
            "  \"type\": \"string\"\n" +
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
