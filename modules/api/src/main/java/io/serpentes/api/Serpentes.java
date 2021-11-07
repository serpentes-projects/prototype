package io.serpentes.api;

import io.serpentes.api.configuration.factories.ConfigurationFactory;
import jakarta.enterprise.inject.spi.Extension;

public interface Serpentes {
    void load();
    <F extends ConfigurationFactory> F createConfigurationFactory(final Class<F> factoryClass);
    void addCDIExtension(Extension extension);
}
