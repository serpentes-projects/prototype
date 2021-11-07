package io.serpentes.definition.trees.base.edges;

import io.serpentes.api.definition.trees.edges.CollectionItemTypeEdge;
import io.serpentes.api.definition.trees.edges.TypeEdgeVisitor;
import io.serpentes.api.definition.trees.vertices.TypeVertex;

public class DefaultCollectionItemTypeEdge implements CollectionItemTypeEdge {
    private final TypeVertex item;
    private final int index;

    public DefaultCollectionItemTypeEdge(final int index, final TypeVertex item){
        this.index = index;
        this.item = item;
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public TypeVertex getItem() {
        return item;
    }

    @Override
    public TypeVertex getVertexX() {
        return null;
    }

    @Override
    public TypeVertex getVertexY() {
        return item;
    }

    @Override
    public void accept(TypeEdgeVisitor typeEdgeVisitor) {
        typeEdgeVisitor.visit(this);
    }
}
