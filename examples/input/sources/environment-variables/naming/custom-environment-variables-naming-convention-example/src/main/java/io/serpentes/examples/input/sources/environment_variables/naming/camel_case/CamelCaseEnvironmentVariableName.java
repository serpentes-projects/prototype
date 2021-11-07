package io.serpentes.examples.input.sources.environment_variables.naming.camel_case;

import io.serpentes.input.sources.environment_variables.api.naming.EnvironmentVariableName;
import io.serpentes.input.sources.environment_variables.api.naming.NestedEnvironmentVariableKeyName;

public class CamelCaseEnvironmentVariableName implements NestedEnvironmentVariableKeyName {
    private final String name;
    private final EnvironmentVariableName predecessor;

    public CamelCaseEnvironmentVariableName(String name, EnvironmentVariableName predecessor) {
        this.name = name;
        this.predecessor = predecessor;
    }

    @Override
    public String getName() {
        return this.predecessor.getName() + CamelCaseUtils.camelize(this.name);
    }

    @Override
    public EnvironmentVariableName getPredecessor() {
        return predecessor;
    }
}
