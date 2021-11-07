package io.serpentes.api.input.trees.edges;

import io.serpentes.api.input.trees.vertices.InputVertex;

public interface KeyValueInputEdge extends LabeledInputEdge {
    String getKey();

    InputVertex getValue();
}
