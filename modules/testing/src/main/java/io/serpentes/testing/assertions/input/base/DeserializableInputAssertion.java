package io.serpentes.testing.assertions.input.base;

import java.lang.reflect.Type;

public interface DeserializableInputAssertion<T> extends InputAssertion<T> {
    Type getType();
}
