package io.serpentes.api.definition.trees.edges;

public interface TypeEdgeVisitor {
    void visit(final KeyValueTypeEdge keyValueTypeEdge);

    void visit(final CollectionItemTypeEdge collectionItemTypeEdge);
}
