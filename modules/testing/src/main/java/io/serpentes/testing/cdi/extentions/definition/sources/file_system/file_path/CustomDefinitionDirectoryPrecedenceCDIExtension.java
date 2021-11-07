package io.serpentes.testing.cdi.extentions.definition.sources.file_system.file_path;

import io.serpentes.api.precedence.NameBasedPrecedence;
import io.serpentes.definition.sources.file_system.annotations.directories.DefinitionDirectoryPrecedence;
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

public class CustomDefinitionDirectoryPrecedenceCDIExtension implements Extension {
    private final CustomCDIExtension<NameBasedPrecedence, CustomDefinitionDirectoryPrecedence> customCDIExtension;
    private final CustomBeanBuilder<NameBasedPrecedence, CustomDefinitionDirectoryPrecedence> customBeanBuilder;

    //CDI Requires default constructor.
    public CustomDefinitionDirectoryPrecedenceCDIExtension() {
        customCDIExtension = null;
        customBeanBuilder = null;
    }

    public CustomDefinitionDirectoryPrecedenceCDIExtension(final CustomDefinitionDirectoryPrecedence nameBasedPrecedence) {
        customBeanBuilder = new CustomBeanBuilder<>();
        final Set<Annotation> qualifiers = new HashSet<>();
        qualifiers.add(new AnnotationLiteral<DefinitionDirectoryPrecedence>() {
        });
        customBeanBuilder.setQualifiers(qualifiers).setCreator((creationalContext, bean) -> nameBasedPrecedence);
        customCDIExtension = new CustomCDIExtension<>(CustomDefinitionDirectoryPrecedence.class, customBeanBuilder);
    }

    void afterBeanDiscovery(@Observes AfterBeanDiscovery abd, BeanManager bm) {
        customCDIExtension.afterBeanDiscovery(abd, bm);
    }

}
