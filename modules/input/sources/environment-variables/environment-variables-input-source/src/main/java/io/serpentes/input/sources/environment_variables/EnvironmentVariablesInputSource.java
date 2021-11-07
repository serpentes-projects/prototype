package io.serpentes.input.sources.environment_variables;

import io.serpentes.api.input.sources.InputSource;
import io.serpentes.input.sources.environment_variables.naming.EnvironmentVariableKeyNamingConventions;
import jakarta.inject.Inject;

public class EnvironmentVariablesInputSource implements InputSource {
    private final EnvironmentVariableKeyNamingConventions namingConventions;
    private final EnvironmentVariablesInputStoreClerk inputStoreClerk;

    @Inject
    public EnvironmentVariablesInputSource(final EnvironmentVariableKeyNamingConventions namingConventions, final EnvironmentVariablesInputStoreClerk inputStoreClerk){
        this.namingConventions = namingConventions;
        this.inputStoreClerk = inputStoreClerk;
    }

    @Override
    public void addValuesToInputStore() {
        namingConventions.deriveEnvironmentVariableKeyNamesFromTypeDefinition();
        inputStoreClerk.stockStore();
    }
}
