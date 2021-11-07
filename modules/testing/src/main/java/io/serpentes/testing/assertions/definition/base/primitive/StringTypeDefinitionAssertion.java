package io.serpentes.testing.assertions.definition.base.primitive;

import io.serpentes.api.definition.trees.TypeTree;
import io.serpentes.api.definition.trees.vertices.terminal.StringTypeVertex;
import io.serpentes.definition.trees.base.DefaultTypeTree;
import io.serpentes.testing.assertions.definition.content.api.primitive.StringDefinitionContent;
import io.serpentes.testing.assertions.definition.type.tree.DefaultTypeVertexFactory;
import io.serpentes.testing.assertions.definition.base.DeserializableTypeDefinitionAssertion;

import java.lang.reflect.Type;

public class StringTypeDefinitionAssertion implements DeserializableTypeDefinitionAssertion {
    private final StringDefinitionContent stringDefinitionContent;

    public StringTypeDefinitionAssertion(final StringDefinitionContent stringDefinitionContent) {
        this.stringDefinitionContent = stringDefinitionContent;
    }

    @Override
    public Type getType() {
        return stringDefinitionContent.asType();
    }

    @Override
    public String getDefinitionContent() {
        return stringDefinitionContent.beforeParsing();
    }

    @Override
    public TypeTree expectedTypeTree() {
        return new DefaultTypeTree(expectedRootTypeVertex());
    }

    @Override
    public StringTypeVertex expectedRootTypeVertex() {
        return DefaultTypeVertexFactory.createStringTerminalVertex();
    }

    @Override
    public void assertTypeTree(TypeTree actualTypeTree) {

    }
}
