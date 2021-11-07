package io.serpentes.testing.cdi.extentions.input.parsers;

import io.serpentes.api.annotations.input.parsers.InputParsersPrecedence;
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

public class CustomInputParsersPrecedenceCDIExtension implements Extension {
    private final CustomCDIExtension<NameBasedPrecedence, CustomInputParsersPrecedence> customCDIExtension;
    private final CustomBeanBuilder<NameBasedPrecedence, CustomInputParsersPrecedence> customBeanBuilder;

    //CDI Requires default constructor.
    public CustomInputParsersPrecedenceCDIExtension() {
        customCDIExtension = null;
        customBeanBuilder = null;
    }

    public CustomInputParsersPrecedenceCDIExtension(final CustomInputParsersPrecedence customInputParsersPrecedence) {
        customBeanBuilder = new CustomBeanBuilder<>();
        final Set<Annotation> qualifiers = new HashSet<>();
        qualifiers.add(new AnnotationLiteral<InputParsersPrecedence>() {
        });
        customBeanBuilder.setQualifiers(qualifiers).setCreator((creationalContext, bean) -> customInputParsersPrecedence);
        customCDIExtension = new CustomCDIExtension<>(CustomInputParsersPrecedence.class, customBeanBuilder);
    }

    void afterBeanDiscovery(@Observes AfterBeanDiscovery abd, BeanManager bm) {
        customCDIExtension.afterBeanDiscovery(abd, bm);
    }
}
