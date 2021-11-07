package io.serpentes.testing.assertions.composed.base.primitives;

import io.serpentes.testing.assertions.composed.ComposedAssertion;
import io.serpentes.testing.assertions.input.content.json.primitive.IntegerJsonContent;
import io.serpentes.testing.assertions.input.base.primitive.IntegerInputAssertion;
import io.serpentes.testing.assertions.definition.content.json_schema.primitive.IntegerJsonSchemaContent;
import io.serpentes.testing.assertions.definition.base.primitive.IntegerTypeDefinitionAssertion;

public class IntegerComposedAssertion implements ComposedAssertion {
    @Override
    public IntegerTypeDefinitionAssertion getTypeDefinitionAssertion() {
        return new IntegerTypeDefinitionAssertion(new IntegerJsonSchemaContent());
    }

    @Override
    public IntegerInputAssertion getInputAssertion() {
        return new IntegerInputAssertion(new IntegerJsonContent());
    }
}
