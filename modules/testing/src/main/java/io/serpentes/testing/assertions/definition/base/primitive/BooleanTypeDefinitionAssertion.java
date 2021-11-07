package io.serpentes.testing.assertions.definition.base.primitive;

import io.serpentes.api.definition.trees.TypeTree;
import io.serpentes.api.definition.trees.vertices.terminal.BooleanTypeVertex;
import io.serpentes.definition.trees.base.DefaultTypeTree;
import io.serpentes.testing.assertions.definition.content.api.primitive.BooleanDefinitionContent;
import io.serpentes.testing.assertions.definition.type.tree.DefaultTypeVertexFactory;
import io.serpentes.testing.assertions.definition.base.DeserializableTypeDefinitionAssertion;

import java.lang.reflect.Type;

public class BooleanTypeDefinitionAssertion implements DeserializableTypeDefinitionAssertion {
    private final BooleanDefinitionContent booleanDefinitionContent;

    public BooleanTypeDefinitionAssertion(final BooleanDefinitionContent booleanDefinitionContent) {
        this.booleanDefinitionContent = booleanDefinitionContent;
    }

    @Override
    public String getDefinitionContent() {
        return booleanDefinitionContent.beforeParsing();
    }

    @Override
    public TypeTree expectedTypeTree() {
        return new DefaultTypeTree(expectedRootTypeVertex());
    }

    @Override
    public BooleanTypeVertex expectedRootTypeVertex() {
        return DefaultTypeVertexFactory.createBooleanTerminalVertex();
    }

    @Override
    public void assertTypeTree(TypeTree actualTypeTree) {
    }

    @Override
    public Type getType() {
        return booleanDefinitionContent.asType();
    }
}
