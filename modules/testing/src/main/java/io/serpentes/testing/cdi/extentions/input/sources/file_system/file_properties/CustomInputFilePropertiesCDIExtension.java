package io.serpentes.testing.cdi.extentions.input.sources.file_system.file_properties;

import io.serpentes.input.sources.file_system.api.file_properties.InputFileProperties;
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

public class CustomInputFilePropertiesCDIExtension implements Extension {
    private final CustomCDIExtension<Set<InputFileProperties>, HashSet<InputFileProperties>> customCDIExtension;
    private final CustomBeanBuilder<Set<InputFileProperties>, HashSet<InputFileProperties>> customBeanBuilder;

    //CDI Requires default constructor.
    public CustomInputFilePropertiesCDIExtension() {
        customCDIExtension = null;
        customBeanBuilder = null;
    }

    public CustomInputFilePropertiesCDIExtension(final Set<InputFileProperties> supportedInputFileProperties) {
        customBeanBuilder = new CustomBeanBuilder<>();
        final Set<Annotation> qualifiers = new HashSet<>();
        qualifiers.add(new AnnotationLiteral<io.serpentes.input.sources.file_system.annotations.file_properties.InputFileProperties>() {
        });
        final Set<Type> types = new HashSet<>();
        types.add(new ParameterizedTypeImpl(Set.class, InputFileProperties.class));
        customBeanBuilder.setTypes(types).setQualifiers(qualifiers).setCreator((creationalContext, bean) -> supportedInputFileProperties);
        final Class<HashSet<InputFileProperties>> concreteClass = ClassUtils.castClass(HashSet.class);
        customCDIExtension = new CustomCDIExtension<>(concreteClass, customBeanBuilder);
    }

    void afterBeanDiscovery(@Observes AfterBeanDiscovery abd, BeanManager bm) {
        customCDIExtension.afterBeanDiscovery(abd, bm);
    }
}
