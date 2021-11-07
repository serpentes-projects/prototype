package io.serpentes.input.sources.environment_variables.naming;

import io.serpentes.api.definition.trees.vertices.TypeVertex;
import jakarta.inject.Singleton;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Singleton
public class EnvironmentVariableKeyNameStore {
    private final Map<TypeVertex, KeyNameSourceValues> store = new HashMap<>();


    public void put(final TypeVertex typeVertex, final KeyNameSourceValues keyNameSourceValues) {
        store.put(typeVertex, keyNameSourceValues);
    }

    public KeyNameSourceValues get(final TypeVertex typeVertex) {
        return store.get(typeVertex);
    }

    public KeyNameSourceValues getOrDefault(final TypeVertex typeVertex, final KeyNameSourceValues defaultValue) {
        return store.getOrDefault(typeVertex, defaultValue);
    }

    public void remove(final TypeVertex typeVertex) {
        store.remove(typeVertex);
    }

    public Collection<KeyNameSourceValues> values() {
        return store.values();
    }
}
