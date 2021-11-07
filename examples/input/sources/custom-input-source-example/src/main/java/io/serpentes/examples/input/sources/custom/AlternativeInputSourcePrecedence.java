package io.serpentes.examples.input.sources.custom;

import io.serpentes.api.annotations.input.sources.InputSourcesPrecedence;
import io.serpentes.api.precedence.ClassBasedPrecedence;
import jakarta.annotation.Priority;
import jakarta.enterprise.inject.Alternative;

import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;
import java.util.List;

@Priority(100)
@Alternative
@InputSourcesPrecedence
public class AlternativeInputSourcePrecedence implements ClassBasedPrecedence {
    @Override
    public Class<? extends Annotation> getAnnotation() {
        return null;
    }

    @Override
    public LinkedHashSet<Class<?>> getPrecedence() {
        return new LinkedHashSet<>(List.of(URLInputSource.class));
    }
}
