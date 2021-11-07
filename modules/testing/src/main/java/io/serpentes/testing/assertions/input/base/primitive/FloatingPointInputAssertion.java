package io.serpentes.testing.assertions.input.base.primitive;

import io.serpentes.api.input.trees.InputTree;
import io.serpentes.input.trees.base.DefaultInputTree;
import io.serpentes.input.trees.base.vertices.terminal.DefaultFloatingPointInputTerminalVertex;
import io.serpentes.testing.assertions.input.base.DefaultInputVertexFactory;
import io.serpentes.testing.assertions.input.base.DeserializableInputAssertion;
import io.serpentes.testing.assertions.input.content.api.Content;
import io.serpentes.testing.assertions.input.content.api.primitive.FloatingPointInputContent;
import io.serpentes.testing.assertions.input.content.json.primitive.FloatingPointJsonContent;

import java.lang.reflect.Type;

public class FloatingPointInputAssertion implements DeserializableInputAssertion<Double> {
    private final FloatingPointInputContent content;

    public FloatingPointInputAssertion() {
        this(new FloatingPointJsonContent());
    }

    public FloatingPointInputAssertion(FloatingPointInputContent content) {
        this.content = content;
    }

    @Override
    public Type getType() {
        return content.asType();
    }

    @Override
    public Double defaultExpectedValue() {
        return content.afterDeserializing();
    }

    @Override
    public InputTree expectedInputTree() {
        return new DefaultInputTree(expectedRootInputVertex());
    }

    @Override
    public DefaultFloatingPointInputTerminalVertex expectedRootInputVertex() {
        return DefaultInputVertexFactory.createFloatingPointTerminalVertex(getContent().beforeParsing());
    }

    @Override
    public Content<Double> getContent() {
        return content;
    }

    @Override
    public void assertInputTree(InputTree actualInputTree) {

    }
}
