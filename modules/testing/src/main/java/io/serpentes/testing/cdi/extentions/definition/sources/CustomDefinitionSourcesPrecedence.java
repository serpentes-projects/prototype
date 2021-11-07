package io.serpentes.testing.cdi.extentions.definition.sources;

import io.serpentes.api.annotations.definition.sources.Source;
import io.serpentes.api.precedence.ClassBasedPrecedence;

import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;
import java.util.Set;

public class CustomDefinitionSourcesPrecedence implements ClassBasedPrecedence {
    private final Set<Class<?>> precedence;

    public CustomDefinitionSourcesPrecedence(final Set<Class<?>> precedence) {
        this.precedence = precedence;
    }

    @Override
    public Class<? extends Annotation> getAnnotation() {
        return Source.class;
    }

    @Override
    public LinkedHashSet<Class<?>> getPrecedence() {
        return new LinkedHashSet<>(this.precedence);
    }
}

