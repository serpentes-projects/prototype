package io.serpentes.examples.definition.sources.jackson;

import io.serpentes.api.annotations.definition.sources.DefinitionSourcesPrecedence;
import io.serpentes.api.precedence.ClassBasedPrecedence;
import jakarta.annotation.Priority;
import jakarta.enterprise.inject.Alternative;

import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;
import java.util.List;

@Priority(100)
@Alternative
@DefinitionSourcesPrecedence
public class AlternativeDefinitionSourcesPrecedence implements ClassBasedPrecedence {
    @Override
    public Class<? extends Annotation> getAnnotation() {
        return null;
    }

    @Override
    public LinkedHashSet<Class<?>> getPrecedence() {
        return new LinkedHashSet<>(List.of(JacksonDefinitionSource.class));
    }
}
