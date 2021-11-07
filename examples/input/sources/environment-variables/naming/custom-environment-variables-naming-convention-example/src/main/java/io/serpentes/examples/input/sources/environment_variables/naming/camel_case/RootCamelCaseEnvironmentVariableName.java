package io.serpentes.examples.input.sources.environment_variables.naming.camel_case;


import io.serpentes.input.sources.environment_variables.api.naming.EnvironmentVariableName;

public class RootCamelCaseEnvironmentVariableName implements EnvironmentVariableName {
    private final String name;

    public RootCamelCaseEnvironmentVariableName(String name){
        this.name = name;
    }

    @Override
    public String getName() {
        return name.toLowerCase();
    }
}
