package io.serpentes.testing.assertions.input.content.api;

public interface Content<T> {
    String beforeParsing();

    String afterParsing();

    T afterDeserializing();
}
