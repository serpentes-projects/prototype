package io.serpentes.testing.cdi.extentions.base;

import jakarta.enterprise.context.spi.CreationalContext;
import jakarta.enterprise.inject.spi.Bean;
import jakarta.enterprise.inject.spi.BeanAttributes;
import jakarta.enterprise.inject.spi.InjectionPoint;
import jakarta.enterprise.inject.spi.InjectionTarget;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.function.BiFunction;

public class CustomBean<I, C extends I> implements Bean<I> {
    private final BeanAttributes<I> beanAttributes;
    private Set<Type> types;
    private Set<Annotation> qualifiers;
    private BiFunction<CreationalContext<I>, CustomBeanInfo<I, C>, I> creator;
    private Set<InjectionPoint> injectionPoints;
    private InjectionTarget<C> injectionTarget;

    CustomBean(BeanAttributes<I> beanAttributes) {
        this.beanAttributes = beanAttributes;
    }

    @Override
    public Set<Type> getTypes() {
        final HashSet<Type> beanTypes = new HashSet<>();
        beanTypes.addAll(this.types);
        beanTypes.addAll(this.beanAttributes.getTypes());
        return Collections.unmodifiableSet(beanTypes);
    }

    void setTypes(Set<Type> types) {
        this.types = types;
    }

    @Override
    public Set<Annotation> getQualifiers() {
        final HashSet<Annotation> annotations = new HashSet<>();
        annotations.addAll(this.beanAttributes.getQualifiers());
        annotations.addAll(this.qualifiers);
        return Collections.unmodifiableSet(annotations);
    }

    void setQualifiers(Set<Annotation> qualifiers) {
        this.qualifiers = qualifiers;
    }

    @Override
    public Class<? extends Annotation> getScope() {
        return beanAttributes.getScope();
    }

    @Override
    public String getName() {
        return beanAttributes.getName();
    }

    @Override
    public Set<Class<? extends Annotation>> getStereotypes() {
        return beanAttributes.getStereotypes();
    }

    @Override
    public boolean isAlternative() {
        return beanAttributes.isAlternative();
    }

    void setCreator(final BiFunction<CreationalContext<I>, CustomBeanInfo<I, C>, I> creator) {
        this.creator = creator;
    }

    @Override
    public I create(final CreationalContext<I> creationalContext) {
        return this.creator.apply(creationalContext, new CustomBeanInfo<>(this));
    }

    @Override
    public void destroy(final I instance,final CreationalContext<I> creationalContext) {
        creationalContext.release();
    }

    @Override
    public Class<C> getBeanClass() {
        return getInjectableConcreteClass();
    }

    @SuppressWarnings("unchecked")
    private Class<C> getInjectableConcreteClass() {
        final int INJECTABLE_CONCRETE_CLASS_GENERIC_INDEX = 1;
        return (Class<C>) getClass().getTypeParameters()[INJECTABLE_CONCRETE_CLASS_GENERIC_INDEX].getClass();
    }

    public InjectionTarget<C> getInjectionTarget() {
        return injectionTarget;
    }

    public void setInjectionTarget(final InjectionTarget<C> injectionTarget) {
        this.injectionTarget = injectionTarget;
    }

    @Override
    public Set<InjectionPoint> getInjectionPoints() {
        return injectionPoints;
    }

    public void setInjectionPoints(final Set<InjectionPoint> injectionPoints) {
        this.injectionPoints = injectionPoints;
    }

    @Override
    public boolean isNullable() {
        return false;
    }
}

