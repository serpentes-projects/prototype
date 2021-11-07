package io.serpentes.testing.cdi.extentions.base;

import jakarta.enterprise.context.spi.CreationalContext;
import jakarta.enterprise.inject.spi.BeanAttributes;
import jakarta.enterprise.inject.spi.InjectionPoint;
import jakarta.enterprise.inject.spi.InjectionTarget;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;
import java.util.function.BiFunction;

public class CustomBeanBuilder<I, C extends I> {
    private BeanAttributes<I> beanAttributes = null;
    private Set<Type> types = new HashSet<>();
    private Set<Annotation> qualifiers = new HashSet<>();
    private Set<InjectionPoint> injectionPoints = new HashSet<>();
    private BiFunction<CreationalContext<I>, CustomBeanInfo<I, C>, I> creator = (context, bean) -> null;
    private InjectionTarget<C> injectionTarget;

    public CustomBeanBuilder<I, C> setBeanAttributes(BeanAttributes<I> beanAttributes) {
        this.beanAttributes = beanAttributes;
        return this;
    }

    public CustomBeanBuilder<I, C> setTypes(Set<Type> types) {
        this.types = types;
        return this;
    }

    public CustomBeanBuilder<I, C> setQualifiers(Set<Annotation> qualifiers) {
        this.qualifiers = qualifiers;
        return this;
    }

    public CustomBeanBuilder<I, C> setInjectionPoints(final Set<InjectionPoint> injectionPoints) {
        this.injectionPoints = injectionPoints;
        return this;
    }

    public CustomBeanBuilder<I, C> setCreator(BiFunction<CreationalContext<I>, CustomBeanInfo<I, C>, I> creator) {
        this.creator = creator;
        return this;
    }

    public CustomBeanBuilder<I, C> setInjectionTarget(InjectionTarget<C> injectionTarget) {
        this.injectionTarget = injectionTarget;
        return this;
    }

    public CustomBean<I, C> build() {
        final CustomBean<I, C> customBean = new CustomBean<>(beanAttributes);
        customBean.setTypes(this.types);
        customBean.setQualifiers(this.qualifiers);
        customBean.setInjectionPoints(this.injectionPoints);
        customBean.setCreator(this.creator);
        customBean.setInjectionTarget(this.injectionTarget);

        return customBean;
    }
}
