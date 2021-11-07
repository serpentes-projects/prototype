package io.serpentes.examples.input.parsers.custom;

import io.serpentes.boot.se.SerpentesSE;
import io.serpentes.configuration.factories.jackson.JacksonConfigurationFactory;
import io.serpentes.input.sources.command_line.parameters.providers.cdi_extension.CommandLineParametersCDIExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class CustomInputParserExample {

    @Test
    @Tags({
            @Tag("feat:retrieval"),
            @Tag("feat:retrieval:input"),
            @Tag("feat:retrieval:input:parsers"),
            @Tag("feat:retrieval:input:parsers:custom")
    })
    void testCustomInputParser() throws IOException {
        final var serpentes = new SerpentesSE();
        serpentes.addCDIExtension(new CommandLineParametersCDIExtension());
        serpentes.load();

        final var desiredPOJO = serpentes.createConfigurationFactory(JacksonConfigurationFactory.class).create(DesiredPOJO.class);

        Assertions.assertNotNull(desiredPOJO);
        Assertions.assertNull(desiredPOJO.aNull);
        Assertions.assertTrue(desiredPOJO.aBoolean);
        Assertions.assertEquals(1, desiredPOJO.integer);
        Assertions.assertEquals(1.1, desiredPOJO.aFloat);
        Assertions.assertEquals("custom-input-parser-example", desiredPOJO.string);
        Assertions.assertNotNull(desiredPOJO.collection);
        Assertions.assertEquals(0, desiredPOJO.collection.size());
        Assertions.assertNotNull(desiredPOJO.map);
        Assertions.assertEquals(0, desiredPOJO.map.size());
    }
}
