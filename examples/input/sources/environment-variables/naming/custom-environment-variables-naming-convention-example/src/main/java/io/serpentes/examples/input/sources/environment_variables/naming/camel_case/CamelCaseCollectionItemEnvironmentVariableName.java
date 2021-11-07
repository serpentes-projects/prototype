package io.serpentes.examples.input.sources.environment_variables.naming.camel_case;


import io.serpentes.input.sources.environment_variables.api.naming.CollectionItemEnvironmentVariableKeyName;
import io.serpentes.input.sources.environment_variables.api.naming.EnvironmentVariableName;

public class CamelCaseCollectionItemEnvironmentVariableName implements CollectionItemEnvironmentVariableKeyName {
    private final EnvironmentVariableName predecessor;
    private int index;

    public CamelCaseCollectionItemEnvironmentVariableName(final int index, final EnvironmentVariableName predecessor) {
        this.index = index;
        this.predecessor = predecessor;
    }

    @Override
    public String getName() {
        return getPredecessor().getName() + this.index;
    }

    @Override
    public EnvironmentVariableName getPredecessor() {
        return predecessor;
    }

    @Override
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
