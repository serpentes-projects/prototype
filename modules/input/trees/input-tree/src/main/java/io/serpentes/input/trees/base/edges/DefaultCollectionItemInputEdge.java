package io.serpentes.input.trees.base.edges;

import io.serpentes.api.input.trees.edges.CollectionItemInputEdge;
import io.serpentes.api.input.trees.edges.InputEdgeVisitor;
import io.serpentes.api.input.trees.vertices.InputVertex;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class DefaultCollectionItemInputEdge implements CollectionItemInputEdge {
    private final int index;
    private final InputVertex item;

    public DefaultCollectionItemInputEdge(final int index, final InputVertex item) {
        this.index = index;
        this.item = item;
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public InputVertex getItem() {
        return item;
    }

    @Override
    public InputVertex getVertexX() {
        return null;
    }

    @Override
    public InputVertex getVertexY() {
        return item;
    }

    @Override
    public void accept(InputEdgeVisitor visitor) {
        visitor.visit(this);
    }
}
