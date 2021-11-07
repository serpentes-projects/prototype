package io.serpentes.testing.assertions.input.base.primitive;

import io.serpentes.api.input.trees.InputTree;
import io.serpentes.input.trees.base.DefaultInputTree;
import io.serpentes.input.trees.base.vertices.terminal.DefaultNullInputTerminalVertex;
import io.serpentes.testing.assertions.input.base.DefaultInputVertexFactory;
import io.serpentes.testing.assertions.input.base.DeserializableInputAssertion;
import io.serpentes.testing.assertions.input.content.api.Content;
import io.serpentes.testing.assertions.input.content.api.primitive.NullInputContent;
import io.serpentes.testing.assertions.input.content.json.primitive.NullJsonContent;
import org.junit.jupiter.api.Assertions;

import java.lang.reflect.Type;

public class NullInputAssertion implements DeserializableInputAssertion<Object> {
    private final NullInputContent content;

    public NullInputAssertion() {
        this(new NullJsonContent());
    }

    public NullInputAssertion(NullInputContent content) {
        this.content = content;
    }

    @Override
    public Content<Object> getContent() {
        return content;
    }

    @Override
    public Type getType() {
        return content.asType();
    }

    @Override
    public Object defaultExpectedValue() {
        return content.afterDeserializing();
    }

    @Override
    public void assertValue(Object nullObject) {
        Assertions.assertNull(nullObject);
    }

    @Override
    public InputTree expectedInputTree() {
        return new DefaultInputTree(expectedRootInputVertex());
    }

    @Override
    public DefaultNullInputTerminalVertex expectedRootInputVertex() {
        return DefaultInputVertexFactory.createNullTerminalVertex();
    }

    @Override
    public void assertInputTree(InputTree actualInputTree) {
        //FIXME
    }
}
