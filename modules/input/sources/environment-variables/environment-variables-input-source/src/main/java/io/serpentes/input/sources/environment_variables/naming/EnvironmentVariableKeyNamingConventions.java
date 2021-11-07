package io.serpentes.input.sources.environment_variables.naming;

import io.serpentes.api.precedence.ClassBasedPrecedence;
import io.serpentes.input.sources.environment_variables.annotations.naming.EnvironmentVariableNamingConventionPrecedence;
import io.serpentes.input.sources.environment_variables.api.naming.EnvironmentVariableKeyNamingConvention;
import jakarta.enterprise.inject.Any;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Singleton
public class EnvironmentVariableKeyNamingConventions {
    private final Set<EnvironmentVariableKeyNamingConvention> orderedNamingConventions = new LinkedHashSet<>();

    @Inject
    public EnvironmentVariableKeyNamingConventions(@Any final Instance<EnvironmentVariableKeyNamingConvention> namingConventions, @EnvironmentVariableNamingConventionPrecedence
                                                           final ClassBasedPrecedence precedence) {
        this.orderNamingConventions(namingConventions, precedence);
    }

    private void orderNamingConventions(final Instance<EnvironmentVariableKeyNamingConvention> namingConventions, final ClassBasedPrecedence precedence) {
        for (final Class<?> precedenceClass : precedence.getPrecedence()) {
            for (final EnvironmentVariableKeyNamingConvention namingConvention : namingConventions) {
                if (precedenceClass.equals(namingConvention.getClass())) {
                    orderedNamingConventions.add(namingConvention);
                }
            }
        }
    }

    public void deriveEnvironmentVariableKeyNamesFromTypeDefinition() {
        final List<EnvironmentVariableKeyNamingConvention> environmentVariableKeyNamingConventions = Arrays.asList(orderedNamingConventions.toArray(new EnvironmentVariableKeyNamingConvention[]{}));
        for (int i = environmentVariableKeyNamingConventions.size() - 1; i >= 0; i--) {
            final var convention = environmentVariableKeyNamingConventions.get(i);
            convention.addEnvironmentVariableNamesToStore();
        }
    }
}
