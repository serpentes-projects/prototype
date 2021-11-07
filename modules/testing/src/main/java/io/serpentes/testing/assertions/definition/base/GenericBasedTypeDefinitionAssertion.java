package io.serpentes.testing.assertions.definition.base;

import java.lang.reflect.Type;

public interface GenericBasedTypeDefinitionAssertion<T> extends TypeDefinitionAssertion {
    Type getType();
}
