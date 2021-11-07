package io.serpentes.testing.cdi.extentions.input.sources.file_system.file_properties;

import io.serpentes.api.precedence.NameBasedPrecedence;
import io.serpentes.input.sources.file_system.annotations.file_properties.InputFilePropertiesPrecedence;
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


public class CustomInputFilePropertiesPrecedenceCDIExtension implements Extension {
    private final CustomCDIExtension<NameBasedPrecedence, CustomInputFilePropertiesPrecedence> customCDIExtension;
    private final CustomBeanBuilder<NameBasedPrecedence, CustomInputFilePropertiesPrecedence> customBeanBuilder;

    //CDI Requires default constructor.
    public CustomInputFilePropertiesPrecedenceCDIExtension() {
        customCDIExtension = null;
        customBeanBuilder = null;
    }

    public CustomInputFilePropertiesPrecedenceCDIExtension(final CustomInputFilePropertiesPrecedence nameBasedPrecedence) {
        customBeanBuilder = new CustomBeanBuilder<>();
        final Set<Annotation> qualifiers = new HashSet<>();
        qualifiers.add(new AnnotationLiteral<InputFilePropertiesPrecedence>() {
        });
        customBeanBuilder.setQualifiers(qualifiers).setCreator((creationalContext, bean) -> nameBasedPrecedence);
        customCDIExtension = new CustomCDIExtension<>(CustomInputFilePropertiesPrecedence.class, customBeanBuilder);
    }

    void afterBeanDiscovery(@Observes AfterBeanDiscovery abd, BeanManager bm) {
        customCDIExtension.afterBeanDiscovery(abd, bm);
    }
}
