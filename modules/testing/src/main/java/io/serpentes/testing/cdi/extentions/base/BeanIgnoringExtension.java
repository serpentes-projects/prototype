package io.serpentes.testing.cdi.extentions.base;

import jakarta.enterprise.event.Observes;
import jakarta.enterprise.inject.spi.Extension;
import jakarta.enterprise.inject.spi.ProcessAnnotatedType;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BeanIgnoringExtension implements Extension {

    private final Map<Type, Set<Annotation>> beansToIgnore;

    public BeanIgnoringExtension() {
        beansToIgnore = new HashMap<>();
    }

    public BeanIgnoringExtension(final Map<Type, Set<Annotation>> beansToIgnore) {
        this.beansToIgnore = beansToIgnore;
    }

    public <T> void ignore(final @Observes ProcessAnnotatedType<T> processAnnotatedType) {
        if (beansToIgnore.containsKey(processAnnotatedType.getAnnotatedType().getJavaClass())) {
            final Set<Annotation> qualifiers = beansToIgnore.get(processAnnotatedType.getAnnotatedType().getJavaClass());
            if (qualifiers.equals(processAnnotatedType.getAnnotatedType().getAnnotations())) {
                processAnnotatedType.veto();
            }
        }
    }
}
