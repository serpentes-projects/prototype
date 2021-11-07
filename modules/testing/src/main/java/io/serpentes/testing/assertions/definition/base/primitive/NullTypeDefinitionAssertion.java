package io.serpentes.testing.assertions.definition.base.primitive;

import io.serpentes.api.definition.trees.TypeTree;
import io.serpentes.api.definition.trees.vertices.terminal.NullTypeVertex;
import io.serpentes.definition.trees.base.DefaultTypeTree;
import io.serpentes.testing.assertions.definition.content.api.primitive.NullDefinitionContent;
import io.serpentes.testing.assertions.definition.type.tree.DefaultTypeVertexFactory;
import io.serpentes.testing.assertions.definition.base.DeserializableTypeDefinitionAssertion;

import java.lang.reflect.Type;

public class NullTypeDefinitionAssertion implements DeserializableTypeDefinitionAssertion {
    private final NullDefinitionContent nullDefinitionContent;

    public NullTypeDefinitionAssertion(final NullDefinitionContent nullDefinitionContent) {
        this.nullDefinitionContent = nullDefinitionContent;
    }

    @Override
    public String getDefinitionContent() {
        return nullDefinitionContent.beforeParsing();
    }

    @Override
    public TypeTree expectedTypeTree() {
        return new DefaultTypeTree(expectedRootTypeVertex());
    }

    @Override
    public NullTypeVertex expectedRootTypeVertex() {
        return DefaultTypeVertexFactory.createNullTerminalVertex();
    }

    @Override
    public void assertTypeTree(TypeTree actualTypeTree) {

    }

    @Override
    public Type getType() {
        return nullDefinitionContent.asType();
    }

}
