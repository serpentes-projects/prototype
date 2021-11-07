package io.serpentes.definition.trees.base.vertices.branch;

import io.serpentes.api.definition.trees.TypeTreeVisitor;
import io.serpentes.api.definition.trees.edges.CollectionItemTypeEdge;
import io.serpentes.api.definition.trees.vertices.TypeVertex;
import io.serpentes.api.definition.trees.vertices.branch.BranchTypeVertexVisitor;
import io.serpentes.api.definition.trees.vertices.branch.CollectionTypeVertex;
import io.serpentes.definition.trees.base.edges.DefaultCollectionItemTypeEdge;
import io.serpentes.definition.trees.base.TypeVertexCloner;

import java.util.*;

public class DefaultCollectionTypeVertex implements CollectionTypeVertex {
    private final Set<TypeVertex> allowedTypes = new HashSet<>();    //TODO: Make sure that this is never empty.
    private final Map<Integer, CollectionItemTypeEdge> children = new HashMap<>();

    @Override
    public void addAllowedVertexType(TypeVertex typeVertex) {
        this.allowedTypes.add(typeVertex);
    }

    @Override
    public Set<TypeVertex> getAllowedVertexTypes() {
        return Collections.unmodifiableSet(allowedTypes);
    }

    @Override
    public List<CollectionItemTypeEdge> getEdges() {
        return new ArrayList<>(children.values());
    }

    @Override
    public void addEdge(CollectionItemTypeEdge edge) {
        this.children.put(edge.getIndex(), edge);
    }

    @Override
    public void accept(TypeTreeVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void accept(BranchTypeVertexVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public CollectionItemTypeEdge getEdge(int index) {
        return children.get(index);
    }

    @Override
    public void createEdge(int index) {
        if (!this.children.containsKey(index)) {
            //FIXME: If support for type-definitions with vertices with multiple types is to be provided.
            final var defaultCollectionItemTypeEdge = new DefaultCollectionItemTypeEdge(index, new TypeVertexCloner().clone(allowedTypes.stream().findFirst().get()));
            this.children.put(index, defaultCollectionItemTypeEdge);
        }
    }
}
