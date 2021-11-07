package io.serpentes.api.definition.trees.edges;

import io.serpentes.api.definition.trees.vertices.TypeVertex;

public interface CollectionItemTypeEdge extends IndexedTypeEdge {
    TypeVertex getItem();
}
