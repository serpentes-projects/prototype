package io.serpentes.testing.assertions.definition.base;

import io.serpentes.api.definition.trees.TypeTree;
import io.serpentes.api.definition.trees.vertices.TypeVertex;
import io.serpentes.testing.assertions.definition.content.api.DefinitionContent;

import java.lang.reflect.Type;

public class EmptyTypeDefinitionAssertion implements DeserializableTypeDefinitionAssertion {
    private final DefinitionContent definitionContent;

    public EmptyTypeDefinitionAssertion(DefinitionContent definitionContent) {
        this.definitionContent = definitionContent;
    }

    @Override
    public String getDefinitionContent() {
        return definitionContent.beforeParsing();
    }

    @Override
    public TypeTree expectedTypeTree() {
        return null;
    }

    @Override
    public TypeVertex expectedRootTypeVertex() {
        return null;
    }

    @Override
    public void assertTypeTree(TypeTree actualTypeTree) {
    }

    @Override
    public Type getType() {
        throw new RuntimeException("There is no class value for an empty input value.");
    }
}
