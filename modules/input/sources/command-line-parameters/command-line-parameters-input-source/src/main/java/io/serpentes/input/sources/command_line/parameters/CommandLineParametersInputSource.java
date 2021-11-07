package io.serpentes.input.sources.command_line.parameters;

import io.serpentes.api.input.sources.InputSource;
import jakarta.inject.Inject;

public class CommandLineParametersInputSource implements InputSource {
    private final CommandLineParametersStoreClerk commandLineArgumentsStoreClerk;
    @Inject
    public CommandLineParametersInputSource(final CommandLineParametersStoreClerk commandLineArgumentsStoreClerk){
        this.commandLineArgumentsStoreClerk = commandLineArgumentsStoreClerk;
    }

    @Override
    public void addValuesToInputStore() {
        commandLineArgumentsStoreClerk.stockStore();
    }
}
