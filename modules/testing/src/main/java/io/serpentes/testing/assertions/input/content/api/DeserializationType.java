package io.serpentes.testing.assertions.input.content.api;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class DeserializationType<T> implements ForceGenericType<T> {
    private final Type type;

    public DeserializationType() {
        Type superClass = getClass().getGenericSuperclass();
        if (superClass instanceof Class<?>) {
            throw new IllegalArgumentException("Internal error: Lacking generic type for deserialization.");
        }
        type = ((ParameterizedType) superClass).getActualTypeArguments()[0];
    }

    public Type getType() {
        return type;
    }

    @Override
    public void naught(T forceGenericType) {
    }
}

