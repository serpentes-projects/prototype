package io.serpentes.examples.input.sources.environment_variables.naming.camel_case;

import io.serpentes.api.definition.sources.DefinitionSources;
import io.serpentes.api.definition.trees.TypeTreeVisitor;
import io.serpentes.api.definition.trees.edges.CollectionItemTypeEdge;
import io.serpentes.api.definition.trees.edges.KeyValueTypeEdge;
import io.serpentes.api.definition.trees.vertices.TypeVertex;
import io.serpentes.api.definition.trees.vertices.UnknownTypeVertex;
import io.serpentes.api.definition.trees.vertices.branch.CollectionTypeVertex;
import io.serpentes.api.definition.trees.vertices.branch.MapTypeVertex;
import io.serpentes.api.definition.trees.vertices.terminal.*;
import io.serpentes.input.sources.environment_variables.api.naming.EnvironmentVariableKeyNamingConvention;
import io.serpentes.input.sources.environment_variables.api.naming.EnvironmentVariableName;
import io.serpentes.input.sources.environment_variables.api.naming.NestedEnvironmentVariableKeyName;
import io.serpentes.input.sources.environment_variables.naming.EnvironmentVariableKeyNameStore;
import io.serpentes.input.sources.environment_variables.naming.KeyNameSourceValues;
import io.serpentes.input.sources.environment_variables.naming.snake_case.SnakeCaseKeyNamingConvention;
import jakarta.inject.Inject;

import java.util.Stack;

public class CamelCaseNamingConvention implements EnvironmentVariableKeyNamingConvention, TypeTreeVisitor {
    private static final String DEFAULT_PREFIX = "boa";
    private final Stack<EnvironmentVariableName> environmentVariableNameStack = new Stack<>();
    private final DefinitionSources definitionSources;
    private final EnvironmentVariableKeyNameStore keyNameStore;

    @Inject
    public CamelCaseNamingConvention(final DefinitionSources definitionSources, final EnvironmentVariableKeyNameStore keyNameStore) {
        this.definitionSources = definitionSources;
        this.keyNameStore = keyNameStore;
    }

    @Override
    public void addEnvironmentVariableNamesToStore() {
        final var rootEnvVarName = new RootCamelCaseEnvironmentVariableName(DEFAULT_PREFIX);
        environmentVariableNameStack.push(rootEnvVarName);
        definitionSources.getTypeTree().accept(this);
        environmentVariableNameStack.pop();
    }

    @Override
    public void visit(MapTypeVertex mapTypeVertex) {
        this.addToStore(mapTypeVertex);
        mapTypeVertex.getEdges().forEach(keyValueTypeEdge -> keyValueTypeEdge.accept(this));
    }

    public void addToStore(final TypeVertex typeVertex) {
        final var keyNameSourceValues = keyNameStore.getOrDefault(typeVertex, new KeyNameSourceValues());
        keyNameSourceValues.put(SnakeCaseKeyNamingConvention.class, environmentVariableNameStack.peek());
        keyNameStore.put(typeVertex, keyNameSourceValues);
    }

    @Override
    public void visit(KeyValueTypeEdge keyValueTypeEdge) {
        NestedEnvironmentVariableKeyName nestedEnvironmentVariableKeyName = new CamelCaseEnvironmentVariableName(keyValueTypeEdge.getKey(), this.environmentVariableNameStack.peek());
        this.environmentVariableNameStack.push(nestedEnvironmentVariableKeyName);
        keyValueTypeEdge.getValue().accept(this);
        this.environmentVariableNameStack.pop();
    }

    @Override
    public void visit(CollectionItemTypeEdge collectionItemTypeEdge) {
        final var collectionItemEnvironmentVariableKeyName = new CamelCaseCollectionItemEnvironmentVariableName(collectionItemTypeEdge.getIndex(), this.environmentVariableNameStack.peek());
        environmentVariableNameStack.push(collectionItemEnvironmentVariableKeyName);
        collectionItemTypeEdge.getItem().accept(this);
        environmentVariableNameStack.pop();
    }

    @Override
    public void visit(CollectionTypeVertex collectionTypeVertex) {
        this.addToStore(collectionTypeVertex);
    }

    @Override
    public void visit(NullTypeVertex nullTypeVertex) {
        this.addToStore(nullTypeVertex);
    }

    @Override
    public void visit(BooleanTypeVertex booleanTypeVertex) {
        this.addToStore(booleanTypeVertex);
    }

    @Override
    public void visit(IntegerTypeVertex integerTypeVertex) {
        this.addToStore(integerTypeVertex);
    }

    @Override
    public void visit(FloatingPointTypeVertex floatingPointTypeVertex) {
        this.addToStore(floatingPointTypeVertex);
    }

    @Override
    public void visit(StringTypeVertex stringTypeVertex) {
        this.addToStore(stringTypeVertex);
    }

    @Override
    public void visit(UnknownTypeVertex unknownTypeVertex) {
        //TODO: Handle this.
    }
}
