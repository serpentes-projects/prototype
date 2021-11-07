package io.serpentes.input.sources.environment_variables.naming;

import io.serpentes.input.sources.environment_variables.api.naming.EnvironmentVariableKeyNamingConvention;
import io.serpentes.input.sources.environment_variables.api.naming.EnvironmentVariableName;

import java.util.*;

public class KeyNameSourceValues {
    private final Map<Class<? extends EnvironmentVariableKeyNamingConvention>, List<EnvironmentVariableName>> store = new HashMap<>();
    private final List<List<EnvironmentVariableName>> orderedValues = new ArrayList<>();
    private final Set<String> names = new HashSet<>();

    public void put(final Class<? extends EnvironmentVariableKeyNamingConvention> key, final EnvironmentVariableName name) {
        final List<EnvironmentVariableName> environmentVariableNames = store.get(key);
        if (environmentVariableNames != null) {
            environmentVariableNames.add(name);
        } else {
            final ArrayList<EnvironmentVariableName> variableNames = new ArrayList<>();
            variableNames.add(name);
            store.put(key, variableNames);
            orderedValues.add(variableNames);
        }
        names.add(name.getName());
    }

    public List<EnvironmentVariableName> get(final Class<? extends EnvironmentVariableKeyNamingConvention> key) {
        return new ArrayList<>(store.get(key));
    }

    public Set<String> getNames() {
        return new HashSet<>(names);
    }

    public List<List<EnvironmentVariableName>> getEnvironmentVariableNames(){
        return new ArrayList<>(orderedValues);
    }
}
