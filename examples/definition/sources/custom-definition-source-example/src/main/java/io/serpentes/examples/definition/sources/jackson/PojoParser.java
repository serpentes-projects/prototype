package io.serpentes.examples.definition.sources.jackson;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.serpentes.api.definition.trees.TypeTree;
import io.serpentes.examples.definition.sources.jackson.api.annotations.DefinitionProvider;
import io.serpentes.examples.definition.sources.jackson.visitors.TypeVisitor;
import io.serpentes.definition.trees.base.DefaultTypeTree;
import jakarta.inject.Inject;

import java.lang.reflect.Type;


public class PojoParser {

    private final Type type;

    @Inject
    public PojoParser(final @DefinitionProvider Type type){
        this.type = type;
    }

    public TypeTree buildTree(){
        final var objectMapper = new ObjectMapper();
        final var serializerProvider = objectMapper.getSerializerProviderInstance();
        final var javaType = objectMapper.constructType(type);
        final var serializerFactory = objectMapper.getSerializerFactory();
        final JsonSerializer<Object> typeSerializer;
        try {
            typeSerializer = serializerFactory.createSerializer(serializerProvider, javaType);
            final var fieldVisitor = new TypeVisitor(serializerProvider, serializerFactory);
            typeSerializer.acceptJsonFormatVisitor(fieldVisitor, javaType);
            return fieldVisitor.getTree();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        }

        return new DefaultTypeTree();
    }
}
