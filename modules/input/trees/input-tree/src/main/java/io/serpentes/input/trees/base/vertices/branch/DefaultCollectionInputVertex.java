package io.serpentes.input.trees.base.vertices.branch;

import io.serpentes.api.input.trees.edges.CollectionItemInputEdge;
import io.serpentes.api.input.trees.vertices.InputVertexVisitor;
import io.serpentes.api.input.trees.vertices.branch.CollectionInputVertex;
import io.serpentes.api.input.trees.vertices.branch.InputBranchVertexVisitor;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode
public class DefaultCollectionInputVertex implements CollectionInputVertex {
    private final List<CollectionItemInputEdge> children = new ArrayList<>();

    @Override
    public List<CollectionItemInputEdge> getEdges() {
        return children;
    }

    @Override
    public void addEdge(CollectionItemInputEdge inputEdge) {
        this.children.add(inputEdge.getIndex(), inputEdge);
    }

    @Override
    public void accept(InputVertexVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void accept(InputBranchVertexVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public CollectionItemInputEdge getEdge(int index) {
        return children.get(index);
    }
}
