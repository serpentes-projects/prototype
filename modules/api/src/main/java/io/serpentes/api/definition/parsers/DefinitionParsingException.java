package io.serpentes.api.definition.parsers;

public class DefinitionParsingException extends Exception {
    public DefinitionParsingException(Exception e) {
        super(e);
    }

    public DefinitionParsingException(String message) {
        super(message);
    }
}
