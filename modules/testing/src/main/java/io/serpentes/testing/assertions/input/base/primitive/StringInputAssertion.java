package io.serpentes.testing.assertions.input.base.primitive;

import io.serpentes.api.input.trees.InputTree;
import io.serpentes.input.trees.base.DefaultInputTree;
import io.serpentes.input.trees.base.vertices.terminal.DefaultStringInputTerminalVertex;
import io.serpentes.testing.assertions.input.base.DefaultInputVertexFactory;
import io.serpentes.testing.assertions.input.base.DeserializableInputAssertion;
import io.serpentes.testing.assertions.input.content.api.Content;
import io.serpentes.testing.assertions.input.content.api.primitive.StringInputContent;
import io.serpentes.testing.assertions.input.content.json.primitive.StringJsonContent;

import java.lang.reflect.Type;

public class StringInputAssertion implements DeserializableInputAssertion<String> {
    private final StringInputContent content;

    public StringInputAssertion() {
        this(new StringJsonContent());
    }

    public StringInputAssertion(StringInputContent content) {
        this.content = content;
    }

    @Override
    public Content<String> getContent() {
        return content;
    }

    @Override
    public Type getType() {
        return content.asType();
    }

    @Override
    public String defaultExpectedValue() {
        return content.afterDeserializing();
    }

    @Override
    public InputTree expectedInputTree() {
        return new DefaultInputTree(expectedRootInputVertex());
    }

    @Override
    public DefaultStringInputTerminalVertex expectedRootInputVertex() {
        return DefaultInputVertexFactory.createStringTerminalVertex(getContent().afterParsing());
    }

    @Override
    public void assertInputTree(InputTree actualInputTree) {

    }
}
