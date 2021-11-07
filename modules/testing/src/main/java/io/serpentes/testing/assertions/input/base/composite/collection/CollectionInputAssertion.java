package io.serpentes.testing.assertions.input.base.composite.collection;

import io.serpentes.testing.assertions.input.base.DeserializableInputAssertion;
import io.serpentes.testing.assertions.input.base.composite.CompositeInputAssertion;

import java.util.Collection;

public interface CollectionInputAssertion<T> extends DeserializableInputAssertion<Collection<T>>, CompositeInputAssertion {
}
