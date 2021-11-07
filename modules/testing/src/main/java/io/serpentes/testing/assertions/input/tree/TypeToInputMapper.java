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
import io.serpentes.api.definition.trees.vertices.terminal.*;
import io.serpentes.api.input.trees.vertices.InputVertex;
import io.serpentes.input.trees.base.DefaultInputTree;
import io.serpentes.input.trees.base.TypedTreeComponentRetriever;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class TypeToInputMapper implements TypeTreeVisitor {
    private final Stack<InputVertex> inputVertexStack = new Stack<>();
    private final TypedTreeComponentRetriever treeComponentRetriever = new TypedTreeComponentRetriever();
    private Map<TypeVertex, InputVertex> typeVertexInputVertexHashMap = new HashMap<>();

    public Map<TypeVertex, InputVertex> map(final TypeVertex typeVertex, final InputVertex inputVertex) {
        typeVertexInputVertexHashMap = new HashMap<>();
        addInputVertexToStack(inputVertex);
        typeVertex.accept(this);
        inputVertexStack.clear();
        return typeVertexInputVertexHashMap;
    }

    private void addInputVertexToStack(final InputVertex inputVertex) {
        inputVertexStack.push(inputVertex);
        refreshTreeComponentRetriever();
    }

    private void refreshTreeComponentRetriever() {
        treeComponentRetriever.refresh(inputVertexStack.peek());
    }

    @Override
    public void visit(MapTypeVertex mapTypeVertex) {
        typeVertexInputVertexHashMap.put(mapTypeVertex, new DefaultInputTree(treeComponentRetriever.getMapInputVertex()));
        visitBranchTypeVertex(mapTypeVertex);
    }

    void visitBranchTypeVertex(BranchTypeVertex<?> branchTypeVertex) {
        branchTypeVertex.getEdges().forEach(typeEdge -> typeEdge.accept(this));
    }

    @Override
    public void visit(KeyValueTypeEdge keyValueTypeEdge) {
        final var mapInputVertex = treeComponentRetriever.getMapInputVertex();
        final var inputEdge = mapInputVertex.getEdge(keyValueTypeEdge.getKey());
        addInputVertexToStack(inputEdge.getValue());
        keyValueTypeEdge.getValue().accept(this);
        removeInputVertexFromStack();
    }

    private void removeInputVertexFromStack() {
        inputVertexStack.pop();
        refreshTreeComponentRetriever();
    }

    @Override
    public void visit(CollectionTypeVertex collectionTypeVertex) {
        final var collectionInputVertex = treeComponentRetriever.getCollectionInputVertex();
        typeVertexInputVertexHashMap.put(collectionTypeVertex, new DefaultInputTree(collectionInputVertex));
        collectionTypeVertex.getEdges().forEach(collectionItemTypeEdge -> collectionItemTypeEdge.accept(this));
    }

    @Override
    public void visit(CollectionItemTypeEdge collectionItemTypeEdge) {
        final var collectionInputVertex = treeComponentRetriever.getCollectionInputVertex();
        final var inputEdge = collectionInputVertex.getEdge(collectionItemTypeEdge.getIndex());
        addInputVertexToStack(inputEdge.getItem());
        collectionItemTypeEdge.getItem().accept(this);
        removeInputVertexFromStack();
    }

    @Override
    public void visit(NullTypeVertex nullTypeVertex) {
        typeVertexInputVertexHashMap.put(nullTypeVertex, new DefaultInputTree(treeComponentRetriever.getNullInputTerminalVertex()));
    }

    @Override
    public void visit(BooleanTypeVertex booleanTypeVertex) {
        typeVertexInputVertexHashMap.put(booleanTypeVertex, new DefaultInputTree(treeComponentRetriever.getBooleanInputTerminalVertex()));
    }

    @Override
    public void visit(IntegerTypeVertex integerTypeVertex) {
        typeVertexInputVertexHashMap.put(integerTypeVertex, new DefaultInputTree(treeComponentRetriever.getIntegerInputTerminalVertex()));
    }

    @Override
    public void visit(FloatingPointTypeVertex floatingPointTypeVertex) {
        typeVertexInputVertexHashMap.put(floatingPointTypeVertex, new DefaultInputTree(treeComponentRetriever.getFloatingPointInputTerminalVertex()));
    }

    @Override
    public void visit(StringTypeVertex stringTypeVertex) {
        typeVertexInputVertexHashMap.put(stringTypeVertex, new DefaultInputTree(treeComponentRetriever.getStringInputTerminalVertex()));
    }

    @Override
    public void visit(UnknownTypeVertex unknownTypeVertex) {

    }
}
