package io.serpentes.input.stores.base;

import io.serpentes.api.input.sources.InputSource;
import io.serpentes.api.input.trees.InputTree;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class InputSourceValues {
    private Map<Class<? extends InputSource>, List<InputTree>> inputSourceValues = new HashMap<>();
    private List<List<InputTree>> orderedInputSourceValues = new ArrayList<>();

    public void put(final Class<? extends InputSource> key,final InputTree value) {
        List<InputTree> existingValues = inputSourceValues.get(key);
        if (existingValues != null) {
            existingValues.add(value);
        } else {
            List<InputTree> newValues = new ArrayList<>();
            newValues.add(value);
            inputSourceValues.put(key, newValues);
            orderedInputSourceValues.add(newValues);
        }
    }

    public List<InputTree> get(final Class<InputSource> key) {
        return inputSourceValues.get(key);
    }

    //TODO: It may be desirable to choose a SelectionStrategy (Override, Merge, NonNull, etc.) of sorts in later versions. This would allow a user to customize value precedence even more.
    public InputTree findFirstNonNullValue() {
        for (List<InputTree> inputTrees : this.orderedInputSourceValues) {
            for (InputTree inputTree : inputTrees) {
                if (inputTree != null) {
                    return inputTree;
                }
            }
        }
        return null;
    }
}
