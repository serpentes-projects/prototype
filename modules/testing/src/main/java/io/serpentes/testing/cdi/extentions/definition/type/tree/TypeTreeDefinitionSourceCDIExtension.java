package io.serpentes.testing.cdi.extentions.definition.type.tree;

import io.serpentes.api.definition.sources.DefinitionSource;
import io.serpentes.api.definition.trees.TypeTree;
import io.serpentes.testing.cdi.extentions.base.CustomBeanBuilder;
import io.serpentes.testing.cdi.extentions.base.CustomCDIExtension;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.inject.spi.AfterBeanDiscovery;
import jakarta.enterprise.inject.spi.BeanManager;
import jakarta.enterprise.inject.spi.Extension;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

public class TypeTreeDefinitionSourceCDIExtension implements Extension {
    private final CustomCDIExtension<DefinitionSource, TypeTreeDefinitionSource> customCDIExtension;
    private final CustomBeanBuilder<DefinitionSource, TypeTreeDefinitionSource> customBeanBuilder;

    //CDI Requires default constructor.
    public TypeTreeDefinitionSourceCDIExtension() {
        customCDIExtension = null;
        customBeanBuilder = null;
    }

    public TypeTreeDefinitionSourceCDIExtension(final TypeTree typeTree) {
        customBeanBuilder = new CustomBeanBuilder<>();
        final Set<Annotation> qualifiers = new HashSet<>();
        final var typeTreeDefinitionSource = new TypeTreeDefinitionSource(typeTree);
        customBeanBuilder.setQualifiers(qualifiers).setCreator((creationalContext, bean) -> typeTreeDefinitionSource);
        customCDIExtension = new CustomCDIExtension<>(TypeTreeDefinitionSource.class, customBeanBuilder);
    }

    void afterBeanDiscovery(@Observes AfterBeanDiscovery abd, BeanManager bm) {
        customCDIExtension.afterBeanDiscovery(abd, bm);
    }
}
