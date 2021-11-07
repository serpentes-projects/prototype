package io.serpentes.testing.cdi.extentions.input.sources.file_system.file_properties;

import io.serpentes.input.sources.file_system.annotations.file_properties.DefaultInputFileProperties;
import io.serpentes.input.sources.file_system.api.file_properties.InputFileProperties;
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


public class CustomDefaultInputFilePropertiesCDIExtension implements Extension {
    private final CustomCDIExtension<InputFileProperties, CustomInputFileProperties> customCDIExtension;
    private final CustomBeanBuilder<InputFileProperties, CustomInputFileProperties> customBeanBuilder;

    //CDI Requires default constructor.
    public CustomDefaultInputFilePropertiesCDIExtension() {
        customCDIExtension = null;
        customBeanBuilder = null;
    }

    public CustomDefaultInputFilePropertiesCDIExtension(final CustomInputFileProperties defaultInputFileProperties) {
        customBeanBuilder = new CustomBeanBuilder<>();
        Set<Annotation> qualifiers = new HashSet<>();
        qualifiers.add(new AnnotationLiteral<DefaultInputFileProperties>() {
        });
        customBeanBuilder.setQualifiers(qualifiers).setCreator((creationalContext, bean) -> defaultInputFileProperties);
        customCDIExtension = new CustomCDIExtension<>(CustomInputFileProperties.class, customBeanBuilder);
    }

    void afterBeanDiscovery(@Observes AfterBeanDiscovery abd, BeanManager bm) {
        customCDIExtension.afterBeanDiscovery(abd, bm);
    }

}
