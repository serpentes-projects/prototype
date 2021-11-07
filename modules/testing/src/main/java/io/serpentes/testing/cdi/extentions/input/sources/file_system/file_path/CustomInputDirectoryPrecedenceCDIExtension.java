package io.serpentes.testing.cdi.extentions.input.sources.file_system.file_path;

import io.serpentes.api.precedence.NameBasedPrecedence;
import io.serpentes.input.sources.file_system.annotations.directories.InputDirectoryPrecedence;
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


public class CustomInputDirectoryPrecedenceCDIExtension implements Extension {
    private final CustomCDIExtension<NameBasedPrecedence, CustomInputDirectoryPrecedence> customCDIExtension;
    private final CustomBeanBuilder<NameBasedPrecedence, CustomInputDirectoryPrecedence> customBeanBuilder;

    //CDI Requires default constructor.
    public CustomInputDirectoryPrecedenceCDIExtension() {
        customCDIExtension = null;
        customBeanBuilder = null;
    }

    public CustomInputDirectoryPrecedenceCDIExtension(final CustomInputDirectoryPrecedence nameBasedPrecedence) {
        customBeanBuilder = new CustomBeanBuilder<>();
        final Set<Annotation> qualifiers = new HashSet<>();
        qualifiers.add(new AnnotationLiteral<InputDirectoryPrecedence>() {
        });
        customBeanBuilder.setQualifiers(qualifiers).setCreator((creationalContext, bean) -> nameBasedPrecedence);
        customCDIExtension = new CustomCDIExtension<>(CustomInputDirectoryPrecedence.class, customBeanBuilder);
    }

    void afterBeanDiscovery(@Observes AfterBeanDiscovery abd, BeanManager bm) {
        customCDIExtension.afterBeanDiscovery(abd, bm);
    }
}
