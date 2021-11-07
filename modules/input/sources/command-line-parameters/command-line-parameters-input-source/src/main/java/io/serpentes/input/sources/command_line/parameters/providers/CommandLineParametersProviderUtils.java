package io.serpentes.input.sources.command_line.parameters.providers;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CommandLineParametersProviderUtils {

    //TODO: Handle situations where there are also parameters that are not meant for the Provider.
    //TODO: Handle situations where the amount of parameters is not an even number.
    public static Map<String, String> listToMap(List<String> parameters) {
        final HashMap<String, String> parametersMap = new HashMap<>();
        if (listContainsEvenAmountOfItems(parameters)) {
            final Iterator<String> parametersIterator = parameters.iterator();
            while (parametersIterator.hasNext()) {
                final var keyName = parametersIterator.next();
                final var value = parametersIterator.next();
                parametersMap.put(keyName, value);
            }
        }

        return parametersMap;
    }

    private static boolean listContainsEvenAmountOfItems(List<?> list) {
        return list.size() % 2 == 0;
    }
}
