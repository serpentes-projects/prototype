package io.serpentes.testing.cdi.extentions.definition.sources.file_system.file_properties;

import io.serpentes.definition.sources.file_system.annotations.file_properties.DefaultDefinitionFileProperties;
import io.serpentes.definition.sources.file_system.api.file_properties.DefinitionFileProperties;
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


public class CustomDefaultDefinitionFilePropertiesCDIExtension implements Extension {
    private final CustomCDIExtension<DefinitionFileProperties, CustomDefinitionFileProperties> customCDIExtension;
    private final CustomBeanBuilder<DefinitionFileProperties, CustomDefinitionFileProperties> customBeanBuilder;

    //CDI Requires default constructor.
    public CustomDefaultDefinitionFilePropertiesCDIExtension() {
        customCDIExtension = null;
        customBeanBuilder = null;
    }

    public CustomDefaultDefinitionFilePropertiesCDIExtension(CustomDefinitionFileProperties defaultSchemaFileProperties) {
        customBeanBuilder = new CustomBeanBuilder<>();
        Set<Annotation> qualifiers = new HashSet<>();
        qualifiers.add(new AnnotationLiteral<DefaultDefinitionFileProperties>() {
        });
        customBeanBuilder.setQualifiers(qualifiers).setCreator((creationalContext, bean) -> defaultSchemaFileProperties);
        customCDIExtension = new CustomCDIExtension<>(CustomDefinitionFileProperties.class, customBeanBuilder);
    }

    void afterBeanDiscovery(@Observes AfterBeanDiscovery abd, BeanManager bm) {
        customCDIExtension.afterBeanDiscovery(abd, bm);
    }

}
