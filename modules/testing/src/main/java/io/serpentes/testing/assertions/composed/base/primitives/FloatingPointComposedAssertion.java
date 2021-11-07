package io.serpentes.testing.assertions.composed.base.primitives;

import io.serpentes.testing.assertions.composed.ComposedAssertion;
import io.serpentes.testing.assertions.input.content.json.primitive.FloatingPointJsonContent;
import io.serpentes.testing.assertions.input.base.primitive.FloatingPointInputAssertion;
import io.serpentes.testing.assertions.definition.content.json_schema.primitive.FloatingPointJsonSchemaContent;
import io.serpentes.testing.assertions.definition.base.primitive.FloatingPointTypeDefinitionAssertion;

public class FloatingPointComposedAssertion implements ComposedAssertion {
    @Override
    public FloatingPointTypeDefinitionAssertion getTypeDefinitionAssertion() {
        return new FloatingPointTypeDefinitionAssertion(new FloatingPointJsonSchemaContent());
    }

    @Override
    public FloatingPointInputAssertion getInputAssertion() {
        return new FloatingPointInputAssertion(new FloatingPointJsonContent());
    }
}
