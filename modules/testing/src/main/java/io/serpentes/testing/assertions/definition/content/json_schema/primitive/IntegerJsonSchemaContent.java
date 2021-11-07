package io.serpentes.testing.assertions.definition.content.json_schema.primitive;

import io.serpentes.testing.assertions.definition.content.api.primitive.IntegerDefinitionContent;

import java.lang.reflect.Type;

public class IntegerJsonSchemaContent implements IntegerDefinitionContent {
    public static final String JSON = "{\n" +
            "  \"$schema\": \"http://json-schema.org/draft-04/schema#\",\n" +
            "  \"type\": \"integer\"\n" +
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
