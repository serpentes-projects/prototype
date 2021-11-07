package io.serpentes.api.input.trees.vertices.branch;

import io.serpentes.api.input.trees.edges.CollectionItemInputEdge;

public interface CollectionInputVertex extends InputBranchVertex<CollectionItemInputEdge> {
    CollectionItemInputEdge getEdge(final int index);
}
