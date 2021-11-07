package io.serpentes.testing.cdi.extentions.input.sources.environment_variables.naming;

import io.serpentes.api.annotations.input.KeyNamingConvention;
import io.serpentes.api.precedence.ClassBasedPrecedence;
import io.serpentes.input.sources.environment_variables.api.naming.EnvironmentVariableKeyNamingConvention;

import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;
import java.util.Set;

public class CustomEnvironmentVariableKeyNamingConventionPrecedence implements ClassBasedPrecedence {
    private final Set<Class<? extends EnvironmentVariableKeyNamingConvention>> precedence;

    public CustomEnvironmentVariableKeyNamingConventionPrecedence(final Set<Class<? extends EnvironmentVariableKeyNamingConvention>> precedence) {
        this.precedence = precedence;
    }

    @Override
    public Class<? extends Annotation> getAnnotation() {
        return KeyNamingConvention.class;
    }

    @Override
    public LinkedHashSet<Class<?>> getPrecedence() {
        return new LinkedHashSet<>(precedence);
    }
}
