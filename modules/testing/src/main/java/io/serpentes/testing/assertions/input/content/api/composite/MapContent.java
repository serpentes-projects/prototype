package io.serpentes.testing.assertions.input.content.api.composite;

import io.serpentes.testing.assertions.input.content.api.DeserializableContent;

import java.util.Collection;

public interface MapContent<T> extends DeserializableContent<T>, CompositeContent {
    Collection<String> primitiveKeyNames();

    Collection<String> compositeKeyNames();

    Collection<String> allKeyNames();
}
