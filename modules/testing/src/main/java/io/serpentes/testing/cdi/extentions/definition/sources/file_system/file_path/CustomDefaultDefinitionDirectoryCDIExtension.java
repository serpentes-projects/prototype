package io.serpentes.testing.cdi.extentions.definition.sources.file_system.file_path;

import io.serpentes.definition.sources.file_system.annotations.directories.DefaultDefinitionDirectory;
import io.serpentes.definition.sources.file_system.api.directories.DefinitionDirectory;
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

public class CustomDefaultDefinitionDirectoryCDIExtension implements Extension {
    private final CustomCDIExtension<DefinitionDirectory, CustomDefinitionDirectory> customCDIExtension;
    private final CustomBeanBuilder<DefinitionDirectory, CustomDefinitionDirectory> customBeanBuilder;

    //CDI Requires default constructor.
    public CustomDefaultDefinitionDirectoryCDIExtension() {
        customCDIExtension = null;
        customBeanBuilder = null;
    }

    public CustomDefaultDefinitionDirectoryCDIExtension(final CustomDefinitionDirectory defaultSchemaDirectory) {
        customBeanBuilder = new CustomBeanBuilder<>();
        final Set<Annotation> qualifiers = new HashSet<>();
        qualifiers.add(new AnnotationLiteral<DefaultDefinitionDirectory>() {
        });
        customBeanBuilder.setQualifiers(qualifiers).setCreator((creationalContext, bean) -> defaultSchemaDirectory);
        customCDIExtension = new CustomCDIExtension<>(CustomDefinitionDirectory.class, customBeanBuilder);
    }

    void afterBeanDiscovery(@Observes final AfterBeanDiscovery abd, final BeanManager bm) {
        customCDIExtension.afterBeanDiscovery(abd, bm);
    }
}
