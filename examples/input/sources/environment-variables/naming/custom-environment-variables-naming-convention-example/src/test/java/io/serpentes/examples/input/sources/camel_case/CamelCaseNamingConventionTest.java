package io.serpentes.examples.input.sources.camel_case;

import io.serpentes.boot.se.SerpentesSE;
import io.serpentes.configuration.factories.jackson.JacksonConfigurationFactory;
import io.serpentes.api.Serpentes;
import io.serpentes.examples.input.sources.environment_variables.naming.camel_case.CamelCaseNamingConvention;
import io.serpentes.input.sources.command_line.parameters.providers.cdi_extension.CommandLineParametersCDIExtension;
import io.serpentes.input.sources.environment_variables.naming.EnvironmentVariableKeyNameStore;
import io.serpentes.input.sources.environment_variables.naming.KeyNameSourceValues;
import io.serpentes.input.stores.base.EmptyStoreClerk;
import io.serpentes.testing.TypeTestUtils;
import io.serpentes.testing.pojos.ClassWithAllValueTypes;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.junit.jupiter.api.*;
import org.junitpioneer.jupiter.SetEnvironmentVariable;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class CamelCaseNamingConventionTest {
    @Test
    @DisplayName("Feature(retrieval): 'Allows you to create a custom environment variable naming convention.' (CamelCase)")
    @Tags({
            @Tag("feat:retrieval"),
            @Tag("feat:retrieval:input"),
            @Tag("feat:retrieval:input:sources"),
            @Tag("feat:retrieval:input:sources:environment-variables"),
            @Tag("feat:retrieval:input:sources:environment-variables:naming"),
            @Tag("feat:retrieval:input:sources:environment-variables:naming:custom")
    })
    public void determineNamesUsingCamelCaseConvention(){
        final Weld weld = new Weld();
        // TODO: Ensure that users don't have to add this CDIExtension themselves.
        // Should be handled by the SEBootstrap module.
        weld.addExtension(new CommandLineParametersCDIExtension());
        final WeldContainer container = weld.initialize();

        final EmptyStoreClerk emptyStoreClerk = container.select(EmptyStoreClerk.class).get();
        emptyStoreClerk.stockStore();
        final CamelCaseNamingConvention camelCase = container.select(CamelCaseNamingConvention.class).get();
        camelCase.addEnvironmentVariableNamesToStore();
        final EnvironmentVariableKeyNameStore environmentVariableKeyNameStore = container.select(EnvironmentVariableKeyNameStore.class).get();

        final Collection<String> names = getNames(environmentVariableKeyNameStore);

        Assertions.assertEquals(8, names.size());
        Assertions.assertTrue(names.contains("boa"));
        Assertions.assertTrue(names.contains("boaNull"));
        Assertions.assertTrue(names.contains("boaBoolean"));
        Assertions.assertTrue(names.contains("boaInteger"));
        Assertions.assertTrue(names.contains("boaFloat"));
        Assertions.assertTrue(names.contains("boaString"));
        Assertions.assertTrue(names.contains("boaCollection"));
        Assertions.assertTrue(names.contains("boaMap"));

        weld.shutdown();
    }

    private Set<String> getNames(EnvironmentVariableKeyNameStore environmentVariableKeyNameStore) {
        final Set<String> names = new HashSet<>();
        for (KeyNameSourceValues value : environmentVariableKeyNameStore.values()) {
            names.addAll(value.getNames());
        }
        return names;
    }

    @Test
    @DisplayName("Feature(retrieval): 'Setting a default environment-variable naming convention.' (CamelCase)")
    @Tags({
            @Tag("feat:retrieval"),
            @Tag("feat:retrieval:input"),
            @Tag("feat:retrieval:input:sources"),
            @Tag("feat:retrieval:input:sources:environment-variables"),
            @Tag("feat:retrieval:input:sources:environment-variables:naming"),
            @Tag("feat:retrieval:input:sources:environment-variables:naming:default")
    })
    @SetEnvironmentVariable(key = "boaNull", value = "null")
    @SetEnvironmentVariable(key = "boaBoolean", value = "true")
    @SetEnvironmentVariable(key = "boaInteger", value = "1")
    @SetEnvironmentVariable(key = "boaFloat", value = "1.1")
    @SetEnvironmentVariable(key = "boaString", value = "\"string\"")
    @SetEnvironmentVariable(key = "boaCollection", value = "[]")
    @SetEnvironmentVariable(key = "boaMap", value = "{}")
    public void registeringCamelCaseNamingConventionAsDefaultNamingConvention() throws IOException {
        final Serpentes serpentes = new SerpentesSE();
        // TODO: Ensure that users don't have to add this CDIExtension themselves.
        // Should be handled by the SEBootstrap module.
        serpentes.addCDIExtension(new CommandLineParametersCDIExtension());
        serpentes.load();
        final JacksonConfigurationFactory configurationFactory = serpentes.createConfigurationFactory(JacksonConfigurationFactory.class);
        TypeTestUtils.assertObject(configurationFactory.create(ClassWithAllValueTypes.class));
    }
}