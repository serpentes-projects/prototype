package io.serpentes.testing.assertions.input.content.json.primitive;

import io.serpentes.testing.assertions.input.content.api.primitive.BooleanInputContent;

public class BooleanJsonContent implements BooleanInputContent {
    public static final String JSON = "true";

    @Override
    public String beforeParsing() {
        return JSON;
    }

    @Override
    public String afterParsing() {
        return JSON;
    }

    @Override
    public Boolean afterDeserializing() {
        return true;
    }

    @Override
    public Class<Boolean> asType() {
        return Boolean.class;
    }
}
