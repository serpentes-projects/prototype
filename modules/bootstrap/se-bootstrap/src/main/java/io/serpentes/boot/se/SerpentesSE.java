package io.serpentes.boot.se;

import io.serpentes.boot.se.input.sources.InputSources;
import io.serpentes.api.Serpentes;
import io.serpentes.input.stores.base.EmptyStoreClerk;
import io.serpentes.api.configuration.factories.ConfigurationFactory;
import jakarta.enterprise.inject.spi.Extension;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

public class SerpentesSE implements Serpentes {
    private final Weld weld;
    private WeldContainer container;
    private InputSources inputSources;

    public SerpentesSE() {
        weld = new Weld(); //TODO: Remove dependency on Weld, allow user to provide CDIContainer.
    }

    /**
     * Load values from input-sources.
     */
    public void load() {
        initializeCDIContainer(); //TODO: Remove this when the dependency on Weld has been removed.
        loadInputStore();
        loadInputSources();
        inputSources.addValuesToInputStore();
    }

    public <F extends ConfigurationFactory> F createConfigurationFactory(final Class<F> factoryClass) {
        //FIXME: This will throw a NullPointerException if the container is not loaded yet.
        return this.container.select(factoryClass).get();
    }

    private void initializeCDIContainer() {
        if (this.container == null) {
            container = weld.initialize();
        }
    }

    private void loadInputStore() {
        EmptyStoreClerk emptyStoreClerk = container.select(EmptyStoreClerk.class).get();
        emptyStoreClerk.stockStore();
    }

    private void loadInputSources() {
        inputSources = container.select(InputSources.class).get();
    }

    public void addCDIExtension(Extension extension) {
        weld.addExtension(extension);
    }
}
