package io.serpentes.api.definition.trees.vertices.branch;

import io.serpentes.api.definition.trees.edges.KeyValueTypeEdge;

public interface MapTypeVertex extends BranchTypeVertex<KeyValueTypeEdge> {
    KeyValueTypeEdge getEdge(final String keyName);
}
