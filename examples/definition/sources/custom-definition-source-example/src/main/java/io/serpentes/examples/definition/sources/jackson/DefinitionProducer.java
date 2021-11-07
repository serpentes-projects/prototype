package io.serpentes.examples.definition.sources.jackson;

import io.serpentes.examples.definition.sources.jackson.api.annotations.DefinitionProvider;
import jakarta.enterprise.inject.Produces;

import java.lang.reflect.Type;

public class DefinitionProducer {

    @Produces
    @DefinitionProvider
    Type getTypeProvider(){
        return UserProvidedTypeDefinition.class;
    }
}
