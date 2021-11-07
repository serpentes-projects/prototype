package io.serpentes.api.definition.trees.edges;

import io.serpentes.api.definition.trees.vertices.TypeVertex;

public interface TypeEdge {
    TypeVertex getVertexX();

    TypeVertex getVertexY();

    void accept(final TypeEdgeVisitor typeEdgeVisitor);
}
