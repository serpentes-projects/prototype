package io.serpentes.api.definition.trees.edges;

import io.serpentes.api.definition.trees.vertices.TypeVertex;

public interface KeyValueTypeEdge extends LabeledTypeEdge {
    String getKey();

    TypeVertex getValue();
}
