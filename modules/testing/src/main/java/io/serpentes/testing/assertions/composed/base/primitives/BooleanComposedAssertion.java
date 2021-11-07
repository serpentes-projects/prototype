package io.serpentes.testing.assertions.composed.base.primitives;

import io.serpentes.testing.assertions.composed.ComposedAssertion;
import io.serpentes.testing.assertions.input.content.json.primitive.BooleanJsonContent;
import io.serpentes.testing.assertions.input.base.primitive.BooleanInputAssertion;
import io.serpentes.testing.assertions.definition.content.json_schema.primitive.BooleanJsonSchemaContent;
import io.serpentes.testing.assertions.definition.base.primitive.BooleanTypeDefinitionAssertion;

public class BooleanComposedAssertion implements ComposedAssertion {
    @Override
    public BooleanTypeDefinitionAssertion getTypeDefinitionAssertion() {
        return new BooleanTypeDefinitionAssertion(new BooleanJsonSchemaContent());
    }

    @Override
    public BooleanInputAssertion getInputAssertion() {
        return new BooleanInputAssertion(new BooleanJsonContent());
    }
}
