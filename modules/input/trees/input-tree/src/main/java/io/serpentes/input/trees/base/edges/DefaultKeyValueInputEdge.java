package io.serpentes.input.trees.base.edges;

import io.serpentes.api.input.trees.edges.InputEdgeVisitor;
import io.serpentes.api.input.trees.edges.KeyValueInputEdge;
import io.serpentes.api.input.trees.vertices.InputVertex;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class DefaultKeyValueInputEdge implements KeyValueInputEdge {
    private final InputVertex value;
    private final String key;

    public DefaultKeyValueInputEdge(final String key, final InputVertex value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String getLabel() {
        return key;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public InputVertex getValue() {
        return value;
    }

    @Override
    public InputVertex getVertexX() {
        return null;
    }

    @Override
    public InputVertex getVertexY() {
        return value;
    }

    @Override
    public void accept(InputEdgeVisitor visitor) {
        visitor.visit(this);
    }
}
