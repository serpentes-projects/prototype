package io.serpentes.definition.trees.base.edges;

import io.serpentes.api.definition.trees.edges.KeyValueTypeEdge;
import io.serpentes.api.definition.trees.edges.TypeEdgeVisitor;
import io.serpentes.api.definition.trees.vertices.TypeVertex;

public class DefaultKeyValueTypeEdge implements KeyValueTypeEdge {
    private final TypeVertex value;
    private final String key;

    public DefaultKeyValueTypeEdge(final String key, final TypeVertex value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String getKey() {
        return key;
    }

    public TypeVertex getValue() {
        return value;
    }

    @Override
    public String getLabel() {
        return key;
    }

    @Override
    public TypeVertex getVertexX() {
        return null;
    }

    @Override
    public TypeVertex getVertexY() {
        return value;
    }

    @Override
    public void accept(final TypeEdgeVisitor typeEdgeVisitor) {
        typeEdgeVisitor.visit(this);
    }
}
