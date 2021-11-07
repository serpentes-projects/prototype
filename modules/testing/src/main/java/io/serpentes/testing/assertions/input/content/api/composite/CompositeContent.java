package io.serpentes.testing.assertions.input.content.api.composite;

import java.util.Collection;

public interface CompositeContent {
    Collection<String> primitivesBeforeParsing();

    Collection<String> compositesBeforeParsing();

    Collection<String> allBeforeParsing();
}
