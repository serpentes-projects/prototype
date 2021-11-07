package io.serpentes.testing.assertions.input.base.primitive;

import io.serpentes.api.input.trees.InputTree;
import io.serpentes.api.input.trees.vertices.terminal.IntegerInputTerminalVertex;
import io.serpentes.input.trees.base.DefaultInputTree;
import io.serpentes.testing.assertions.input.base.DefaultInputVertexFactory;
import io.serpentes.testing.assertions.input.base.DeserializableInputAssertion;
import io.serpentes.testing.assertions.input.content.api.Content;
import io.serpentes.testing.assertions.input.content.api.primitive.IntegerInputContent;
import io.serpentes.testing.assertions.input.content.json.primitive.IntegerJsonContent;

import java.lang.reflect.Type;

public class IntegerInputAssertion implements DeserializableInputAssertion<Integer> {
    private final IntegerInputContent content;

    public IntegerInputAssertion() {
        this(new IntegerJsonContent());
    }

    public IntegerInputAssertion(IntegerInputContent content) {
        this.content = content;
    }

    @Override
    public Content<Integer> getContent() {
        return content;
    }

    @Override
    public Type getType() {
        return content.asType();
    }

    @Override
    public Integer defaultExpectedValue() {
        return content.afterDeserializing();
    }

    @Override
    public InputTree expectedInputTree() {
        return new DefaultInputTree(expectedRootInputVertex());
    }

    @Override
    public IntegerInputTerminalVertex expectedRootInputVertex() {
        return DefaultInputVertexFactory.createIntegerTerminalVertex(getContent().beforeParsing());
    }

    @Override
    public void assertInputTree(InputTree actualInputTree) {

    }
}
