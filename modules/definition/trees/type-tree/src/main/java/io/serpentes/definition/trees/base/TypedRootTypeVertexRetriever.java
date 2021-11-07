package io.serpentes.definition.trees.base;

import io.serpentes.api.definition.trees.TypeTreeVisitor;
import io.serpentes.api.definition.trees.edges.CollectionItemTypeEdge;
import io.serpentes.api.definition.trees.edges.KeyValueTypeEdge;
import io.serpentes.api.definition.trees.edges.TypeEdge;
import io.serpentes.api.definition.trees.vertices.TypeVertex;
import io.serpentes.api.definition.trees.vertices.UnknownTypeVertex;
import io.serpentes.api.definition.trees.vertices.branch.CollectionTypeVertex;
import io.serpentes.api.definition.trees.vertices.branch.MapTypeVertex;
import io.serpentes.api.definition.trees.vertices.terminal.*;
import io.serpentes.api.definition.trees.vertices.terminal.*;


public class TypedRootTypeVertexRetriever implements TypeTreeVisitor {
    private NullTypeVertex nullTypeVertex;
    private BooleanTypeVertex booleanTypeVertex;
    private IntegerTypeVertex integerTypeVertex;
    private FloatingPointTypeVertex floatingPointTypeVertex;
    private StringTypeVertex stringTypeVertex;
    private MapTypeVertex mapTypeVertex;
    private KeyValueTypeEdge keyValueTypeEdge;
    private CollectionTypeVertex collectionTypeVertex;
    private CollectionItemTypeEdge collectionItemTypeEdge;
    private TypeVertex typeVertex;
    private TypeEdge typeEdge;

    public TypedRootTypeVertexRetriever() {
    }

    @Override
    public void visit(MapTypeVertex mapTypeVertex) {
        this.mapTypeVertex = mapTypeVertex;
        this.typeVertex = mapTypeVertex;
    }

    @Override
    public void visit(KeyValueTypeEdge keyValueTypeEdge) {
        this.keyValueTypeEdge = keyValueTypeEdge;
        this.typeEdge = keyValueTypeEdge;
    }

    @Override
    public void visit(CollectionTypeVertex collectionTypeVertex) {
        this.collectionTypeVertex = collectionTypeVertex;
        this.typeVertex = collectionTypeVertex;
    }

    @Override
    public void visit(CollectionItemTypeEdge collectionItemTypeEdge) {
        this.collectionItemTypeEdge = collectionItemTypeEdge;
        this.typeEdge = collectionItemTypeEdge;
    }

    @Override
    public void visit(NullTypeVertex nullTypeVertex) {
        this.nullTypeVertex = nullTypeVertex;
        this.typeVertex = nullTypeVertex;
    }

    @Override
    public void visit(BooleanTypeVertex booleanTypeVertex) {
        this.booleanTypeVertex = booleanTypeVertex;
        this.typeVertex = booleanTypeVertex;
    }

    @Override
    public void visit(IntegerTypeVertex integerTypeVertex) {
        this.integerTypeVertex = integerTypeVertex;
        this.typeVertex = integerTypeVertex;
    }

    @Override
    public void visit(FloatingPointTypeVertex floatingPointTypeVertex) {
        this.floatingPointTypeVertex = floatingPointTypeVertex;
        this.typeVertex = floatingPointTypeVertex;
    }

    @Override
    public void visit(StringTypeVertex stringTypeVertex) {
        this.stringTypeVertex = stringTypeVertex;
        this.typeVertex = stringTypeVertex;
    }

    @Override
    public void visit(UnknownTypeVertex unknownTypeVertex) {
    }

    public NullTypeVertex getNullTypeVertex() {
        return nullTypeVertex;
    }

    public BooleanTypeVertex getBooleanTypeVertex() {
        return booleanTypeVertex;
    }

    public IntegerTypeVertex getIntegerTypeVertex() {
        return integerTypeVertex;
    }

    public FloatingPointTypeVertex getFloatingPointTypeVertex() {
        return floatingPointTypeVertex;
    }

    public StringTypeVertex getStringTypeVertex() {
        return stringTypeVertex;
    }

    public MapTypeVertex getMapTypeVertex() {
        return mapTypeVertex;
    }

    public KeyValueTypeEdge getKeyValueTypeEdge() {
        return keyValueTypeEdge;
    }

    public CollectionTypeVertex getCollectionTypeVertex() {
        return collectionTypeVertex;
    }

    public CollectionItemTypeEdge getCollectionItemTypeEdge() {
        return collectionItemTypeEdge;
    }

    public TypeVertex getTypeVertex() {
        return typeVertex;
    }

    public TypeEdge getTypeEdge() {
        return typeEdge;
    }

    public void refresh(final TypeVertex typeVertex) {
        this.clear();
        typeVertex.accept(this);
    }

    public void clear() {
        nullTypeVertex = null;
        booleanTypeVertex = null;
        integerTypeVertex = null;
        floatingPointTypeVertex = null;
        stringTypeVertex = null;
        mapTypeVertex = null;
        collectionTypeVertex = null;
    }
}
