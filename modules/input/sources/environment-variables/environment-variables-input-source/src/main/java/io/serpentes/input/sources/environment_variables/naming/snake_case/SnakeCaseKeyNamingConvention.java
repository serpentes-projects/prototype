package io.serpentes.input.sources.environment_variables.naming.snake_case;

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
import io.serpentes.input.sources.environment_variables.EnvironmentVariables;
import io.serpentes.input.sources.environment_variables.api.naming.EnvironmentVariableKeyNamingConvention;
import io.serpentes.input.sources.environment_variables.api.naming.EnvironmentVariableName;
import io.serpentes.input.sources.environment_variables.naming.EnvironmentVariableKeyNameStore;
import io.serpentes.input.sources.environment_variables.naming.KeyNameSourceValues;
import io.serpentes.input.stores.base.EmptyStoreClerk;
import io.serpentes.input.stores.base.InputStore;
import jakarta.inject.Inject;

import java.util.Stack;

public class SnakeCaseKeyNamingConvention implements EnvironmentVariableKeyNamingConvention, TypeTreeVisitor {
    private static final int MAX_LOOK_AHEAD = 50; //TODO: Make configurable
    private static final String DEFAULT_PREFIX = "BOA"; //TODO: Make configurable
    private final Stack<EnvironmentVariableName> environmentVariableNameStack = new Stack<>();

    private final EnvironmentVariableKeyNameStore keyNameStore;
    private final InputStore inputStore;
    private final EmptyStoreClerk emptyStoreClerk;
    private final DefinitionSources definitionSources;

    @Inject
    public SnakeCaseKeyNamingConvention(final DefinitionSources definitionSources, final InputStore inputStore, final EmptyStoreClerk emptyStoreClerk, final EnvironmentVariableKeyNameStore keyNameStore ){
        this.definitionSources = definitionSources;
        this.inputStore = inputStore;
        this.emptyStoreClerk = emptyStoreClerk;
        this.keyNameStore = keyNameStore;
    }

    @Override
    public void addEnvironmentVariableNamesToStore() {
        final var rootEnvVarName = new SnakeCaseRootEnvironmentVariableName(DEFAULT_PREFIX);
        environmentVariableNameStack.push(rootEnvVarName);
        definitionSources.getTypeTree().accept(this);
        environmentVariableNameStack.pop();
    }

    @Override
    public void visit(final UnknownTypeVertex unknownTypeVertex) {
        //TODO: Handle this.
    }

    @Override
    public void visit(final MapTypeVertex mapTypeVertex) {
        addToStore(mapTypeVertex);
        mapTypeVertex.getEdges().forEach(keyValueTypeEdge -> keyValueTypeEdge.accept(this));
    }

    public void addToStore(final TypeVertex typeVertex) {
        final KeyNameSourceValues keyNameSourceValues = keyNameStore.getOrDefault(typeVertex, new KeyNameSourceValues());
        keyNameSourceValues.put(SnakeCaseKeyNamingConvention.class, environmentVariableNameStack.peek());
        keyNameStore.put(typeVertex, keyNameSourceValues);
    }

    @Override
    public void visit(final KeyValueTypeEdge keyValueTypeEdge) {
        final var snakeCaseEnvironmentVariableKeyName = new SnakeCaseEnvironmentVariableKeyName(keyValueTypeEdge.getKey(), this.environmentVariableNameStack.peek());
        environmentVariableNameStack.push(snakeCaseEnvironmentVariableKeyName);
        keyValueTypeEdge.getValue().accept(this);
        environmentVariableNameStack.pop();
    }

    @Override
    public void visit(final CollectionTypeVertex collectionTypeVertex) {
        addToStore(collectionTypeVertex);
        for (int i = 0; i < collectionTypeVertex.getEdges().size() + 1; i++) {

            //TODO: See if this can be done more efficiently.
            if (collectionTypeVertex.getEdge(i) == null) {
                final int MAX_LOOK_AHEAD_INDEX = i + MAX_LOOK_AHEAD;
                // Find the first index for which a collection item name exists
                int firstIndexFound = -1;
                for (int possibleIndex = i; possibleIndex < MAX_LOOK_AHEAD_INDEX; possibleIndex++) {
                    final var collectionItemName = new SnakeCaseCollectionItemEnvironmentVariableKeyName(possibleIndex, environmentVariableNameStack.peek());
                    if (EnvironmentVariables.getEnvVarValue(collectionItemName) != null) {
                        firstIndexFound = possibleIndex;
                        break;
                    }
                }
                // Create collection-item type-vertices up until the index that was found.
                if (firstIndexFound > -1) {
                    final var inputSourceValues = inputStore.get(collectionTypeVertex);
                    inputStore.remove(collectionTypeVertex);
                    final KeyNameSourceValues keyNameSourceValues = keyNameStore.getOrDefault(collectionTypeVertex, new KeyNameSourceValues());
                    keyNameStore.remove(collectionTypeVertex);
                    for (int indexToCreate = 0; indexToCreate < firstIndexFound + 1; indexToCreate++) {
                        collectionTypeVertex.createEdge(indexToCreate);
                        final CollectionItemTypeEdge child = collectionTypeVertex.getEdge(indexToCreate);
                        emptyStoreClerk.visit(child);
                    }
                    inputStore.put(collectionTypeVertex, inputSourceValues);
                    keyNameStore.put(collectionTypeVertex, keyNameSourceValues);
                }
            }
            if (collectionTypeVertex.getEdge(i) != null) {
                collectionTypeVertex.getEdge(i).accept(this);
            }
        }
    }

    @Override
    public void visit(CollectionItemTypeEdge collectionItemTypeEdge) {
        final var collectionItemEnvironmentVariableKeyName = new SnakeCaseCollectionItemEnvironmentVariableKeyName(collectionItemTypeEdge.getIndex(), this.environmentVariableNameStack.peek());
        environmentVariableNameStack.push(collectionItemEnvironmentVariableKeyName);
        collectionItemTypeEdge.getItem().accept(this);
        environmentVariableNameStack.pop();
    }

    @Override
    public void visit(final NullTypeVertex nullTypeVertex) {
        addToStore(nullTypeVertex);
    }

    @Override
    public void visit(final BooleanTypeVertex booleanTypeVertex) {
        addToStore(booleanTypeVertex);
    }

    @Override
    public void visit(final IntegerTypeVertex integerTypeVertex) {
        addToStore(integerTypeVertex);
    }

    @Override
    public void visit(final FloatingPointTypeVertex floatingPointTypeVertex) {
        addToStore(floatingPointTypeVertex);
    }

    @Override
    public void visit(final StringTypeVertex stringTypeVertex) {
        addToStore(stringTypeVertex);
    }
}
