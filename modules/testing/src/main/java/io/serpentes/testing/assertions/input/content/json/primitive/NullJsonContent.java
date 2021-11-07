package io.serpentes.testing.assertions.input.content.json.primitive;

import io.serpentes.testing.assertions.input.content.api.primitive.NullInputContent;

public class NullJsonContent implements NullInputContent {
    public static final String JSON = "null";

    @Override
    public String beforeParsing() {
        return JSON;
    }

    @Override
    public String afterParsing() {
        return JSON;
    }

    @Override
    public Object afterDeserializing() {
        return null;
    }

    @Override
    public Class<Object> asType() {
        return Object.class;
    }
}
