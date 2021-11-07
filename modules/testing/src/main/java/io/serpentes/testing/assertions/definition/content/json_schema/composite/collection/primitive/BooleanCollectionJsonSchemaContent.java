package io.serpentes.testing.assertions.definition.content.json_schema.composite.collection.primitive;

import io.serpentes.testing.assertions.definition.content.api.composite.CollectionDefinitionContent;

import java.lang.reflect.Type;

public class BooleanCollectionJsonSchemaContent implements CollectionDefinitionContent {
    public static final String JSON = "{\n" +
            "  \"$schema\": \"http://json-schema.org/draft-04/schema#\",\n" +
            "  \"type\": \"array\",\n" +
            "  \"items\": {\n" +
            "    \"type\": \"boolean\"\n" +
            "  }\n" +
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
