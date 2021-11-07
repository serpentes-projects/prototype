package io.serpentes.testing.cdi.extentions.input.sources.environment_variables.naming;

import io.serpentes.api.precedence.ClassBasedPrecedence;
import io.serpentes.input.sources.environment_variables.annotations.naming.EnvironmentVariableNamingConventionPrecedence;
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

public class CustomEnvironmentVariableKeyNamingConventionPrecedenceCDIExtension implements Extension {
    private final CustomCDIExtension<ClassBasedPrecedence, CustomEnvironmentVariableKeyNamingConventionPrecedence> customCDIExtension;
    private final CustomBeanBuilder<ClassBasedPrecedence, CustomEnvironmentVariableKeyNamingConventionPrecedence> customBeanBuilder;

    //CDI Requires default constructor.
    private CustomEnvironmentVariableKeyNamingConventionPrecedenceCDIExtension() {
        customCDIExtension = null;
        customBeanBuilder = null;
    }

    public CustomEnvironmentVariableKeyNamingConventionPrecedenceCDIExtension(final CustomEnvironmentVariableKeyNamingConventionPrecedence classBasedPrecedence) {
        customBeanBuilder = new CustomBeanBuilder<>();
        final Set<Annotation> qualifiers = new HashSet<>();
        qualifiers.add(new AnnotationLiteral<EnvironmentVariableNamingConventionPrecedence>() {
        });
        customBeanBuilder.setQualifiers(qualifiers).setCreator((creationalContext, bean) -> classBasedPrecedence);
        customCDIExtension = new CustomCDIExtension<>(CustomEnvironmentVariableKeyNamingConventionPrecedence.class, customBeanBuilder);
    }

    void afterBeanDiscovery(@Observes AfterBeanDiscovery abd, BeanManager bm) {
        customCDIExtension.afterBeanDiscovery(abd, bm);
    }

}
