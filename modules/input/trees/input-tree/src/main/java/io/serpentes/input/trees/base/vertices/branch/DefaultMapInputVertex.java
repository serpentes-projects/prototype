package io.serpentes.input.trees.base.vertices.branch;

import io.serpentes.api.input.trees.edges.KeyValueInputEdge;
import io.serpentes.api.input.trees.vertices.InputVertexVisitor;
import io.serpentes.api.input.trees.vertices.branch.InputBranchVertexVisitor;
import io.serpentes.api.input.trees.vertices.branch.MapInputVertex;
import lombok.EqualsAndHashCode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@EqualsAndHashCode
public class DefaultMapInputVertex implements MapInputVertex {
    private final Map<String, KeyValueInputEdge> childrenMap = new HashMap<>();
    @Override
    public List<KeyValueInputEdge> getEdges() {
        return List.copyOf(childrenMap.values());
    }

    @Override
    public void addEdge(KeyValueInputEdge inputEdge) {
        childrenMap.put(inputEdge.getLabel(), inputEdge);
    }

    @Override
    public void accept(InputBranchVertexVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public KeyValueInputEdge getEdge(String keyName) {
        return childrenMap.get(keyName);
    }

    @Override
    public void accept(InputVertexVisitor visitor) {
        visitor.visit(this);
    }
}
