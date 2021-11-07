package io.serpentes.api.definition.sources;

import io.serpentes.api.annotations.definition.sources.DefinitionSourcesPrecedence;
import io.serpentes.api.definition.trees.TypeTree;
import io.serpentes.api.precedence.ClassBasedPrecedence;
import jakarta.enterprise.inject.Any;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;

@Singleton
public final class DefinitionSources {
    private final LinkedHashSet<DefinitionSource> orderedDefinitionSources = new LinkedHashSet<>();
    private TypeTree typeTree;

    @Inject
    public DefinitionSources(@Any final Instance<DefinitionSource> definitionSources, @DefinitionSourcesPrecedence final ClassBasedPrecedence precedence) {
        this.orderDefinitionSourcesAccordingToPrecedence(definitionSources, precedence);
    }

    private void orderDefinitionSourcesAccordingToPrecedence(final Instance<DefinitionSource> definitionSources, final ClassBasedPrecedence precedence) {
        for (final Class<?> precedenceClass : precedence.getPrecedence()) {
            for (final DefinitionSource definitionSource : definitionSources) {
                if (precedenceClass.equals(definitionSource.getClass())) {
                    orderedDefinitionSources.add(definitionSource);
                }
            }
        }
    }

    public TypeTree getTypeTree() {
        if (typeTree == null) {
            final List<DefinitionSource> definitionSources = Arrays.asList(orderedDefinitionSources.toArray(new DefinitionSource[]{}));
            final List<TypeTree> typeTrees = new ArrayList<>();
            //TODO: Check if this order is correct, doesnt matter for prototype as there is only one source.
            for (int i = definitionSources.size() - 1; i >= 0; i--) {
                DefinitionSource definitionSource = definitionSources.get(i);
                typeTrees.add(definitionSource.createTypeTree());
            }
            this.typeTree = typeTrees.get(0);
        }

        return typeTree;
    }
}
