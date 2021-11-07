package io.serpentes.input.sources.environment_variables.naming.snake_case;


import io.serpentes.input.sources.environment_variables.api.naming.EnvironmentVariableName;

public class SnakeCaseRootEnvironmentVariableName implements EnvironmentVariableName {
    private final String name;

    public SnakeCaseRootEnvironmentVariableName(String name){
        this.name = name;
    }

    @Override
    public String getName() {
        return name.toUpperCase();
    }
}
