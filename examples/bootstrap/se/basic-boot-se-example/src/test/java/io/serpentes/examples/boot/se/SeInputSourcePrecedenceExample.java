package io.serpentes.examples.boot.se;

import io.serpentes.boot.se.SerpentesSE;
import io.serpentes.configuration.factories.jackson.JacksonConfigurationFactory;
import io.serpentes.api.Serpentes;
import io.serpentes.input.sources.command_line.parameters.providers.cdi_extension.CommandLineParametersCDIExtension;
import io.serpentes.testing.assertions.input.base.composite.JSONUtils;
import io.serpentes.testing.pojos.ClassWithAllValueTypes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junitpioneer.jupiter.SetEnvironmentVariable;

import java.io.IOException;

public class SeInputSourcePrecedenceExample {
    @Test
    void fileSystemAsDefault() throws IOException {
        final Serpentes serpentes = new SerpentesSE();
        // TODO: Ensure that users don't have to add this CDIExtension themselves.
        // Should be handled by the SEBootstrap module.
        serpentes.addCDIExtension(new CommandLineParametersCDIExtension());
        serpentes.load();
        final JacksonConfigurationFactory configurationFactory = serpentes.createConfigurationFactory(JacksonConfigurationFactory.class);
        final var classWithPrimitiveValueTypes = configurationFactory.create(ClassWithAllValueTypes.class);
        Assertions.assertTrue(classWithPrimitiveValueTypes.getABoolean());
    }

    @Test
    @SetEnvironmentVariable(key = "BOA_STRING", value = "\"env-var\"")
    void environmentVariablesPrecedeFileSystem() throws IOException {
        final Serpentes serpentes = new SerpentesSE();
        // TODO: Ensure that users don't have to add this CDIExtension themselves.
        // Should be handled by the SEBootstrap module.
        serpentes.addCDIExtension(new CommandLineParametersCDIExtension());
        serpentes.load();
        final JacksonConfigurationFactory configurationFactory = serpentes.createConfigurationFactory(JacksonConfigurationFactory.class);
        final var classWithPrimitiveValueTypes = configurationFactory.create(ClassWithAllValueTypes.class);

        Assertions.assertNotNull(classWithPrimitiveValueTypes);
        Assertions.assertEquals("env-var", classWithPrimitiveValueTypes.getString());
    }

    @Test
    @SetEnvironmentVariable(key = "BOA_STRING", value = "\"env-var\"")
    void commandLinePrecedesEnvironmentVariable() throws IOException {
        final Serpentes serpentes = new SerpentesSE();
        final String expected = "command-line";
        final String mapWithStringValueType = "{" + JSONUtils.wrapInQuotationMarks("string") + ":" + JSONUtils.wrapInQuotationMarks(expected) + "}";
        final String[] parameters = new String[]{mapWithStringValueType};
        // TODO: Ensure that users don't have to add this CDIExtension themselves.
        // Should be handled by the SEBootstrap module.
        serpentes.addCDIExtension(new CommandLineParametersCDIExtension(parameters));
        serpentes.load();
        final JacksonConfigurationFactory configurationFactory = serpentes.createConfigurationFactory(JacksonConfigurationFactory.class);
        final var classWithPrimitiveValueTypes = configurationFactory.create(ClassWithAllValueTypes.class);

        Assertions.assertNotNull(classWithPrimitiveValueTypes);
        Assertions.assertEquals(expected, classWithPrimitiveValueTypes.getString());
    }
}
