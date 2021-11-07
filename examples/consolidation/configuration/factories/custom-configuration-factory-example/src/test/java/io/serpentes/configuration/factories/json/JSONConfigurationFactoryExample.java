package io.serpentes.configuration.factories.json;

import io.serpentes.boot.se.SerpentesSE;
import io.serpentes.input.sources.command_line.parameters.providers.cdi_extension.CommandLineParametersCDIExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junitpioneer.jupiter.SetEnvironmentVariable;

public class JSONConfigurationFactoryExample {
    @Test
    @Tags({
            
            @Tag("feat:consolidation"),
            @Tag("feat:consolidation:configuration"),
            @Tag("feat:consolidation:configuration:factories"),
            @Tag("feat:consolidation:configuration:factories:custom")
    })
    @SetEnvironmentVariable(key = "BOA_STRING", value = "\"custom-configuration-factory\"")
    void customConfigurationFactory() {
        final var serpentes = new SerpentesSE();
        serpentes.addCDIExtension(new CommandLineParametersCDIExtension(new String[]{"{\"float\":3.1415}"}));
        serpentes.load();
        final var json = serpentes.createConfigurationFactory(JSONConfigurationFactory.class).create();

        Assertions.assertNotNull(json);
        Assertions.assertTrue(json.contains("custom-configuration-factory"));
        Assertions.assertTrue(json.contains("3.1415"));
    }
}
