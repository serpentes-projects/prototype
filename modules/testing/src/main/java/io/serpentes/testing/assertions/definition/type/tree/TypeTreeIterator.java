package io.serpentes.testing.assertions.definition.type.tree;

import io.serpentes.api.definition.trees.TypeTree;
import io.serpentes.api.definition.trees.TypeTreeVisitor;
import io.serpentes.api.definition.trees.edges.CollectionItemTypeEdge;
import io.serpentes.api.definition.trees.edges.KeyValueTypeEdge;
import io.serpentes.api.definition.trees.vertices.TypeVertex;
import io.serpentes.api.definition.trees.vertices.UnknownTypeVertex;
import io.serpentes.api.definition.trees.vertices.branch.CollectionTypeVertex;
import io.serpentes.api.definition.trees.vertices.branch.MapTypeVertex;
import io.serpentes.api.definition.trees.vertices.terminal.*;
import io.serpentes.api.definition.trees.vertices.terminal.*;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class TypeTreeIterator implements TypeTreeVisitor, Iterator<TypeVertex> {
    private final Queue<TypeVertex> typeVertexDeque = new LinkedList<>();

    public TypeTreeIterator(TypeTree typeTree) {
        typeTree.accept(this);
    }

    @Override
    public void visit(UnknownTypeVertex unknownTypeVertex) {
        this.typeVertexDeque.add(unknownTypeVertex);
    }

    @Override
    public void visit(MapTypeVertex mapTypeVertex) {
        this.typeVertexDeque.add(mapTypeVertex);
        mapTypeVertex.getEdges().forEach(child -> child.accept(this));
    }

    @Override
    public void visit(KeyValueTypeEdge keyValueTypeEdge) {
//        this.typeVertexDeque.add(keyValueTypeEdge); FIXME
        keyValueTypeEdge.getValue().accept(this);
    }

    @Override
    public void visit(CollectionTypeVertex collectionTypeVertex) {
        this.typeVertexDeque.add(collectionTypeVertex);
        collectionTypeVertex.getEdges().forEach(child -> child.accept(this));
    }

    @Override
    public void visit(CollectionItemTypeEdge collectionItemTypeEdge) {
//        this.typeVertexDeque.add(collectionItemTypeEdge); FIXME
        collectionItemTypeEdge.getItem().accept(this);
    }

    @Override
    public void visit(NullTypeVertex nullTypeVertex) {
        this.typeVertexDeque.add(nullTypeVertex);
    }

    @Override
    public void visit(BooleanTypeVertex booleanTypeVertex) {
        this.typeVertexDeque.add(booleanTypeVertex);
    }

    @Override
    public void visit(IntegerTypeVertex integerTypeVertex) {
        this.typeVertexDeque.add(integerTypeVertex);
    }

    @Override
    public void visit(FloatingPointTypeVertex floatingPointTypeVertex) {
        this.typeVertexDeque.add(floatingPointTypeVertex);
    }

    @Override
    public void visit(StringTypeVertex stringTypeVertex) {
        this.typeVertexDeque.add(stringTypeVertex);
    }

    @Override
    public boolean hasNext() {
        return !this.typeVertexDeque.isEmpty();
    }

    @Override
    public TypeVertex next() {
        return this.typeVertexDeque.poll();
    }
}
