package io.serpentes.testing.cdi.utils;

import io.serpentes.api.definition.sources.DefinitionSources;
import io.serpentes.api.definition.trees.TypeTree;
import io.serpentes.input.stores.base.EmptyStoreClerk;
import jakarta.enterprise.inject.Default;
import jakarta.enterprise.inject.Instance;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import jakarta.enterprise.util.AnnotationLiteral;

public class CDIUtils {
    private CDIUtils() {
    }

    @SuppressWarnings("unchecked")
    public static <C extends SeContainer> C createSeContainer(final SeContainerInitializer seContainerInitializer) {
        return (C) seContainerInitializer.initialize();
    }

    public static void stockEmptyInputStore(final Instance<Object> containerInstance) {
        EmptyStoreClerk emptyStoreClerk = containerInstance.select(EmptyStoreClerk.class, new AnnotationLiteral<Default>() {
        }).get();
        emptyStoreClerk.stockStore();
    }

    public static TypeTree getPreferredTypeTree(final SeContainer container) {
        final DefinitionSources definitionSources = container.select(DefinitionSources.class).get();
        return definitionSources.getTypeTree();
    }
}
