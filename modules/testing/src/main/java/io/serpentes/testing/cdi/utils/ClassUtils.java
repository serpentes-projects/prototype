package io.serpentes.testing.cdi.utils;

public final class ClassUtils {
    private ClassUtils() {
    }

    @SuppressWarnings("unchecked")
    public static <T> Class<T> castClass(Class<?> aClass) {
        return (Class<T>) aClass;
    }
}