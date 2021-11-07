package io.serpentes.testing.assertions.input.tree;


import io.serpentes.api.input.trees.InputTree;
import io.serpentes.api.input.trees.InputTreeVisitor;
import io.serpentes.api.input.trees.edges.InputEdge;
import io.serpentes.api.input.trees.vertices.InputVertex;
import io.serpentes.api.input.trees.vertices.InputNullVertex;
import io.serpentes.api.input.trees.vertices.branch.CollectionInputVertex;
import io.serpentes.api.input.trees.edges.CollectionItemInputEdge;
import io.serpentes.api.input.trees.edges.KeyValueInputEdge;
import io.serpentes.api.input.trees.vertices.branch.MapInputVertex;
import io.serpentes.api.input.trees.vertices.terminal.*;
import io.serpentes.api.input.trees.vertices.terminal.*;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class InputTreeIterator implements InputTreeVisitor, Iterator<InputVertex> {
    private final Queue<InputVertex> inputVertices = new LinkedList<>();
    private final Queue<InputEdge> inputEdges = new LinkedList<>(); //FIXME: This will break something..

    public InputTreeIterator(InputTree inputTree) {
        inputTree.accept(this);
    }

    @Override
    public void visit(InputNullVertex inputNullVertex) {
        this.inputVertices.add(inputNullVertex);
    }

    @Override
    public void visit(MapInputVertex mapInputVertex) {
        this.inputVertices.add(mapInputVertex);
        mapInputVertex.getEdges().forEach(child -> child.accept(this));
    }

    @Override
    public void visit(KeyValueInputEdge keyValueInputEdge) {
        this.inputEdges.add(keyValueInputEdge);
        keyValueInputEdge.getValue().accept(this);
    }

    @Override
    public void visit(CollectionInputVertex collectionInputVertex) {
        this.inputVertices.add(collectionInputVertex);
        collectionInputVertex.getEdges().forEach(child -> child.accept(this));
    }

    @Override
    public void visit(CollectionItemInputEdge collectionItemInputEdge) {
        this.inputEdges.add(collectionItemInputEdge);
        collectionItemInputEdge.getItem().accept(this);
    }

    @Override
    public void visit(NullInputTerminalVertex nullInputTerminalVertex) {
        this.inputVertices.add(nullInputTerminalVertex);
    }

    @Override
    public void visit(BooleanInputTerminalVertex booleanInputTerminalVertex) {
        this.inputVertices.add(booleanInputTerminalVertex);
    }

    @Override
    public void visit(IntegerInputTerminalVertex integerInputTerminalVertex) {
        this.inputVertices.add(integerInputTerminalVertex);
    }

    @Override
    public void visit(FloatingPointInputTerminalVertex floatingPointInputTerminalVertex) {
        this.inputVertices.add(floatingPointInputTerminalVertex);
    }

    @Override
    public void visit(StringInputTerminalVertex stringInputTerminalVertex) {
        this.inputVertices.add(stringInputTerminalVertex);
    }

    @Override
    public boolean hasNext() {
        return !this.inputVertices.isEmpty();
    }

    @Override
    public InputVertex next() {
        return this.inputVertices.poll();
    }
}
