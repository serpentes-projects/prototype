package io.serpentes.testing.assertions.definition.base;

import java.lang.reflect.Type;

public interface DeserializableTypeDefinitionAssertion extends TypeDefinitionAssertion {
    Type getType();
}
