package io.serpentes.api.precedence;

import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;

public interface ClassBasedPrecedence extends Precedence {
    Class<? extends Annotation> getAnnotation();

    @Override
    @SuppressWarnings("unchecked")
    LinkedHashSet<Class<?>> getPrecedence();
}
