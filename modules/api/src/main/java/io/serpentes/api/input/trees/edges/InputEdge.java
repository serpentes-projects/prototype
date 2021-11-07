package io.serpentes.api.input.trees.edges;

import io.serpentes.api.input.trees.vertices.InputVertex;

public interface InputEdge {
    InputVertex getVertexX();
    InputVertex getVertexY();

    void accept(InputEdgeVisitor visitor);
}
