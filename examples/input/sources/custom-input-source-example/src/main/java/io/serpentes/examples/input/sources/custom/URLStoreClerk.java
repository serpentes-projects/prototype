package io.serpentes.examples.input.sources.custom;

import io.serpentes.api.configuration.store.StoreClerk;
import io.serpentes.api.definition.sources.DefinitionSources;
import io.serpentes.api.definition.trees.TypeTreeVisitor;
import io.serpentes.api.definition.trees.edges.CollectionItemTypeEdge;
import io.serpentes.api.definition.trees.edges.KeyValueTypeEdge;
import io.serpentes.api.definition.trees.vertices.TypeVertex;
import io.serpentes.api.definition.trees.vertices.UnknownTypeVertex;
import io.serpentes.api.definition.trees.vertices.branch.BranchTypeVertex;
import io.serpentes.api.definition.trees.vertices.branch.CollectionTypeVertex;
import io.serpentes.api.definition.trees.vertices.branch.MapTypeVertex;
import io.serpentes.api.definition.trees.vertices.terminal.*;
import io.serpentes.api.input.parsers.InputParsers;
import io.serpentes.api.input.trees.InputTree;
import io.serpentes.api.input.trees.vertices.InputVertex;
import io.serpentes.input.stores.base.InputStore;
import io.serpentes.input.trees.base.DefaultInputTree;
import io.serpentes.input.trees.base.TypedTreeComponentRetriever;
import jakarta.inject.Inject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Stack;

// NOTE: This class was largely derived from FileSystemInputSource as they share many aspects.
// The main difference being that the URL implementation is simpler API-wise.
public class URLStoreClerk implements StoreClerk, TypeTreeVisitor {
    private final URLs urLs;
    private final DefinitionSources definitionSources;
    private final InputParsers inputParsers;
    private final InputStore inputStore;

    private final TypedTreeComponentRetriever typedTreeComponentRetriever = new TypedTreeComponentRetriever();
    private final Stack<InputVertex> inputVertexStack = new Stack<>();

    @Inject
    public URLStoreClerk(URLs urls, DefinitionSources definitionSources, InputParsers inputParsers, InputStore inputStore) {
        this.urLs = urls;
        this.definitionSources = definitionSources;
        this.inputParsers = inputParsers;
        this.inputStore = inputStore;
    }

    @Override
    public void stockStore() {
        for (final URL url : this.urLs.getURLs()) {
            storeURL(url);
        }
    }

    private void storeURL(final URL url) {
        try {
            //TODO: Ensure CharSets are configurable.
            final var typeTree = definitionSources.getTypeTree();
            try (final BufferedReader in = new BufferedReader(
                    new InputStreamReader(url.openStream()))) {
                final var lines = in.lines().iterator();
                final StringBuilder stringBuilder = new StringBuilder();
                while (lines.hasNext()) {
                    stringBuilder.append(lines.next());
                }

                final var inputTree = inputParsers.parse(stringBuilder.toString());
                if (typeTree != null && inputVertexExists(inputTree)) {
                    inputVertexStack.push(inputTree);
                    refreshInputComponentRetriever();
                    inputTree.accept(typedTreeComponentRetriever);
                    typeTree.accept(this);
                    inputVertexStack.pop();
                }
            }
        } catch (IOException e) {
            //TODO: Properly handle this, eat it for now.
            e.printStackTrace();
        }
    }

    private boolean inputVertexExists(final InputVertex inputVertex) {
        return inputVertex != null;
    }


    private void refreshInputComponentRetriever() {
        typedTreeComponentRetriever.refresh(inputVertexStack.peek());
    }

    @Override
    public void visit(MapTypeVertex mapTypeVertex) {
        final var mapInputVertex = typedTreeComponentRetriever.getMapInputVertex();
        if (inputVertexExists(mapInputVertex)) {
            addTypeVertexToStore(mapTypeVertex, new DefaultInputTree(mapInputVertex));
            visitBranchTypeVertex(mapTypeVertex);
        }
    }

