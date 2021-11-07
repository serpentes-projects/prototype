package io.serpentes.testing.cdi.extentions.input.sources.file_system.file_path;

import io.serpentes.input.sources.file_system.annotations.directories.SupportedInputDirectories;
import io.serpentes.input.sources.file_system.api.directories.InputDirectory;
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

public class CustomInputDirectoriesCDIExtension implements Extension {
    private final CustomCDIExtension<Set<InputDirectory>, HashSet<InputDirectory>> customCDIExtension;
    private final CustomBeanBuilder<Set<InputDirectory>, HashSet<InputDirectory>> customBeanBuilder;

    //CDI Requires default constructor.
    public CustomInputDirectoriesCDIExtension() {
        customCDIExtension = null;
        customBeanBuilder = null;
    }

    public CustomInputDirectoriesCDIExtension(final Set<InputDirectory> supportedInputDirectories) {
        customBeanBuilder = new CustomBeanBuilder<>();
        final Set<Annotation> qualifiers = new HashSet<>();
        qualifiers.add(new AnnotationLiteral<SupportedInputDirectories>() {
        });
        final Set<Type> types = new HashSet<>();
        types.add(new ParameterizedTypeImpl(Set.class, InputDirectory.class));
        customBeanBuilder.setTypes(types).setQualifiers(qualifiers).setCreator((creationalContext, bean) -> supportedInputDirectories);
        final Class<HashSet<InputDirectory>> concreteClass = ClassUtils.castClass(HashSet.class);
        customCDIExtension = new CustomCDIExtension<>(concreteClass, customBeanBuilder);
    }

    void afterBeanDiscovery(@Observes final AfterBeanDiscovery abd, final BeanManager bm) {
        customCDIExtension.afterBeanDiscovery(abd, bm);
    }
}
