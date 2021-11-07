package io.serpentes.input.sources.environment_variables.naming.snake_case;


import io.serpentes.input.sources.environment_variables.api.naming.EnvironmentVariableName;
import io.serpentes.input.sources.environment_variables.api.naming.NestedEnvironmentVariableKeyName;

public class SnakeCaseEnvironmentVariableKeyName implements NestedEnvironmentVariableKeyName {
    public static final String INFIX = "_";
    private final String name;
    private final EnvironmentVariableName predecessor;

    public SnakeCaseEnvironmentVariableKeyName(final String name, final EnvironmentVariableName predecessor) {
        this.name = name;
        this.predecessor = predecessor;
    }

    @Override
    public String getName() {
        return this.predecessor.getName() + INFIX + this.name.toUpperCase();
    }

    @Override
    public EnvironmentVariableName getPredecessor() {
        return predecessor;
    }
}
