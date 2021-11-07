package io.serpentes.api.input.parsers;

public class InputParsingException extends Exception {
    public InputParsingException(Exception e) {
        super(e);
    }

    public InputParsingException(String message) {
        super(message);
    }
}
