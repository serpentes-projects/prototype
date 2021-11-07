package io.serpentes.testing.cdi.extentions.definition.sources.file_system.file_properties;


import io.serpentes.definition.sources.file_system.annotations.file_properties.SupportedDefinitionFileProperties;
import io.serpentes.definition.sources.file_system.api.file_properties.DefinitionFileProperties;
import io.serpentes.testing.cdi.extentions.base.CustomBeanBuilder;
import io.serpentes.testing.cdi.extentions.base.CustomCDIExtension;
import io.serpentes.testing.cdi.utils.ClassUtils;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.inject.spi.AfterBeanDiscovery;
import jakarta.enterprise.inject.spi.BeanManager;
import jakarta.enterprise.inject.spi.Extension;
import jakarta.enterprise.util.AnnotationLiteral;
import org.jboss.weld.util.reflection.ParameterizedTypeImpl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

public class CustomDEfinitionFilePropertiesCDIExtension implements Extension {
    private final CustomCDIExtension<Set<DefinitionFileProperties>, HashSet<DefinitionFileProperties>> customCDIExtension;
    private final CustomBeanBuilder<Set<DefinitionFileProperties>, HashSet<DefinitionFileProperties>> customBeanBuilder;

    //CDI Requires default constructor.
    public CustomDEfinitionFilePropertiesCDIExtension() {
        customCDIExtension = null;
        customBeanBuilder = null;
    }

    public CustomDEfinitionFilePropertiesCDIExtension(Set<DefinitionFileProperties> definitionFileProperties) {
        customBeanBuilder = new CustomBeanBuilder<>();
        final Set<Annotation> qualifiers = new HashSet<>();
        qualifiers.add(new AnnotationLiteral<SupportedDefinitionFileProperties>() {
        });
        final Set<Type> types = new HashSet<>();
        types.add(new ParameterizedTypeImpl(Set.class, DefinitionFileProperties.class));
        customBeanBuilder.setTypes(types).setQualifiers(qualifiers).setCreator((creationalContext, bean) -> definitionFileProperties);
        final Class<HashSet<DefinitionFileProperties>> concreteClass = ClassUtils.castClass(HashSet.class);
        customCDIExtension = new CustomCDIExtension<>(concreteClass, customBeanBuilder);
    }

    void afterBeanDiscovery(@Observes AfterBeanDiscovery abd, BeanManager bm) {
        customCDIExtension.afterBeanDiscovery(abd, bm);
    }

}
