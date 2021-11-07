package io.serpentes.testing.assertions.input.content.json.primitive;

import io.serpentes.testing.assertions.input.content.api.primitive.FloatingPointInputContent;

public class FloatingPointJsonContent implements FloatingPointInputContent {
    public static final String JSON = "1.1";

    @Override
    public String beforeParsing() {
        return JSON;
    }

    @Override
    public String afterParsing() {
        return JSON;
    }

    @Override
    public Double afterDeserializing() {
        return Double.parseDouble(JSON);
    }

    @Override
    public Class<Double> asType() {
        return Double.class;
    }
}
