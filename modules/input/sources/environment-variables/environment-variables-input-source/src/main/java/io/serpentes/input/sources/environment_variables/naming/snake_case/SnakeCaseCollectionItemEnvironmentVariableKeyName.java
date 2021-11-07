package io.serpentes.input.sources.environment_variables.naming.snake_case;


import io.serpentes.input.sources.environment_variables.api.naming.CollectionItemEnvironmentVariableKeyName;
import io.serpentes.input.sources.environment_variables.api.naming.EnvironmentVariableName;

import static io.serpentes.input.sources.environment_variables.naming.snake_case.SnakeCaseEnvironmentVariableKeyName.INFIX;

public class SnakeCaseCollectionItemEnvironmentVariableKeyName implements CollectionItemEnvironmentVariableKeyName {
    private final EnvironmentVariableName predecessor;
    private final int index;

    public SnakeCaseCollectionItemEnvironmentVariableKeyName(final int index, final EnvironmentVariableName predecessor) {
        this.index = index;
        this.predecessor = predecessor;
    }

    @Override
    public String getName() {
        return getPredecessor().getName() + ((this.index >= 0) ? INFIX + this.index : "");
    }

    @Override
    public EnvironmentVariableName getPredecessor() {
        return predecessor;
    }

    @Override
    public int getIndex() {
        return index;
    }
}
