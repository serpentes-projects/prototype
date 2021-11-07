package io.serpentes.testing.assertions.definition.base;

import io.serpentes.api.definition.trees.TypeTree;
import io.serpentes.api.definition.trees.vertices.TypeVertex;

public interface TypeDefinitionAssertion {
    String getDefinitionContent();

    TypeTree expectedTypeTree();

    TypeVertex expectedRootTypeVertex();

    void assertTypeTree(TypeTree actualTypeTree);
}
