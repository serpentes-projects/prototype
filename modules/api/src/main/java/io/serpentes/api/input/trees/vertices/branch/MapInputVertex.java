package io.serpentes.api.input.trees.vertices.branch;

import io.serpentes.api.input.trees.edges.KeyValueInputEdge;

public interface MapInputVertex extends InputBranchVertex<KeyValueInputEdge> {
    KeyValueInputEdge getEdge(final String keyName);
}
