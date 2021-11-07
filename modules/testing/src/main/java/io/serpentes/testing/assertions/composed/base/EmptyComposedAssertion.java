package io.serpentes.testing.assertions.composed.base;

import io.serpentes.testing.assertions.composed.ComposedAssertion;
import io.serpentes.testing.assertions.input.base.EmptyInputAssertion;
import io.serpentes.testing.assertions.input.content.json.NoContent;
import io.serpentes.testing.assertions.definition.base.EmptyTypeDefinitionAssertion;
import io.serpentes.testing.assertions.definition.content.json_schema.EmptyJsonSchemaContent;

public class EmptyComposedAssertion implements ComposedAssertion {

    @Override
    public EmptyTypeDefinitionAssertion getTypeDefinitionAssertion() {
        return new EmptyTypeDefinitionAssertion(new EmptyJsonSchemaContent());
    }

    @Override
    public EmptyInputAssertion getInputAssertion() {
        return new EmptyInputAssertion(new NoContent());
    }
}
