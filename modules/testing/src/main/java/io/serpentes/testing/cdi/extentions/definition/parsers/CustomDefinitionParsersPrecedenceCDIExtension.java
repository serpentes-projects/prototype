package io.serpentes.testing.cdi.extentions.definition.parsers;

import io.serpentes.api.annotations.definition.parsers.DefinitionParsersPrecedence;
import io.serpentes.api.precedence.NameBasedPrecedence;
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

public class CustomDefinitionParsersPrecedenceCDIExtension implements Extension {
    private final CustomCDIExtension<NameBasedPrecedence, CustomDefinitionParsersPrecedence> customCDIExtension;
    private final CustomBeanBuilder<NameBasedPrecedence, CustomDefinitionParsersPrecedence> customBeanBuilder;

    //CDI Requires default constructor.
    public CustomDefinitionParsersPrecedenceCDIExtension() {
        customCDIExtension = null;
        customBeanBuilder = null;
    }

    public CustomDefinitionParsersPrecedenceCDIExtension(final CustomDefinitionParsersPrecedence customDefinitionParsersPrecedence) {
        customBeanBuilder = new CustomBeanBuilder<>();
        final Set<Annotation> qualifiers = new HashSet<>();
        qualifiers.add(new AnnotationLiteral<DefinitionParsersPrecedence>() {
        });
        customBeanBuilder.setQualifiers(qualifiers).setCreator((creationalContext, bean) -> customDefinitionParsersPrecedence);
        customCDIExtension = new CustomCDIExtension<>(CustomDefinitionParsersPrecedence.class, customBeanBuilder);
    }

    void afterBeanDiscovery(@Observes AfterBeanDiscovery abd, BeanManager bm) {
        customCDIExtension.afterBeanDiscovery(abd, bm);
    }
}
