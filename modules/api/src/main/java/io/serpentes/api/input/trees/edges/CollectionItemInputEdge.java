package io.serpentes.api.input.trees.edges;

import io.serpentes.api.input.trees.vertices.InputVertex;

public interface CollectionItemInputEdge extends IndexedInputEdge {
    InputVertex getItem();
}
