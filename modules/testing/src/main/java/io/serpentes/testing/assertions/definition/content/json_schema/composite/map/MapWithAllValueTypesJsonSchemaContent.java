package io.serpentes.testing.assertions.definition.content.json_schema.composite.map;

import io.serpentes.testing.assertions.definition.content.api.composite.MapDefinitionContent;

import java.lang.reflect.Type;

public class MapWithAllValueTypesJsonSchemaContent implements MapDefinitionContent {
    public static final String JSON = "{\n" +
            "  \"$schema\": \"http://json-schema.org/draft-04/schema#\",\n" +
            "  \"type\": \"object\",\n" +
            "  \"properties\": {\n" +
            "    \"null\": {\n" +
            "      \"type\": \"null\"\n" +
            "    },\n" +
            "    \"boolean\": {\n" +
            "      \"type\": \"boolean\"\n" +
            "    },\n" +
            "    \"integer\": {\n" +
            "      \"type\": \"integer\"\n" +
            "    },\n" +
            "    \"float\": {\n" +
            "      \"type\": \"number\"\n" +
            "    },\n" +
            "    \"string\": {\n" +
            "      \"type\": \"string\"\n" +
            "    },\n" +
            "    \"collection\": {\n" +
            "      \"type\": \"array\",\n" +
            "      \"items\": {\n" +
            "        \"type\": \"null\"\n" +
            "      }\n" +
            "    },\n" +
            "    \"map\": {\n" +
            "      \"type\": \"object\"\n" +
            "    }\n" +
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
