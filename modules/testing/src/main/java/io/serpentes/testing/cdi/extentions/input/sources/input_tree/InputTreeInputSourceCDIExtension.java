package io.serpentes.testing.cdi.extentions.input.sources.input_tree;

import io.serpentes.api.input.sources.InputSource;
import io.serpentes.api.input.trees.InputTree;
import io.serpentes.api.definition.trees.TypeTree;
import io.serpentes.testing.cdi.extentions.base.CustomBeanBuilder;
import io.serpentes.testing.cdi.extentions.base.CustomCDIExtension;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.inject.spi.*;
import jakarta.enterprise.util.AnnotationLiteral;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

public class InputTreeInputSourceCDIExtension implements Extension {
    private final CustomCDIExtension<InputTreeInputSource, InputTreeInputSource> customCDIExtension;
    private final CustomBeanBuilder<InputTreeInputSource, InputTreeInputSource> customBeanBuilder;
    private TypeTree typeTree;
    private InputTree inputTree;

    //CDI Requires default constructor.
    public InputTreeInputSourceCDIExtension() {
        customCDIExtension = null;
        customBeanBuilder = null;
    }

    public InputTreeInputSourceCDIExtension(final TypeTree typeTree, final InputTree inputTree) {
        this.typeTree = typeTree;
        this.inputTree = inputTree;

        customBeanBuilder = new CustomBeanBuilder<>();
        final Set<Annotation> qualifiers = new HashSet<>();
        qualifiers.add(new AnnotationLiteral<ExtensionInputSource>() {
        });
        final Set<Type> types = new HashSet<>();
        types.add(InputSource.class);
        types.add(InputTreeInputSource.class);
        customBeanBuilder.setQualifiers(qualifiers).setTypes(types);
        customCDIExtension = new CustomCDIExtension<>(InputTreeInputSource.class, customBeanBuilder);
    }

    void afterBeanDiscovery(@Observes AfterBeanDiscovery abd, BeanManager bm) {
        AnnotatedType<InputTreeInputSource> type = null;
        for (AnnotatedType<InputTreeInputSource> annotatedType : abd.getAnnotatedTypes(InputTreeInputSource.class)) {
            type = annotatedType;
            break;
        }
        if(type != null) {
            customBeanBuilder.setInjectionTarget(bm.createInjectionTarget(type));
            customBeanBuilder.setCreator((creationalContext, customBeanInfo) -> {
                InputTreeInputSource instance = new InputTreeInputSource(this.typeTree, this.inputTree);
                final InjectionTarget<InputTreeInputSource> injectionTarget = customBeanInfo.getInjectionTarget();
                injectionTarget.inject(instance, creationalContext);
                injectionTarget.postConstruct(instance);
                return instance;
            });
            customCDIExtension.afterBeanDiscovery(abd, bm);
        }
    }
}