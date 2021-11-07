package io.serpentes.input.stores.base;

import io.serpentes.api.definition.trees.vertices.TypeVertex;
import jakarta.inject.Singleton;

import java.util.HashMap;
import java.util.Map;

@Singleton
public class InputStore {
    private final Map<TypeVertex, InputSourceValues> store = new HashMap<>();

    public void put(final TypeVertex typeVertex, final InputSourceValues inputSourceValues) {
        store.put(typeVertex, inputSourceValues);
    }

    public InputSourceValues get(final TypeVertex typeVertex) {
        return store.get(typeVertex);
    }

    public void replaceKey(final int hashCode, final TypeVertex replacement) {
        for (Map.Entry<TypeVertex, InputSourceValues> entry : store.entrySet()) {
            if (hashCode == entry.getKey().hashCode()) {
                final var value = entry.getValue();
                store.remove(entry.getKey());
                store.put(replacement, value);
                break;
            }
        }
    }

    public void remove(final TypeVertex typeVertex) {
        store.remove(typeVertex);
    }
}
