package io.serpentes.testing.assertions.input.tree;


import io.serpentes.api.input.trees.InputTreeVisitor;
import io.serpentes.api.input.trees.vertices.InputNullVertex;
import io.serpentes.api.input.trees.vertices.branch.CollectionInputVertex;
import io.serpentes.api.input.trees.edges.CollectionItemInputEdge;
import io.serpentes.api.input.trees.edges.KeyValueInputEdge;
import io.serpentes.api.input.trees.vertices.branch.MapInputVertex;
import io.serpentes.api.input.trees.vertices.terminal.*;
import io.serpentes.api.input.trees.vertices.terminal.*;

public class InputTreeVertexCounter implements InputTreeVisitor {
    private final InputTreeAssertion inputTreeAssertion;

    public InputTreeVertexCounter(InputTreeAssertion inputTreeAssertion) {
        this.inputTreeAssertion = inputTreeAssertion;
    }

    private int increment(int toIncrement) {
        return ++toIncrement;
    }

    @Override
    public void visit(InputNullVertex inputNullVertex) {
        inputTreeAssertion.actualAmountOfNullVertices(increment(inputTreeAssertion.actualAmountOfNullVertices()));
    }

    @Override
    public void visit(MapInputVertex mapInputVertex) {
        inputTreeAssertion.actualAmountOfMapBranchVertices(increment(inputTreeAssertion.actualAmountOfMapBranchVertices()));
        mapInputVertex.getEdges().forEach(keyValueInputEdge -> keyValueInputEdge.accept(this));
    }

    @Override
    public void visit(KeyValueInputEdge keyValueInputEdge) {
        inputTreeAssertion.actualAmountOfKeyValueBranchVertices(increment(inputTreeAssertion.actualAmountOfKeyValueBranchVertices()));
        keyValueInputEdge.getValue().accept(this);
    }

    @Override
    public void visit(CollectionInputVertex collectionInputVertex) {
        inputTreeAssertion.actualAmountOfCollectionBranchVertices(increment(inputTreeAssertion.actualAmountOfCollectionBranchVertices()));
        collectionInputVertex.getEdges().forEach(collectionItemInputEdge -> collectionItemInputEdge.accept(this));
    }

    @Override
    public void visit(CollectionItemInputEdge collectionItemInputEdge) {
        inputTreeAssertion.actualAmountOfCollectionItemBranchVertices(increment(inputTreeAssertion.actualAmountOfCollectionItemBranchVertices()));
        collectionItemInputEdge.getItem().accept(this);
    }

    @Override
    public void visit(NullInputTerminalVertex nullInputTerminalVertex) {
        inputTreeAssertion.actualAmountOfNullInputTerminalVertices(increment(inputTreeAssertion.actualAmountOfNullInputTerminalVertices()));
    }

    @Override
    public void visit(BooleanInputTerminalVertex booleanInputTerminalVertex) {
        inputTreeAssertion.actualAmountOfBooleanInputTerminalVertices(increment(inputTreeAssertion.actualAmountOfBooleanInputTerminalVertices()));
    }

    @Override
    public void visit(IntegerInputTerminalVertex integerInputTerminalVertex) {
        inputTreeAssertion.actualAmountOfIntegerInputTerminalVertices(increment(inputTreeAssertion.actualAmountOfIntegerInputTerminalVertices()));
    }

    @Override
    public void visit(FloatingPointInputTerminalVertex floatingPointInputTerminalVertex) {
        inputTreeAssertion.actualAmountOfFloatingPointInputTerminalVertices(increment(inputTreeAssertion.actualAmountOfFloatingPointInputTerminalVertices()));
    }

    @Override
    public void visit(StringInputTerminalVertex stringInputTerminalVertex) {
        inputTreeAssertion.actualAmountOfStringInputTerminalVertices(increment(inputTreeAssertion.actualAmountOfStringInputTerminalVertices()));
    }
}
