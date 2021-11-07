package io.serpentes.testing.assertions.input.content.json;

import io.serpentes.testing.assertions.input.content.api.Content;

public class NoContent implements Content<Void> {
    public static final String CONTENT = "";

    @Override
    public String beforeParsing() {
        return CONTENT;
    }

    @Override
    public String afterParsing() {
        //FIXME Should this throw an exception (InputParsingException)? Because "" is not what is expected here.
        return CONTENT;
    }

    @Override
    public Void afterDeserializing() {
        //FIXME Should this throw an exception (InputParsingException)? Because null is not what is expected here.
        return null;
    }
}
