package io.serpentes.testing.assertions.definition.base.primitive;

import io.serpentes.api.definition.trees.TypeTree;
import io.serpentes.api.definition.trees.vertices.terminal.IntegerTypeVertex;
import io.serpentes.definition.trees.base.DefaultTypeTree;
import io.serpentes.testing.assertions.definition.type.tree.DefaultTypeVertexFactory;
import io.serpentes.testing.assertions.definition.base.DeserializableTypeDefinitionAssertion;
import io.serpentes.testing.assertions.definition.content.api.primitive.IntegerDefinitionContent;

import java.lang.reflect.Type;

public class IntegerTypeDefinitionAssertion implements DeserializableTypeDefinitionAssertion {
    private final IntegerDefinitionContent integerDefinitionContent;

    public IntegerTypeDefinitionAssertion(final IntegerDefinitionContent integerDefinitionContent) {
        this.integerDefinitionContent = integerDefinitionContent;
    }

    @Override
    public Type getType() {
        return integerDefinitionContent.asType();
    }

    @Override
    public String getDefinitionContent() {
        return integerDefinitionContent.beforeParsing();
    }

    @Override
    public TypeTree expectedTypeTree() {
        return new DefaultTypeTree(expectedRootTypeVertex());
    }

    @Override
    public IntegerTypeVertex expectedRootTypeVertex() {
        return DefaultTypeVertexFactory.createIntegerTerminalVertex();
    }

    @Override
    public void assertTypeTree(TypeTree actualTypeTree) {

    }
}
