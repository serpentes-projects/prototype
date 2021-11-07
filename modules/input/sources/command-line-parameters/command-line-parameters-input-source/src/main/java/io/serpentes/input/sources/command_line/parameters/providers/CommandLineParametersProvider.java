package io.serpentes.input.sources.command_line.parameters.providers;

import java.util.List;
import java.util.Map;

public interface CommandLineParametersProvider {
    List<String> toList();

    Map<String, String> toMap();
}
