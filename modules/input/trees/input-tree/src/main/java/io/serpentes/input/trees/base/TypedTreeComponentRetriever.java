package io.serpentes.input.trees.base;

import io.serpentes.api.input.trees.InputTreeVisitor;
import io.serpentes.api.input.trees.vertices.InputVertex;
import io.serpentes.api.input.trees.vertices.InputNullVertex;
import io.serpentes.api.input.trees.vertices.branch.CollectionInputVertex;
import io.serpentes.api.input.trees.edges.CollectionItemInputEdge;
import io.serpentes.api.input.trees.edges.KeyValueInputEdge;
import io.serpentes.api.input.trees.vertices.branch.MapInputVertex;
import io.serpentes.api.input.trees.vertices.terminal.*;
import io.serpentes.api.input.trees.vertices.terminal.*;


public class TypedTreeComponentRetriever implements InputTreeVisitor {
    private NullInputTerminalVertex nullInputTerminalVertex;
    private BooleanInputTerminalVertex booleanInputTerminalVertex;
    private IntegerInputTerminalVertex integerInputTerminalVertex;
    private FloatingPointInputTerminalVertex floatingPointInputTerminalVertex;
    private StringInputTerminalVertex stringInputTerminalVertex;
    private MapInputVertex mapInputVertex;
    private KeyValueInputEdge keyValueInputEdge;
    private CollectionInputVertex collectionInputVertex;
    private CollectionItemInputEdge collectionItemInputEdge;

    public TypedTreeComponentRetriever() {

    }

    @Override
    public void visit(MapInputVertex mapInputVertex) {
        this.mapInputVertex = mapInputVertex;
    }

    @Override
    public void visit(KeyValueInputEdge keyValueInputEdge) {
        this.keyValueInputEdge = keyValueInputEdge;
    }

    @Override
    public void visit(CollectionInputVertex collectionInputVertex) {
        this.collectionInputVertex = collectionInputVertex;
    }

    @Override
    public void visit(CollectionItemInputEdge collectionItemInputEdge) {
        this.collectionItemInputEdge = collectionItemInputEdge;
    }

    @Override
    public void visit(NullInputTerminalVertex nullInputTerminalVertex) {
        this.nullInputTerminalVertex = nullInputTerminalVertex;
    }

    @Override
    public void visit(BooleanInputTerminalVertex booleanInputTerminalVertex) {
        this.booleanInputTerminalVertex = booleanInputTerminalVertex;
    }

    @Override
    public void visit(IntegerInputTerminalVertex integerInputTerminalVertex) {
        this.integerInputTerminalVertex = integerInputTerminalVertex;
    }

    @Override
    public void visit(FloatingPointInputTerminalVertex floatingPointInputTerminalVertex) {
        this.floatingPointInputTerminalVertex = floatingPointInputTerminalVertex;
    }

    @Override
    public void visit(StringInputTerminalVertex stringInputTerminalVertex) {
        this.stringInputTerminalVertex = stringInputTerminalVertex;
    }

    @Override
    public void visit(InputNullVertex inputNullVertex) {
    }

    public NullInputTerminalVertex getNullInputTerminalVertex() {
        return nullInputTerminalVertex;
    }

    public BooleanInputTerminalVertex getBooleanInputTerminalVertex() {
        return booleanInputTerminalVertex;
    }

    public IntegerInputTerminalVertex getIntegerInputTerminalVertex() {
        return integerInputTerminalVertex;
    }

    public FloatingPointInputTerminalVertex getFloatingPointInputTerminalVertex() {
        return floatingPointInputTerminalVertex;
    }

    public StringInputTerminalVertex getStringInputTerminalVertex() {
        return stringInputTerminalVertex;
    }

    public MapInputVertex getMapInputVertex() {
        return mapInputVertex;
    }

    public KeyValueInputEdge getKeyValueInputEdge() {
        return keyValueInputEdge;
    }

    public CollectionInputVertex getCollectionInputVertex() {
        return collectionInputVertex;
    }

    public CollectionItemInputEdge getCollectionItemInputEdge() {
        return collectionItemInputEdge;
    }

    public void refresh(final InputVertex inputVertex) {
        this.clear();
        inputVertex.accept(this);
    }

    public void clear() {
        nullInputTerminalVertex = null;
        booleanInputTerminalVertex = null;
        integerInputTerminalVertex = null;
        floatingPointInputTerminalVertex = null;
        stringInputTerminalVertex = null;
        mapInputVertex = null;
        collectionInputVertex = null;
    }
}
