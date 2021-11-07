package io.serpentes.testing.cdi.extentions.definition.sources.file_system.file_path;


import io.serpentes.definition.sources.file_system.annotations.directories.SupportedDefinitionDirectories;
import io.serpentes.definition.sources.file_system.api.directories.DefinitionDirectory;
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

public class CustomDefinitionDirectoriesCDIExtension implements Extension {
    private final CustomCDIExtension<Set<DefinitionDirectory>, HashSet<DefinitionDirectory>> customCDIExtension;
    private final CustomBeanBuilder<Set<DefinitionDirectory>, HashSet<DefinitionDirectory>> customBeanBuilder;

    //CDI Requires default constructor.
    public CustomDefinitionDirectoriesCDIExtension() {
        customCDIExtension = null;
        customBeanBuilder = null;
    }

    public CustomDefinitionDirectoriesCDIExtension(Set<DefinitionDirectory> supportedSchemaDirectories) {
        customBeanBuilder = new CustomBeanBuilder<>();
        final Set<Annotation> qualifiers = new HashSet<>();
        qualifiers.add(new AnnotationLiteral<SupportedDefinitionDirectories>() {
        });
        final Set<Type> types = new HashSet<>();
        types.add(new ParameterizedTypeImpl(Set.class, DefinitionDirectory.class));
        customBeanBuilder.setTypes(types).setQualifiers(qualifiers).setCreator((creationalContext, bean) -> supportedSchemaDirectories);
        final Class<HashSet<DefinitionDirectory>> concreteClass = ClassUtils.castClass(HashSet.class);
        customCDIExtension = new CustomCDIExtension<>(concreteClass, customBeanBuilder);
    }

    void afterBeanDiscovery(@Observes AfterBeanDiscovery abd, BeanManager bm) {
        customCDIExtension.afterBeanDiscovery(abd, bm);
    }
}
