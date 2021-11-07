package io.serpentes.testing.cdi.extentions.definition.sources;

import io.serpentes.api.annotations.definition.sources.DefinitionSourcesPrecedence;
import io.serpentes.api.precedence.ClassBasedPrecedence;
import io.serpentes.testing.cdi.extentions.base.CustomBeanBuilder;
import io.serpentes.testing.cdi.extentions.base.CustomCDIExtension;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.inject.spi.AfterBeanDiscovery;
import jakarta.enterprise.inject.spi.BeanManager;
import jakarta.enterprise.inject.spi.Extension;
import jakarta.enterprise.util.AnnotationLiteral;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

public class CustomDefinitionSourcesPrecedenceCDIExtension implements Extension {
    private final CustomCDIExtension<ClassBasedPrecedence, CustomDefinitionSourcesPrecedence> customCDIExtension;
    private final CustomBeanBuilder<ClassBasedPrecedence, CustomDefinitionSourcesPrecedence> customBeanBuilder;

    //CDI Requires default constructor.
    public CustomDefinitionSourcesPrecedenceCDIExtension() {
        customCDIExtension = null;
        customBeanBuilder = null;
    }

    public CustomDefinitionSourcesPrecedenceCDIExtension(final CustomDefinitionSourcesPrecedence customDefinitionSourcesPrecedence) {
        customBeanBuilder = new CustomBeanBuilder<>();
        final Set<Annotation> qualifiers = new HashSet<>();
        qualifiers.add(new AnnotationLiteral<DefinitionSourcesPrecedence>() {
        });
        customBeanBuilder.setQualifiers(qualifiers).setCreator((creationalContext, bean) -> customDefinitionSourcesPrecedence);
        customCDIExtension = new CustomCDIExtension<>(CustomDefinitionSourcesPrecedence.class, customBeanBuilder);
    }

    void afterBeanDiscovery(@Observes AfterBeanDiscovery abd, BeanManager bm) {
        customCDIExtension.afterBeanDiscovery(abd, bm);
    }
}
