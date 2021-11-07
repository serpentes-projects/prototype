package io.serpentes.input.sources.environment_variables;

import io.serpentes.api.configuration.store.StoreClerk;
import io.serpentes.api.definition.sources.DefinitionSources;
import io.serpentes.api.definition.trees.TypeTreeVisitor;
import io.serpentes.api.definition.trees.edges.CollectionItemTypeEdge;
import io.serpentes.api.definition.trees.edges.KeyValueTypeEdge;
import io.serpentes.api.definition.trees.vertices.TypeVertex;
import io.serpentes.api.definition.trees.vertices.UnknownTypeVertex;
import io.serpentes.api.definition.trees.vertices.branch.CollectionTypeVertex;
import io.serpentes.api.definition.trees.vertices.branch.MapTypeVertex;
import io.serpentes.api.definition.trees.vertices.terminal.*;
import io.serpentes.api.definition.trees.vertices.terminal.*;
import io.serpentes.api.input.parsers.InputParsers;
import io.serpentes.api.input.trees.InputTree;
import io.serpentes.input.sources.environment_variables.api.naming.EnvironmentVariableName;
import io.serpentes.input.sources.environment_variables.naming.EnvironmentVariableKeyNameStore;
import io.serpentes.input.stores.base.InputStore;
import jakarta.inject.Inject;

import java.util.List;

public class EnvironmentVariablesInputStoreClerk implements StoreClerk, TypeTreeVisitor {
    private final DefinitionSources definitionSources;
    private final InputParsers inputParsers;
    private final EnvironmentVariableKeyNameStore environmentVariableKeyNameStore;
    private final InputStore inputStore;

    @Inject
    public EnvironmentVariablesInputStoreClerk(final DefinitionSources definitionSources, final InputParsers inputParsers, final InputStore inputStore, final EnvironmentVariableKeyNameStore environmentVariableKeyNameStore){
        this.definitionSources = definitionSources;
        this.inputParsers = inputParsers;
        this.inputStore = inputStore;
        this.environmentVariableKeyNameStore = environmentVariableKeyNameStore;
    }

    @Override
    public void stockStore() {
        final var typeTree = definitionSources.getTypeTree();
        typeTree.accept(this);
    }

    @Override
    public void visit(UnknownTypeVertex unknownTypeVertex) {
        //TODO: Handle this?
    }

    private void addToStore(final TypeVertex typeVertex) {
        final var inputTree = getEnvVarValue(typeVertex);
        addToStore(typeVertex, inputTree);
    }

    private InputTree getEnvVarValue(final TypeVertex typeVertex) {
        final var keyNameSourceValues = environmentVariableKeyNameStore.get(typeVertex);
        for (final List<EnvironmentVariableName> environmentVariableNames :keyNameSourceValues.getEnvironmentVariableNames()) {
            for (final var environmentVariableName : environmentVariableNames) {
                final String envVarValue = EnvironmentVariables.getEnvVarValue(environmentVariableName);
                //TODO: Check for null? Or use a different mechanism?
                if (envVarValue != null) {
                    return inputParsers.parse(envVarValue);
                }
            }
        }
        return null; // TODO: Should I really return null here? It is depended upon by "findFirstNonNullValue" but is this desirable?
    }

    private void addToStore(TypeVertex typeVertex, InputTree inputTree) {
        final var inputSourceValues = inputStore.get(typeVertex);
        inputSourceValues.put(EnvironmentVariablesInputSource.class, inputTree);
    }

    @Override
    public void visit(MapTypeVertex mapTypeVertex) {
        //TODO: Implement specificity overruling.
        mapTypeVertex.getEdges().forEach(keyValueTypeEdge -> keyValueTypeEdge.accept(this));
        addToStore(mapTypeVertex);
    }

    @Override
    public void visit(KeyValueTypeEdge keyValueTypeEdge) {
        keyValueTypeEdge.getValue().accept(this);
    }

    @Override
    public void visit(CollectionTypeVertex collectionTypeVertex) {
        //TODO: Handle collection items.
        addToStore(collectionTypeVertex);
    }

    @Override
    public void visit(CollectionItemTypeEdge collectionItemTypeEdge) {
        //TODO: Implement this.
    }

    @Override
    public void visit(NullTypeVertex nullTypeVertex) {
        addToStore(nullTypeVertex);
    }

    @Override
    public void visit(BooleanTypeVertex booleanTypeVertex) {
        addToStore(booleanTypeVertex);
    }

    @Override
    public void visit(IntegerTypeVertex integerTypeVertex) {
        addToStore(integerTypeVertex);
    }

    @Override
    public void visit(FloatingPointTypeVertex floatingPointTypeVertex) {
        addToStore(floatingPointTypeVertex);
    }

    @Override
    public void visit(StringTypeVertex stringTypeVertex) {
        addToStore(stringTypeVertex);
    }
}