package io.serpentes.testing.cdi.extentions.base;

import io.serpentes.testing.cdi.utils.ClassUtils;
import jakarta.enterprise.inject.spi.AfterBeanDiscovery;
import jakarta.enterprise.inject.spi.AnnotatedType;
import jakarta.enterprise.inject.spi.BeanAttributes;
import jakarta.enterprise.inject.spi.BeanManager;

public class CustomCDIExtension<I, C extends I> {
    private final Class<C> concreteClass;
    private final CustomBeanBuilder<I, C> customBeanBuilder;

    public CustomCDIExtension(final Class<C> concreteClass, CustomBeanBuilder<I, C> customBeanBuilder) {
        this.concreteClass = concreteClass;
        this.customBeanBuilder = customBeanBuilder;
    }

    public void afterBeanDiscovery(final AfterBeanDiscovery abd, final BeanManager beanManager) {
        final Class<I> clazz = ClassUtils.castClass(concreteClass);
        final AnnotatedType<I> annotatedType = beanManager.createAnnotatedType(clazz);
        final BeanAttributes<I> beanAttributes = beanManager.createBeanAttributes(annotatedType);
        customBeanBuilder.setBeanAttributes(beanAttributes);
        abd.addBean(customBeanBuilder.build());
    }
}
