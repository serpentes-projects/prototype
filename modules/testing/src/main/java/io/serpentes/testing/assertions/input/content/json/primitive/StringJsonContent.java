package io.serpentes.testing.assertions.input.content.json.primitive;

import io.serpentes.testing.assertions.input.content.api.primitive.StringInputContent;

public class StringJsonContent implements StringInputContent {
    public static final String JSON = "\"string\"";

    @Override
    public String beforeParsing() {
        return JSON;
    }

    @Override
    public String afterParsing() {
        return "string";
    }

    @Override
    public String afterDeserializing() {
        return this.afterParsing();
    }

    @Override
    public Class<String> asType() {
        return String.class;
    }
}
