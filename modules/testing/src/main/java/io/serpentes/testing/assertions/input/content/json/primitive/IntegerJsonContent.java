package io.serpentes.testing.assertions.input.content.json.primitive;

import io.serpentes.testing.assertions.input.content.api.primitive.IntegerInputContent;

public class IntegerJsonContent implements IntegerInputContent {
    public static final String JSON = "1";

    @Override
    public String beforeParsing() {
        return JSON;
    }

    @Override
    public String afterParsing() {
        return JSON;
    }

    @Override
    public Integer afterDeserializing() {
        return 1;
    }

    @Override
    public Class<Integer> asType() {
        return Integer.class;
    }

}