    private void addTypeVertexToStore(final TypeVertex typeVertex, final InputTree inputTree) {
        final var inputSourceValues = inputStore.get(typeVertex);
        inputSourceValues.put(URLInputSource.class, inputTree);
    }

    private void visitBranchTypeVertex(BranchTypeVertex<?> branchTypeVertex) {
        branchTypeVertex.getEdges().forEach(typeEdge -> typeEdge.accept(this));
    }

    @Override
    public void visit(KeyValueTypeEdge keyValueTypeEdge) {
        final var parent = typedTreeComponentRetriever.getMapInputVertex();
        final var keyValueInputEdge = parent.getEdge(keyValueTypeEdge.getKey());
        if (keyValueInputEdge != null && inputVertexExists(keyValueInputEdge.getValue())) {
            inputVertexStack.push(keyValueInputEdge.getValue());
            refreshInputComponentRetriever();
            keyValueTypeEdge.getValue().accept(this);
            inputVertexStack.pop();
            refreshInputComponentRetriever();
        }
    }

    @Override
    public void visit(CollectionTypeVertex collectionTypeVertex) {
        final var collectionInputVertex = typedTreeComponentRetriever.getCollectionInputVertex();
        if (inputVertexExists(collectionInputVertex)) {
            for (int i = 0; i < collectionInputVertex.getEdges().size(); i++) {
                if (collectionTypeVertex.getEdge(i) != null) {
                    collectionTypeVertex.accept(this);
                } else {
                    //Collection visitor
                    collectionTypeVertex.createEdge(i);
                    collectionTypeVertex.getEdge(i).accept(this);
                }
            }
            addTypeVertexToStore(collectionTypeVertex, new DefaultInputTree(collectionInputVertex));
        }
    }

    @Override
    public void visit(CollectionItemTypeEdge collectionItemTypeEdge) {
        final var parent = typedTreeComponentRetriever.getCollectionInputVertex();
        final var collectionItemInputEdge = parent.getEdge(collectionItemTypeEdge.getIndex());
        if (inputVertexExists(collectionItemInputEdge.getItem())) {
            inputVertexStack.push(collectionItemInputEdge.getItem());
            refreshInputComponentRetriever();
            collectionItemTypeEdge.getItem().accept(this);
            inputVertexStack.pop();
            refreshInputComponentRetriever();
        }
    }

    @Override
    public void visit(NullTypeVertex nullTypeVertex) {
        final var inputVertex = typedTreeComponentRetriever.getNullInputTerminalVertex();
        if (inputVertexExists(inputVertex)) {
            addTypeVertexToStore(nullTypeVertex, new DefaultInputTree(inputVertex));
        }
    }


    @Override
    public void visit(BooleanTypeVertex booleanTypeVertex) {
        final var inputVertex = typedTreeComponentRetriever.getBooleanInputTerminalVertex();
        if (inputVertexExists(inputVertex)) {
            addTypeVertexToStore(booleanTypeVertex, new DefaultInputTree(inputVertex));
        }
    }

    @Override
    public void visit(IntegerTypeVertex integerTypeVertex) {
        final var inputVertex = typedTreeComponentRetriever.getIntegerInputTerminalVertex();
        if (inputVertexExists(inputVertex)) {
            addTypeVertexToStore(integerTypeVertex, new DefaultInputTree(inputVertex));
        }
    }

    @Override
    public void visit(FloatingPointTypeVertex floatingPointTypeVertex) {
        final var inputVertex = typedTreeComponentRetriever.getFloatingPointInputTerminalVertex();
        if (inputVertexExists(inputVertex)) {
            addTypeVertexToStore(floatingPointTypeVertex, new DefaultInputTree(inputVertex));
        }
    }

    @Override
    public void visit(StringTypeVertex stringTypeVertex) {
        final var inputVertex = typedTreeComponentRetriever.getStringInputTerminalVertex();
        if (inputVertexExists(inputVertex)) {
            addTypeVertexToStore(stringTypeVertex, new DefaultInputTree(inputVertex));
        }
    }

    @Override
    public void visit(UnknownTypeVertex unknownTypeVertex) {
        //TODO: Handle this?
    }
}
