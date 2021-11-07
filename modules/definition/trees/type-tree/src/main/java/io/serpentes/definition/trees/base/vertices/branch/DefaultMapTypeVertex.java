package io.serpentes.definition.trees.base.vertices.branch;

import io.serpentes.api.definition.trees.TypeTreeVisitor;
import io.serpentes.api.definition.trees.edges.KeyValueTypeEdge;
import io.serpentes.api.definition.trees.vertices.branch.BranchTypeVertexVisitor;
import io.serpentes.api.definition.trees.vertices.branch.MapTypeVertex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultMapTypeVertex implements MapTypeVertex {
    private final List<KeyValueTypeEdge> children = new ArrayList<>();
    private final Map<String, KeyValueTypeEdge> childrenMap = new HashMap<>();
    @Override
    public List<KeyValueTypeEdge> getEdges() {
        return children;
    }

    @Override
    public void addEdge(final KeyValueTypeEdge edge) {
        childrenMap.put(edge.getKey(), edge);
        children.add(edge);
    }

    @Override
    public KeyValueTypeEdge getEdge(String keyName) {
        return childrenMap.get(keyName);
    }

    @Override
    public void accept(final TypeTreeVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void accept(final BranchTypeVertexVisitor visitor) {
        visitor.visit(this);
    }

}
