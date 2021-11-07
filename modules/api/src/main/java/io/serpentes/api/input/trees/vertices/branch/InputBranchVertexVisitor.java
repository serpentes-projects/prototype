package io.serpentes.api.input.trees.vertices.branch;


import io.serpentes.api.input.trees.vertices.NullInputVertexVisitor;

public interface InputBranchVertexVisitor extends NullInputVertexVisitor {
    void visit(MapInputVertex mapInputVertex);

    void visit(CollectionInputVertex collectionInputVertex);
}
