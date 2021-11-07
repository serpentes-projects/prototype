package io.serpentes.boot.se.input.sources.environment_variables;

import io.serpentes.api.annotations.input.KeyNamingConvention;
import io.serpentes.api.precedence.ClassBasedPrecedence;
import io.serpentes.input.sources.environment_variables.annotations.naming.EnvironmentVariableNamingConventionPrecedence;
import io.serpentes.input.sources.environment_variables.naming.snake_case.SnakeCaseKeyNamingConvention;

import java.lang.annotation.Annotation;
import java.util.Collections;
import java.util.LinkedHashSet;

@EnvironmentVariableNamingConventionPrecedence
public class DefaultEnvironmentVariableNamingConventionPrecedence implements ClassBasedPrecedence {
    @Override
    public Class<? extends Annotation> getAnnotation() {
        return KeyNamingConvention.class;
    }

    @Override
    public LinkedHashSet<Class<?>> getPrecedence() {
        return new LinkedHashSet<>(Collections.singletonList(SnakeCaseKeyNamingConvention.class));
    }
}
