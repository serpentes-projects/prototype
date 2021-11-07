package io.serpentes.input.sources.command_line.parameters.providers.weld_boostrap;

import io.serpentes.input.sources.command_line.parameters.providers.CommandLineParametersProvider;
import io.serpentes.input.sources.command_line.parameters.providers.CommandLineParametersProviderUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * This class should only be injected if you're:
 * 1. Using Weld as CDI manager.
 * 2. Bootstrapping your application with Weld.
 * <p>
 * If you're not then @Parameters will not be added to Weld's BeanManager which may cause unexpected behaviour.
 */
//@CommandLineParameters
public class WeldBootstrappedCommandLineParametersProvider implements CommandLineParametersProvider {
    //    @Inject
//    @Parameters
    private ArrayList<String> parameters;

    @Override
    public List<String> toList() {
        return parameters;
    }

    @Override
    public Map<String, String> toMap() {
        return CommandLineParametersProviderUtils.listToMap( parameters);
    }
}
