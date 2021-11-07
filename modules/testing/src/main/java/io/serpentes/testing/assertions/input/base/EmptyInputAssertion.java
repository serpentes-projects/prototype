package io.serpentes.testing.assertions.input.base;

import io.serpentes.api.input.trees.InputTree;
import io.serpentes.api.input.trees.vertices.InputVertex;
import io.serpentes.testing.assertions.input.content.api.Content;
import io.serpentes.testing.assertions.input.content.json.NoContent;

import java.lang.reflect.Type;

public class EmptyInputAssertion implements DeserializableInputAssertion<Void> {
    private final NoContent noContent;

    public EmptyInputAssertion(NoContent noContent) {
        this.noContent = noContent;
    }

    @Override
    public Type getType() {
        throw new RuntimeException("There is no type for an empty input value.");
    }

    @Override
    public Content<Void> getContent() {
        return noContent;
    }

    @Override
    public Void defaultExpectedValue() {
        throw new RuntimeException("There is no default expected value for an empty input value.");
    }

    @Override
    public InputTree expectedInputTree() {
        return null;
    }

    @Override
    public InputVertex expectedRootInputVertex() {
        return null;
    }

    @Override
    public void assertInputTree(InputTree actualInputTree) {

    }
}
