package io.serpentes.input.sources.environment_variables;

import io.serpentes.input.sources.environment_variables.api.naming.EnvironmentVariableName;

import java.util.Map;

public class EnvironmentVariables {
    private EnvironmentVariables() {
    }

    public static boolean hostHasEnvironmentVariableNameContaining(final String term) {
        final Map<String, String> environmentVariablesOnSystem = System.getenv();
        for (final String name : environmentVariablesOnSystem.keySet()) {
            if (name.contains(term)) {
                return true;
            }
        }
        return false;
    }

    public static String getEnvVarValue(final EnvironmentVariableName name) {
        return System.getenv(name.getName());
    }
}
