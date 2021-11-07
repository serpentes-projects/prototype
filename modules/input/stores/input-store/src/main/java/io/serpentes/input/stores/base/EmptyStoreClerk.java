package io.serpentes.input.stores.base;

import io.serpentes.api.configuration.store.StoreClerk;
import io.serpentes.api.definition.sources.DefinitionSources;
import io.serpentes.api.definition.trees.TypeTreeVisitor;
import io.serpentes.api.definition.trees.edges.CollectionItemTypeEdge;
import io.serpentes.api.definition.trees.edges.KeyValueTypeEdge;
import io.serpentes.api.definition.trees.vertices.UnknownTypeVertex;
import io.serpentes.api.definition.trees.vertices.TypeVertex;
import io.serpentes.api.definition.trees.vertices.branch.CollectionTypeVertex;
import io.serpentes.api.definition.trees.vertices.branch.MapTypeVertex;
import io.serpentes.api.definition.trees.vertices.terminal.*;
import io.serpentes.api.definition.trees.vertices.terminal.*;
import jakarta.inject.Inject;

public class EmptyStoreClerk implements StoreClerk, TypeTreeVisitor {
    private final InputStore inputStore;
    private final DefinitionSources definitionSources;

    @Inject
    public EmptyStoreClerk(DefinitionSources definitionSources, InputStore inputStore){
        this.definitionSources = definitionSources;
        this.inputStore = inputStore;
    }

    @Override
    public void stockStore() {
        definitionSources.getTypeTree().accept(this);
    }

    @Override
    public void visit(UnknownTypeVertex unknownTypeVertex) {
        addTypeVertexToStore(unknownTypeVertex);
    }

    @Override
    public void visit(MapTypeVertex mapTypeVertex) {
        addTypeVertexToStore(mapTypeVertex);
        mapTypeVertex.getEdges().forEach(keyValueTypeEdge -> keyValueTypeEdge.accept(this));
    }

    @Override
    public void visit(KeyValueTypeEdge keyValueTypeEdge) {
        keyValueTypeEdge.getValue().accept(this);
    }

    @Override
    public void visit(CollectionTypeVertex collectionTypeVertex) {
        addTypeVertexToStore(collectionTypeVertex);
        collectionTypeVertex.getEdges().forEach(collectionItemTypeEdge -> collectionItemTypeEdge.accept(this));
    }

    @Override
    public void visit(CollectionItemTypeEdge collectionItemTypeEdge) {
        collectionItemTypeEdge.getItem().accept(this);
    }

    @Override
    public void visit(NullTypeVertex nullTypeVertex) {
        addTypeVertexToStore(nullTypeVertex);
    }

    @Override
    public void visit(BooleanTypeVertex booleanTypeVertex) {
        addTypeVertexToStore(booleanTypeVertex);
    }

    @Override
    public void visit(IntegerTypeVertex integerTypeVertex) {
        addTypeVertexToStore(integerTypeVertex);
    }

    @Override
    public void visit(FloatingPointTypeVertex floatingPointTypeVertex) {
        addTypeVertexToStore(floatingPointTypeVertex);
    }

    @Override
    public void visit(StringTypeVertex stringTypeVertex) {
        addTypeVertexToStore(stringTypeVertex);
    }

    private void addTypeVertexToStore(TypeVertex typeVertex) {
        inputStore.put(typeVertex, new InputSourceValues());
    }
}
