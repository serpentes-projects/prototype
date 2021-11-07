package io.serpentes.examples.input.sources.environment_variables.naming.camel_case;

import io.serpentes.api.annotations.input.KeyNamingConvention;
import io.serpentes.api.precedence.ClassBasedPrecedence;
import io.serpentes.input.sources.environment_variables.annotations.naming.EnvironmentVariableNamingConventionPrecedence;
import io.serpentes.input.sources.environment_variables.naming.snake_case.SnakeCaseKeyNamingConvention;
import jakarta.annotation.Priority;
import jakarta.enterprise.inject.Alternative;

import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;
import java.util.List;

@Priority(100)
@Alternative
@EnvironmentVariableNamingConventionPrecedence
public class AlternativeEnvironmentVariableNamingConventionPrecedence implements ClassBasedPrecedence {
    @Override
    public Class<? extends Annotation> getAnnotation() {
        return KeyNamingConvention.class;
    }

    @Override
    public LinkedHashSet<Class<?>> getPrecedence() {
        return new LinkedHashSet<>(List.of(CamelCaseNamingConvention.class, SnakeCaseKeyNamingConvention.class));
    }
}