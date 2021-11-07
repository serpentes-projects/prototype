package io.serpentes.testing.assertions.composed.base.primitives;

import io.serpentes.testing.assertions.composed.ComposedAssertion;
import io.serpentes.testing.assertions.input.content.json.primitive.NullJsonContent;
import io.serpentes.testing.assertions.input.base.primitive.NullInputAssertion;
import io.serpentes.testing.assertions.definition.content.json_schema.primitive.NullJsonSchemaContent;
import io.serpentes.testing.assertions.definition.base.primitive.NullTypeDefinitionAssertion;

public class NullComposedAssertion implements ComposedAssertion {
    @Override
    public NullTypeDefinitionAssertion getTypeDefinitionAssertion() {
        return new NullTypeDefinitionAssertion(new NullJsonSchemaContent());
    }

    @Override
    public NullInputAssertion getInputAssertion() {
        return new NullInputAssertion(new NullJsonContent());
    }
}
