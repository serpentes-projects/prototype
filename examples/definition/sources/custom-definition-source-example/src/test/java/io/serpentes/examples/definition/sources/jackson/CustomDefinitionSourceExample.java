package io.serpentes.examples.definition.sources.jackson;

import io.serpentes.boot.se.SerpentesSE;
import io.serpentes.configuration.factories.jackson.JacksonConfigurationFactory;
import io.serpentes.input.sources.command_line.parameters.providers.cdi_extension.CommandLineParametersCDIExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class CustomDefinitionSourceExample {

    @Test
    void someTest() throws IOException {
        final var serpentes = new SerpentesSE();
        // TODO: Ensure that users don't have to add this CDIExtension themselves.
        // Should be handled by the SEBootstrap module.
        serpentes.addCDIExtension(new CommandLineParametersCDIExtension());
        serpentes.load();
        final var configurationFactory = serpentes.createConfigurationFactory(JacksonConfigurationFactory.class);
        final var userProvidedTypeDefinition = configurationFactory.create(UserProvidedTypeDefinition.class);
        Assertions.assertTrue(userProvidedTypeDefinition.aBoolean);
        Assertions.assertEquals(1, userProvidedTypeDefinition.integer);
        Assertions.assertEquals(1.1, userProvidedTypeDefinition.floatingPoint);
        Assertions.assertEquals("userProvided", userProvidedTypeDefinition.string);
        Assertions.assertNotNull(userProvidedTypeDefinition.collection);
        Assertions.assertEquals(0, userProvidedTypeDefinition.collection.size());
        Assertions.assertNotNull(userProvidedTypeDefinition.map);
        Assertions.assertEquals(0, userProvidedTypeDefinition.map.size());
    }
}