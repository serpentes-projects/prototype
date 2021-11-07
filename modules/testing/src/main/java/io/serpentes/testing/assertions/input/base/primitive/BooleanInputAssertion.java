package io.serpentes.testing.assertions.input.base.primitive;

import io.serpentes.api.input.trees.InputTree;
import io.serpentes.input.trees.base.DefaultInputTree;
import io.serpentes.input.trees.base.vertices.terminal.DefaultBooleanInputTerminalVertex;
import io.serpentes.testing.assertions.input.base.DefaultInputVertexFactory;
import io.serpentes.testing.assertions.input.base.DeserializableInputAssertion;
import io.serpentes.testing.assertions.input.content.api.Content;
import io.serpentes.testing.assertions.input.content.api.primitive.BooleanInputContent;
import io.serpentes.testing.assertions.input.content.json.primitive.BooleanJsonContent;
import org.junit.jupiter.api.Assertions;

import java.lang.reflect.Type;

public class BooleanInputAssertion implements DeserializableInputAssertion<Boolean> {
    private final BooleanInputContent content;

    public BooleanInputAssertion() {
        this(new BooleanJsonContent());
    }

    public BooleanInputAssertion(BooleanInputContent content) {
        this.content = content;
    }

    @Override
    public Content<Boolean> getContent() {
        return content;
    }

    @Override
    public Type getType() {
        return content.asType();
    }

    @Override
    public void assertValue(Boolean aBoolean) {
        Assertions.assertTrue(aBoolean);
    }

    @Override
    public InputTree expectedInputTree() {
        return new DefaultInputTree(expectedRootInputVertex());
    }

    @Override
    public DefaultBooleanInputTerminalVertex expectedRootInputVertex() {
        return DefaultInputVertexFactory.createBooleanTerminalVertex(getContent().beforeParsing());
    }

    @Override
    public void assertInputTree(InputTree actualInputTree) {

    }

    @Override
    public Boolean defaultExpectedValue() {
        return content.afterDeserializing();
    }
}
