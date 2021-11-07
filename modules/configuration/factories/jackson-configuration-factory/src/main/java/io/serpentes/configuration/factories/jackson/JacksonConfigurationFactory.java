package io.serpentes.configuration.factories.jackson;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.serpentes.api.configuration.factories.ConfigurationFactory;
import jakarta.inject.Inject;

import java.io.IOException;

public class JacksonConfigurationFactory implements ConfigurationFactory {
    private final JacksonTreeFactory jacksonTreeFactory;
    private ObjectMapper objectMapper;

    @Inject
    public JacksonConfigurationFactory(final JacksonTreeFactory jacksonTreeFactory) {
        this.jacksonTreeFactory = jacksonTreeFactory;
        this.objectMapper = new ObjectMapper();
    }

    public void setObjectMapper(final ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public <T> T create(final Class<T> pojoClass) throws IOException {
        final var jsonNode = jacksonTreeFactory.create(objectMapper);
        final var objectReader = this.objectMapper.readerFor(pojoClass);
        return objectReader.readValue(jsonNode);
    }

    public <T> T create(final TypeReference<T> typeReference) throws IOException {
        final var jsonNode = jacksonTreeFactory.create(objectMapper);
        final var objectReader = this.objectMapper.readerFor(typeReference);
        return objectReader.readValue(jsonNode);
    }
}
