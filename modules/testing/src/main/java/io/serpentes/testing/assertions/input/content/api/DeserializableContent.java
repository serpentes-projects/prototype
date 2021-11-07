package io.serpentes.testing.assertions.input.content.api;

import java.lang.reflect.Type;

public interface DeserializableContent<T> extends Content<T> {
    Type asType();
}
