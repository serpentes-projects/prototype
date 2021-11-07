package io.serpentes.testing.assertions.input.tree;

import io.serpentes.api.definition.trees.TypeTreeVisitor;
import io.serpentes.api.definition.trees.edges.CollectionItemTypeEdge;
import io.serpentes.api.definition.trees.edges.KeyValueTypeEdge;
import io.serpentes.api.definition.trees.vertices.TypeVertex;
import io.serpentes.api.definition.trees.vertices.UnknownTypeVertex;
import io.serpentes.api.definition.trees.vertices.branch.BranchTypeVertex;
import io.serpentes.api.definition.trees.vertices.branch.CollectionTypeVertex;
import io.serpentes.api.definition.trees.vertices.branch.MapTypeVertex;
import io.serpentes.api.definition.trees.vertices.terminal.*;
import io.serpentes.api.input.trees.vertices.InputVertex;
import io.serpentes.input.trees.base.TypedTreeComponentRetriever;
import org.junit.jupiter.api.Assertions;

import java.util.Stack;

public class TypeBasedInputVertexAssertion implements TypeTreeVisitor {
    private final Stack<InputVertex> expectedInputVertexStack = new Stack<>();
    private final Stack<InputVertex> actualInputVertexStack = new Stack<>();
    private final TypedTreeComponentRetriever expectedInputVertexRetriever = new TypedTreeComponentRetriever();
    private final TypedTreeComponentRetriever actualInputVertexRetriever = new TypedTreeComponentRetriever();
    private final InputVertexAssertion inputVertexAssertion = new InputVertexAssertion();


    public void assertEquals(final TypeVertex typeVertex, final InputVertex expectedInputVertex, final InputVertex actualInputVertex) {
        if (expectedInputVertex != null) {
            addInputVerticesToStacks(expectedInputVertex, actualInputVertex);
            typeVertex.accept(this);
            expectedInputVertexStack.clear();
            actualInputVertexStack.clear();
        } else {
            Assertions.assertNull(actualInputVertex);
        }
    }

    private void addInputVerticesToStacks(final InputVertex expectedInputVertex, final InputVertex actualInputVertex) {
        expectedInputVertexStack.push(expectedInputVertex);
        actualInputVertexStack.push(actualInputVertex);
        refreshVerticesRetrievers();
    }

    public void refreshVerticesRetrievers() {
        expectedInputVertexRetriever.refresh(expectedInputVertexStack.peek());
        actualInputVertexRetriever.refresh(actualInputVertexStack.peek());
    }

    @Override
    public void visit(MapTypeVertex mapTypeVertex) {
        assertInputVertices();
        final var expectedMapInputVertex = expectedInputVertexRetriever.getMapInputVertex();
        final var actualMapInputVertex = actualInputVertexRetriever.getMapInputVertex();
        mapTypeVertex.getEdges().forEach(keyValueTypeEdge -> {
            final var keyName = keyValueTypeEdge.getKey();
            final var expectedEdge = expectedMapInputVertex.getEdge(keyName);
            final var actualChild = actualMapInputVertex.getEdge(keyName);
            addInputVerticesToStacks(expectedEdge.getValue(), actualChild.getValue());
            keyValueTypeEdge.accept(this);
            removeInputVerticesFromStacks();
        });
    }

    public void removeInputVerticesFromStacks() {
        expectedInputVertexStack.pop();
        actualInputVertexStack.pop();
        refreshVerticesRetrievers();
    }

    private void assertInputVertices() {
        inputVertexAssertion.assertEquals(expectedInputVertexStack.peek(), actualInputVertexStack.peek());
    }

    @Override
    public void visit(KeyValueTypeEdge keyValueTypeEdge) {
        keyValueTypeEdge.getValue().accept(this);
    }

    @Override
    public void visit(CollectionTypeVertex collectionTypeVertex) {
        assertInputVertices();
        final var expectedCollectionInputVertex = expectedInputVertexRetriever.getCollectionInputVertex();
        final var actualInputVertexCollection = actualInputVertexRetriever.getCollectionInputVertex();
        collectionTypeVertex.getEdges().forEach(collectionItemTypeEdge -> {
            final int index = collectionItemTypeEdge.getIndex();
            final var expectedChild = expectedCollectionInputVertex.getEdge(index);
            final var actualChild = actualInputVertexCollection.getEdge(index);
            addInputVerticesToStacks(expectedChild.getItem(), actualChild.getItem());
            collectionItemTypeEdge.accept(this);
            removeInputVerticesFromStacks();
        });
    }

    @Override
    public void visit(CollectionItemTypeEdge collectionItemTypeEdge) {
        assertInputVertices();
        final var expectedCollectionItemInputEdge = expectedInputVertexRetriever.getCollectionItemInputEdge();
        final var actualCollectionItemInputEdge = actualInputVertexRetriever.getCollectionItemInputEdge();
        final var expectedChild = expectedCollectionItemInputEdge.getItem();
        final var actualChild = actualCollectionItemInputEdge.getItem();
        addInputVerticesToStacks(expectedChild, actualChild);
        collectionItemTypeEdge.getItem().accept(this);
        removeInputVerticesFromStacks();
    }

    private void visitBranchTypeVertex(final BranchTypeVertex<?> branchTypeVertex) {
        branchTypeVertex.getEdges().forEach(typeEdge -> typeEdge.accept(this));
    }

    @Override
    public void visit(NullTypeVertex nullTypeVertex) {
        assertInputVertices();
    }

    @Override
    public void visit(BooleanTypeVertex booleanTypeVertex) {
        assertInputVertices();
    }

    @Override
    public void visit(IntegerTypeVertex integerTypeVertex) {
        assertInputVertices();
    }

    @Override
    public void visit(FloatingPointTypeVertex floatingPointTypeVertex) {
        assertInputVertices();
    }

    @Override
    public void visit(StringTypeVertex stringTypeVertex) {
        assertInputVertices();
    }

    @Override
    public void visit(UnknownTypeVertex unknownTypeVertex) {

    }
}
