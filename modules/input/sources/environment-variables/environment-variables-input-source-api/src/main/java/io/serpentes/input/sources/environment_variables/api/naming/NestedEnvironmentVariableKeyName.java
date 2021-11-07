package io.serpentes.input.sources.environment_variables.api.naming;


public interface NestedEnvironmentVariableKeyName extends EnvironmentVariableName {
    EnvironmentVariableName getPredecessor();
}
