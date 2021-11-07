package io.serpentes.input.sources.command_line.parameters.providers.cdi_extension;


import io.serpentes.api.annotations.input.sources.command_line.CommandLineParameters;
import io.serpentes.input.sources.command_line.parameters.providers.CommandLineParametersProvider;
import io.serpentes.input.sources.command_line.parameters.providers.CommandLineParametersProviderUtils;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.Map;

@CommandLineParameters
public class CDIExtensionBasedCommandLineParametersProvider implements CommandLineParametersProvider {
    @Inject
    @CommandLineParameters
    private ArrayList<String> parameters;

    @Override
    public ArrayList<String> toList() {
        return parameters;
    }

    @Override
    public Map<String, String> toMap() {
        return CommandLineParametersProviderUtils.listToMap(parameters);
    }
}
