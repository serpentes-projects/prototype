package io.serpentes.api.definition.trees.vertices.branch;

import io.serpentes.api.definition.trees.vertices.UnknownTypeVertexVisitor;

public interface BranchTypeVertexVisitor extends UnknownTypeVertexVisitor {
    void visit(MapTypeVertex mapTypeVertex);

    void visit(CollectionTypeVertex collectionTypeVertex);
}
