package io.serpentes.testing.assertions.input.base;

import io.serpentes.api.input.trees.InputTree;
import io.serpentes.api.input.trees.vertices.InputVertex;
import io.serpentes.testing.assertions.input.content.api.Content;
import org.junit.jupiter.api.Assertions;

public interface InputAssertion<T> {
    Content<T> getContent();

    T defaultExpectedValue();

    default void assertValue(T value) {
        Assertions.assertEquals(defaultExpectedValue(), value);
    }

    InputTree expectedInputTree();

    InputVertex expectedRootInputVertex();

    void assertInputTree(InputTree actualInputTree);
}
