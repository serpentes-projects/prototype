package io.serpentes.testing.assertions.composed;

import io.serpentes.testing.assertions.input.base.InputAssertion;
import io.serpentes.testing.assertions.definition.base.TypeDefinitionAssertion;

public interface ComposedAssertion {
    TypeDefinitionAssertion getTypeDefinitionAssertion();

    InputAssertion<?> getInputAssertion();
}
