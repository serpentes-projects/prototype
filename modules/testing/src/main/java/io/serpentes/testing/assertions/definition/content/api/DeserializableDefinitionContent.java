package io.serpentes.testing.assertions.definition.content.api;

import java.lang.reflect.Type;

public interface DeserializableDefinitionContent extends DefinitionContent {
    Type asType();
}
