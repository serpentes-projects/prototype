package io.serpentes.examples.input.sources.custom;

import io.serpentes.boot.se.SerpentesSE;
import io.serpentes.configuration.factories.jackson.JacksonConfigurationFactory;
import io.serpentes.input.sources.command_line.parameters.providers.cdi_extension.CommandLineParametersCDIExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class CustomInputSourceExample {

    @Test
    void testCustomInputSource() throws IOException {
        final var serpentes = new SerpentesSE();
        serpentes.addCDIExtension(new CommandLineParametersCDIExtension());
        serpentes.load();
        final var npmPackage = serpentes.createConfigurationFactory(JacksonConfigurationFactory.class)
                .create(NPMPackage.class);
        // NOTES:
        // 1. Since we're retrieving this from a remote source it would be undesirable to check exact values.
        // So we check that known required properties are not null instead.
        // 2. The schema located in /src/main/resources/configuration/schema is incomplete.
        // The schema in use was retrieved from https://json.schemastore.org/package.json (on 18/09/2021).
        // It does not define some properties present in the lodash package.json and as a result they are missing from the POJO.
        // The missing properties are mostly due to the schema in use.
        // However, even if the schema were properly defined, the prototype does not support certain JSON-schema properties.
        // For instance, "additionalProperties" is not supported, which would allow Serpentes to correctly map a Map<String, String> to a JSON object.
        // As an example, the "scripts" property in a NPM package would be a good fit for the "additionalProperties" definition of a JSON-schema.
        Assertions.assertNotNull(npmPackage);
        Assertions.assertNotNull(npmPackage.name);
        Assertions.assertNotNull(npmPackage.version);
        Assertions.assertNotNull(npmPackage.main);
    }
}
