package io.serpentes.testing.assertions.input.tree;

import io.serpentes.api.input.trees.InputTreeVisitor;
import io.serpentes.api.input.trees.edges.CollectionItemInputEdge;
import io.serpentes.api.input.trees.edges.KeyValueInputEdge;
import io.serpentes.api.input.trees.vertices.InputVertex;
import io.serpentes.api.input.trees.vertices.InputNullVertex;
import io.serpentes.api.input.trees.vertices.branch.CollectionInputVertex;
import io.serpentes.api.input.trees.vertices.branch.InputBranchVertex;
import io.serpentes.api.input.trees.vertices.branch.MapInputVertex;
import io.serpentes.api.input.trees.vertices.terminal.*;
import io.serpentes.api.input.trees.vertices.terminal.*;
import io.serpentes.input.trees.base.TypedTreeComponentRetriever;
import org.junit.jupiter.api.Assertions;

import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class InputVertexAssertion implements InputTreeVisitor {
    private final Stack<InputVertex> actualInputVertexStack = new Stack<>();
    private final Stack<String> pathStack = new Stack<>();
    private final TypedTreeComponentRetriever treeComponentRetriever = new TypedTreeComponentRetriever();

    public void assertEquals(final InputVertex expectedInputVertex, final InputVertex actualInputVertex) {
        pathStack.push("$.");
        addInputVertexToStack(actualInputVertex);
        expectedInputVertex.accept(this);
        actualInputVertexStack.clear();
        pathStack.clear();
    }

    public void addInputVertexToStack(final InputVertex inputVertex) {
        actualInputVertexStack.push(inputVertex);
        refreshTreeComponentRetriever();
    }

    private void refreshTreeComponentRetriever() {
        treeComponentRetriever.refresh(actualInputVertexStack.peek());
    }

    @Override
    public void visit(MapInputVertex mapInputVertex) {
        visitInputBranchVertex(mapInputVertex);
    }

    private void visitInputBranchVertex(final InputBranchVertex<?> expectedInputBranchVertex) {
        expectedInputBranchVertex.getEdges().forEach(inputEdge -> inputEdge.accept(this));
    }

    @Override
    public void visit(KeyValueInputEdge keyValueInputEdge) {
        pathStack.push(keyValueInputEdge.getLabel());
        final var actualMapInputVertex = this.treeComponentRetriever.getMapInputVertex();
        KeyValueInputEdge actualKeyValueInputEdge;
        if (actualMapInputVertex != null) {
            actualKeyValueInputEdge = actualMapInputVertex.getEdge(keyValueInputEdge.getLabel());
        } else {
            actualKeyValueInputEdge = treeComponentRetriever.getKeyValueInputEdge();
        }

        Assertions.assertNotNull(actualKeyValueInputEdge, createShouldNotBeNullMessage());
        visitChildInputVertex(keyValueInputEdge.getValue(), actualKeyValueInputEdge.getValue());
    }

    private String createShouldNotBeNullMessage() {
        return "Should not encounter a non-existent child for path:" + createPath();
    }

    private String createPath() {
        final List<String> collectedPathElements = pathStack.stream().peek(s -> {
        }).collect(Collectors.toList());

        final StringBuilder pathBuilder = new StringBuilder();
        collectedPathElements.forEach(pathBuilder::append);
        return pathBuilder.toString();
    }

    private void visitChildInputVertex(final InputVertex expectedInputVertex, final InputVertex actualChildInputVertex) {
        addInputVertexToStack(actualChildInputVertex);
        expectedInputVertex.accept(this);
        removeInputVertexFromStack();
    }

    public void removeInputVertexFromStack() {
        actualInputVertexStack.pop();
        refreshTreeComponentRetriever();
    }

    @Override
    public void visit(CollectionInputVertex collectionInputVertex) {
        visitInputBranchVertex(collectionInputVertex);
    }

    @Override
    public void visit(CollectionItemInputEdge collectionItemInputEdge) {
        final var actualCollectionInputVertex = this.treeComponentRetriever.getCollectionInputVertex();
        final var actualCollectionInputVertexEdge = actualCollectionInputVertex.getEdge(collectionItemInputEdge.getIndex());
        Assertions.assertNotNull(collectionItemInputEdge, createShouldNotBeNullMessage());
        visitChildInputVertex(collectionItemInputEdge.getItem(), actualCollectionInputVertexEdge.getItem());
    }

    @Override
    public void visit(NullInputTerminalVertex nullInputTerminalVertex) {
        assertEqualsActualInputVertex(nullInputTerminalVertex, treeComponentRetriever.getNullInputTerminalVertex());
    }

    private void assertEqualsActualInputVertex(final InputVertex expectedInputVertex, final InputVertex actualInputVertex) {
        Assertions.assertEquals(expectedInputVertex, actualInputVertex, "Should have equal input vertices for path: " + createPath());
    }

    @Override
    public void visit(BooleanInputTerminalVertex booleanInputTerminalVertex) {
        assertEqualsActualInputVertex(booleanInputTerminalVertex, treeComponentRetriever.getBooleanInputTerminalVertex());
    }

    @Override
    public void visit(IntegerInputTerminalVertex integerInputTerminalVertex) {
        assertEqualsActualInputVertex(integerInputTerminalVertex, treeComponentRetriever.getIntegerInputTerminalVertex());
    }

    @Override
    public void visit(FloatingPointInputTerminalVertex floatingPointInputTerminalVertex) {
        assertEqualsActualInputVertex(floatingPointInputTerminalVertex, treeComponentRetriever.getFloatingPointInputTerminalVertex());
    }

    @Override
    public void visit(StringInputTerminalVertex stringInputTerminalVertex) {
        assertEqualsActualInputVertex(stringInputTerminalVertex, treeComponentRetriever.getStringInputTerminalVertex());
    }

    @Override
    public void visit(InputNullVertex inputNullVertex) {

    }
}
