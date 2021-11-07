package io.serpentes.input.sources.command_line.parameters.providers.cdi_extension;

public final class ClassUtils {
    @SuppressWarnings("unchecked")
    public static <T> Class<T> castClass(Class<?> aClass) {
        return (Class<T>)aClass;
    }
}