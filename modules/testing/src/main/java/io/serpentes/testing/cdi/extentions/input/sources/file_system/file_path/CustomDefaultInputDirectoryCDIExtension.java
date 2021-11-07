package io.serpentes.testing.cdi.extentions.input.sources.file_system.file_path;

import io.serpentes.input.sources.file_system.annotations.directories.DefaultInputDirectory;
import io.serpentes.input.sources.file_system.api.directories.InputDirectory;
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

public class CustomDefaultInputDirectoryCDIExtension implements Extension {
    private final CustomCDIExtension<InputDirectory, CustomInputDirectory> customCDIExtension;
    private final CustomBeanBuilder<InputDirectory, CustomInputDirectory> customBeanBuilder;

    //CDI Requires default constructor.
    public CustomDefaultInputDirectoryCDIExtension() {
        customCDIExtension = null;
        customBeanBuilder = null;
    }

    public CustomDefaultInputDirectoryCDIExtension(final CustomInputDirectory defaultInputDirectory) {
        customBeanBuilder = new CustomBeanBuilder<>();
        final Set<Annotation> qualifiers = new HashSet<>();
        qualifiers.add(new AnnotationLiteral<DefaultInputDirectory>() {
        });
        customBeanBuilder.setQualifiers(qualifiers).setCreator((creationalContext, bean) -> defaultInputDirectory);
        customCDIExtension = new CustomCDIExtension<>(CustomInputDirectory.class, customBeanBuilder);
    }

    void afterBeanDiscovery(@Observes final AfterBeanDiscovery abd, final BeanManager bm) {
        customCDIExtension.afterBeanDiscovery(abd, bm);
    }
}
