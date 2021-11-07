package io.serpentes.api.input.trees.edges;

public interface InputEdgeVisitor {
    void visit(final KeyValueInputEdge keyValueInputEdge);

    void visit(final CollectionItemInputEdge collectionItemInputEdge);
}