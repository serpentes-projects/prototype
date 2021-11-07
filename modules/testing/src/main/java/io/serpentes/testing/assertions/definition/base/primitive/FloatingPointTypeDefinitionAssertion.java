package io.serpentes.testing.assertions.definition.base.primitive;

import io.serpentes.api.definition.trees.TypeTree;
import io.serpentes.api.definition.trees.vertices.terminal.FloatingPointTypeVertex;
import io.serpentes.definition.trees.base.DefaultTypeTree;
import io.serpentes.testing.assertions.definition.content.api.primitive.FloatingPointDefinitionContent;
import io.serpentes.testing.assertions.definition.type.tree.DefaultTypeVertexFactory;
import io.serpentes.testing.assertions.definition.base.DeserializableTypeDefinitionAssertion;

import java.lang.reflect.Type;

public class FloatingPointTypeDefinitionAssertion implements DeserializableTypeDefinitionAssertion {
    private final FloatingPointDefinitionContent floatingPointDefinitionContent;

    public FloatingPointTypeDefinitionAssertion(final FloatingPointDefinitionContent floatingPointDefinitionContent) {
        this.floatingPointDefinitionContent = floatingPointDefinitionContent;
    }

    @Override
    public Type getType() {
        return floatingPointDefinitionContent.asType();
    }

    @Override
    public String getDefinitionContent() {
        return floatingPointDefinitionContent.beforeParsing();
    }

    @Override
    public TypeTree expectedTypeTree() {
        return new DefaultTypeTree(expectedRootTypeVertex());
    }

    @Override
    public FloatingPointTypeVertex expectedRootTypeVertex() {
        return DefaultTypeVertexFactory.createFloatingPointTerminalVertex();
    }

    @Override
    public void assertTypeTree(TypeTree actualTypeTree) {

    }
}
