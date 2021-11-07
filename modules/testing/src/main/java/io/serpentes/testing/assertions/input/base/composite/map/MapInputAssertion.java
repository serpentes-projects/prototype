package io.serpentes.testing.assertions.input.base.composite.map;

import io.serpentes.testing.assertions.input.base.DeserializableInputAssertion;
import io.serpentes.testing.assertions.input.base.composite.CompositeInputAssertion;

import java.util.Collection;

public interface MapInputAssertion<T> extends DeserializableInputAssertion<T>, CompositeInputAssertion {
    Collection<String> primitiveKeyNames();

    Collection<String> compositeKeyNames();

    Collection<String> allKeyNames();
}
