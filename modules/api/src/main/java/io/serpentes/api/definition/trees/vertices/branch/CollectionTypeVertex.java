package io.serpentes.api.definition.trees.vertices.branch;

import io.serpentes.api.definition.trees.vertices.TypeVertex;
import io.serpentes.api.definition.trees.edges.CollectionItemTypeEdge;

import java.util.Set;

public interface CollectionTypeVertex extends BranchTypeVertex<CollectionItemTypeEdge> {
    CollectionItemTypeEdge getEdge(final int index);

    void createEdge(final int index);

    void addAllowedVertexType(final TypeVertex typeVertex);

    Set<TypeVertex> getAllowedVertexTypes();
}
