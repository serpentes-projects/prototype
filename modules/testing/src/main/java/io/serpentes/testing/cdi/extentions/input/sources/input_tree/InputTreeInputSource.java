package io.serpentes.testing.cdi.extentions.input.sources.input_tree;

import io.serpentes.api.definition.trees.TypeTree;
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
import io.serpentes.api.input.sources.InputSource;
import io.serpentes.api.input.trees.InputTree;
import io.serpentes.api.input.trees.vertices.InputVertex;
import io.serpentes.api.input.trees.vertices.branch.CollectionInputVertex;
import io.serpentes.api.input.trees.vertices.branch.MapInputVertex;
import io.serpentes.input.stores.base.EmptyStoreClerk;
import io.serpentes.input.stores.base.InputSourceValues;
import io.serpentes.input.stores.base.InputStore;
import io.serpentes.input.trees.base.DefaultInputTree;
import io.serpentes.input.trees.base.TypedTreeComponentRetriever;
import jakarta.inject.Inject;

import java.util.Stack;

public class InputTreeInputSource implements InputSource, TypeTreeVisitor {
    private final Stack<InputVertex> inputVertexStack = new Stack<>();
    private final TypedTreeComponentRetriever treeComponentRetriever = new TypedTreeComponentRetriever();
    private final TypeTree typeTree;
    private final InputTree inputTree;

    @Inject
    private EmptyStoreClerk emptyStoreClerk;

    @Inject
    private InputStore inputStore;

    public InputTreeInputSource() {
        this.typeTree = null;
        this.inputTree = null;
    }

    //TODO: Reassess these parameters in favor of Injected clerk and store.
    public InputTreeInputSource(final TypeTree typeTree, final InputTree inputTree) {
        this.typeTree = typeTree;
        this.inputTree = inputTree;
    }

    @Override
    public void addValuesToInputStore() {
        inputVertexStack.push(inputTree);
        refreshTreeComponentRetriever();
        inputTree.accept(treeComponentRetriever);
        typeTree.accept(this);
        inputVertexStack.pop();
    }

    private void refreshTreeComponentRetriever() {
        treeComponentRetriever.refresh(inputVertexStack.peek());
    }

    @Override
    public void visit(UnknownTypeVertex unknownTypeVertex) {
        //TODO: Handle this?
    }

    @Override
    public void visit(MapTypeVertex mapTypeVertex) {
        final MapInputVertex mapInputVertex = treeComponentRetriever.getMapInputVertex();
        if (inputVertexExists(mapInputVertex)) {
            updateInputStore(mapTypeVertex, new DefaultInputTree(mapInputVertex));
            visitBranchTypeVertex(mapTypeVertex);
        }
    }


    private void updateInputStore(final TypeVertex typeVertex, final InputTree inputTree) {
        final InputSourceValues inputSourceValues = inputStore.get(typeVertex);
        inputSourceValues.put(InputTreeInputSource.class, inputTree);
    }

    private boolean inputVertexExists(final InputVertex inputVertex) {
        return inputVertex != null;
    }

    private void visitBranchTypeVertex(BranchTypeVertex<?> branchTypeVertex) {
        branchTypeVertex.getEdges().forEach(typeEdge -> typeEdge.accept(this));
    }

    @Override
    public void visit(KeyValueTypeEdge keyValueTypeEdge) {
        final var mapInputVertex = treeComponentRetriever.getMapInputVertex();
        final var mapInputVertexEdge = mapInputVertex.getEdge(keyValueTypeEdge.getKey());
        if (mapInputVertexEdge != null) {
            inputVertexStack.push(mapInputVertexEdge.getValue());
            refreshTreeComponentRetriever();
            keyValueTypeEdge.getValue().accept(this);
            inputVertexStack.pop();
            refreshTreeComponentRetriever();
        }
    }

    @Override
    public void visit(CollectionTypeVertex collectionTypeVertex) {
        final var collectionInputVertex = treeComponentRetriever.getCollectionInputVertex();
        if (inputVertexExists(collectionInputVertex)) {
            for (int i = 0; i < collectionInputVertex.getEdges().size(); i++) {
                if (collectionTypeVertex.getEdge(i) != null) {
                    collectionTypeVertex.getEdge(i).accept(this);
                } else {
                    inputStore.remove(collectionTypeVertex);
                    //Collection visitor
                    collectionTypeVertex.createEdge(i);
                    final CollectionItemTypeEdge child = collectionTypeVertex.getEdge(i);
                    emptyStoreClerk.visit(child);
                    collectionTypeVertex.getEdge(i).accept(this);
                    addInputToStore(collectionTypeVertex, collectionInputVertex);
                }
            }
            updateInputStore(collectionTypeVertex, new DefaultInputTree(collectionInputVertex));
        }
    }

    private void addInputToStore(CollectionTypeVertex collectionTypeVertex, CollectionInputVertex collectionInputVertex) {
        final var inputSourceValues = new InputSourceValues();
        inputSourceValues.put(InputTreeInputSource.class, new DefaultInputTree(collectionInputVertex));
        inputStore.put(collectionTypeVertex, inputSourceValues);
    }

    @Override
    public void visit(CollectionItemTypeEdge collectionItemTypeEdge) {
        final var parent = treeComponentRetriever.getCollectionInputVertex();
        final var collectionItemInputEdge = parent.getEdge(collectionItemTypeEdge.getIndex());
        if (collectionItemInputEdge != null) {
            inputVertexStack.push(collectionItemInputEdge.getItem());
            refreshTreeComponentRetriever();
            collectionItemTypeEdge.getItem().accept(this);
            inputVertexStack.pop();
            refreshTreeComponentRetriever();
        }
    }

    @Override
    public void visit(NullTypeVertex nullTypeVertex) {
        final var inputTerminalVertex = treeComponentRetriever.getNullInputTerminalVertex();
        if (inputVertexExists(inputTerminalVertex)) {
            updateInputStore(nullTypeVertex, new DefaultInputTree(inputTerminalVertex));
        }
    }


    @Override
    public void visit(BooleanTypeVertex booleanTypeVertex) {
        final var inputTerminalVertex = treeComponentRetriever.getBooleanInputTerminalVertex();
        if (inputVertexExists(inputTerminalVertex)) {
            updateInputStore(booleanTypeVertex, new DefaultInputTree(inputTerminalVertex));
        }
    }

    @Override
    public void visit(IntegerTypeVertex integerTypeVertex) {
        final var inputTerminalVertex = treeComponentRetriever.getIntegerInputTerminalVertex();
        if (inputVertexExists(inputTerminalVertex)) {
            updateInputStore(integerTypeVertex, new DefaultInputTree(inputTerminalVertex));
        }
    }

    @Override
    public void visit(FloatingPointTypeVertex floatingPointTypeVertex) {
        final var inputTerminalVertex = treeComponentRetriever.getFloatingPointInputTerminalVertex();
        if (inputVertexExists(inputTerminalVertex)) {
            updateInputStore(floatingPointTypeVertex, new DefaultInputTree(inputTerminalVertex));
        }
    }

    @Override
    public void visit(StringTypeVertex stringTypeVertex) {
        final var inputTerminalVertex = treeComponentRetriever.getStringInputTerminalVertex();
        if (inputVertexExists(inputTerminalVertex)) {
            updateInputStore(stringTypeVertex, new DefaultInputTree(inputTerminalVertex));
        }
    }
}

