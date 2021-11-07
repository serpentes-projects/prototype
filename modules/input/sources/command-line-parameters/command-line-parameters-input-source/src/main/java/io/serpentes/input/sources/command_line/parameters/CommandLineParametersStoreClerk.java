package io.serpentes.input.sources.command_line.parameters;

import io.serpentes.api.annotations.input.sources.command_line.CommandLineParameters;
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
import io.serpentes.api.definition.trees.vertices.terminal.*;
import io.serpentes.api.input.parsers.InputParsers;
import io.serpentes.api.input.trees.InputTree;
import io.serpentes.api.input.trees.vertices.InputVertex;
import io.serpentes.input.sources.command_line.parameters.providers.CommandLineParametersProvider;
import io.serpentes.input.stores.base.InputStore;
import io.serpentes.input.trees.base.DefaultInputTree;
import io.serpentes.input.trees.base.TypedTreeComponentRetriever;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Stack;

public class CommandLineParametersStoreClerk implements StoreClerk, TypeTreeVisitor {
    private final TypedTreeComponentRetriever typedTreeComponentRetriever = new TypedTreeComponentRetriever();
    private final Stack<InputVertex> inputVertexStack = new Stack<>();

    private final DefinitionSources definitionSources;
    private final InputParsers inputParsers;
    private final InputStore inputStore;
    private final CommandLineParametersProvider commandLineParametersProvider;

    @Inject
    public CommandLineParametersStoreClerk(final DefinitionSources definitionSources, final InputParsers inputParsers, final InputStore inputStore, @CommandLineParameters final CommandLineParametersProvider commandLineParametersProvider){
        this.definitionSources = definitionSources;
        this.inputParsers = inputParsers;
        this.inputStore = inputStore;
        this.commandLineParametersProvider = commandLineParametersProvider;
    }

    @Override
    public void stockStore() {
        final var typeTree = definitionSources.getTypeTree();
        final List<String> commandLineParameters = commandLineParametersProvider.toList();
        //TODO: Currently Serpentes can only handle 1 parameter. When command-line parameter key-name support is added that will be fixed.
        if (typeTree != null && commandLineParameters.size() == 1) {
            final var inputTree = inputParsers.parse(commandLineParameters.get(0));
            if(inputVertexExists(inputTree)){
                inputVertexStack.push(inputTree);
                refreshInputVertexStore();
                //TODO: This introduces a undesirable situation were we are dependent on the order and existence of input. Replace it.
                typeTree.accept(this);
                inputVertexStack.pop();
            }
        }
    }

    private void refreshInputVertexStore() {
        typedTreeComponentRetriever.refresh(inputVertexStack.peek());
    }

    @Override
    public void visit(UnknownTypeVertex unknownTypeVertex) {
        //TODO: Handle this?
    }

    @Override
    public void visit(MapTypeVertex mapTypeVertex) {
        final var mapInputVertex = typedTreeComponentRetriever.getMapInputVertex();
        if (inputVertexExists(mapInputVertex)) {
            addTypeVertexToStore(mapTypeVertex, new DefaultInputTree(mapInputVertex));
            visitBranchTypeVertex(mapTypeVertex);
        }
    }

    private void visitBranchTypeVertex(BranchTypeVertex<?> branchTypeVertex) {
        branchTypeVertex.getEdges().forEach(typeEdge -> typeEdge.accept(this));
    }

    private void addTypeVertexToStore(TypeVertex typeVertex, InputTree inputTree) {
        final var inputSourceValues = inputStore.get(typeVertex);
        inputSourceValues.put(CommandLineParametersInputSource.class, inputTree);
    }

    private boolean inputVertexExists(final InputVertex inputVertex) {
        return inputVertex != null;
    }

    @Override
    public void visit(KeyValueTypeEdge keyValueTypeEdge) {
        final var parent = typedTreeComponentRetriever.getMapInputVertex();
        final var keyValueInputEdge = parent.getEdge(keyValueTypeEdge.getKey());
        if (keyValueInputEdge != null) {
            inputVertexStack.push(keyValueInputEdge.getValue());
            refreshInputVertexStore();
            keyValueTypeEdge.getValue().accept(this);
            inputVertexStack.pop();
            refreshInputVertexStore();
        }
    }

    @Override
    public void visit(CollectionTypeVertex collectionTypeVertex) {
        //TODO: Visit children.
        final var collectionInputVertex = typedTreeComponentRetriever.getCollectionInputVertex();
        if (inputVertexExists(collectionInputVertex)) {
            addTypeVertexToStore(collectionTypeVertex, new DefaultInputTree(collectionInputVertex));
        }
    }

    @Override
    public void visit(CollectionItemTypeEdge collectionItemTypeEdge) {
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
}
