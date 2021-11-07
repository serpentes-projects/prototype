package io.serpentes.input.sources.command_line.parameters.providers.cdi_extension;

import io.serpentes.api.annotations.input.sources.command_line.CommandLineParameters;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.context.spi.CreationalContext;
import jakarta.enterprise.inject.spi.Bean;
import jakarta.enterprise.inject.spi.InjectionPoint;
import jakarta.enterprise.inject.spi.InjectionTarget;
import jakarta.enterprise.inject.spi.PassivationCapable;
import jakarta.enterprise.util.AnnotationLiteral;
import org.jboss.weld.util.reflection.ParameterizedTypeImpl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class CommandLineParametersBean implements Bean<ArrayList<String>>, PassivationCapable {

    private final ArrayList<String> parameters;
    private final InjectionTarget<ArrayList<String>> injectionTarget;

    public CommandLineParametersBean(ArrayList<String> parameters, InjectionTarget<ArrayList<String>> injectionTarget) {
        this.parameters = parameters;
        this.injectionTarget = injectionTarget;
    }

    @Override
    public Class<?> getBeanClass() {
        return ArrayList.class;
    }

    @Override
    public Set<InjectionPoint> getInjectionPoints() {
        return new HashSet<>(injectionTarget.getInjectionPoints());
    }

    @Override
    public String getName() {
        return ArrayList.class.getTypeName();
    }

    @Override
    public Set<Annotation> getQualifiers() {
        Set<Annotation> qualifiers = new HashSet<>();
        qualifiers.add(new AnnotationLiteral<CommandLineParameters>() {
        });

        return qualifiers;

    }

    @Override
    public Class<? extends Annotation> getScope() {
        return Dependent.class;
    }

    @Override
    public Set<Class<? extends Annotation>> getStereotypes() {
        return Collections.emptySet();
    }

    @Override
    public Set<Type> getTypes() {
        Set<Type> types = new HashSet<>();
        final ParameterizedTypeImpl parameterizedType = new ParameterizedTypeImpl(ArrayList.class, String.class);
        types.add(parameterizedType);
        types.add(ArrayList.class);
        types.add(Object.class);

        return types;
    }

    @Override
    public boolean isAlternative() {
        return false;
    }

    @Override
    public boolean isNullable() {
        return false;
    }

    @Override
    public ArrayList<String> create(final CreationalContext<ArrayList<String>> creationalContext) {
        return parameters;
    }

    @Override
    public void destroy(final ArrayList<String> instance, final CreationalContext<ArrayList<String>> creationalContext) {
        creationalContext.release();
    }

    @Override
    public String getId() {
        return parameters.toString();
    }
}
