package io.serpentes.testing.assertions.composed.base.primitives;

import io.serpentes.testing.assertions.composed.ComposedAssertion;
import io.serpentes.testing.assertions.input.content.json.primitive.StringJsonContent;
import io.serpentes.testing.assertions.input.base.primitive.StringInputAssertion;
import io.serpentes.testing.assertions.definition.content.json_schema.primitive.StringJsonSchemaContent;
import io.serpentes.testing.assertions.definition.base.primitive.StringTypeDefinitionAssertion;

public class StringComposedAssertion implements ComposedAssertion {
    @Override
    public StringTypeDefinitionAssertion getTypeDefinitionAssertion() {
        return new StringTypeDefinitionAssertion(new StringJsonSchemaContent());
    }

    @Override
    public StringInputAssertion getInputAssertion() {
        return new StringInputAssertion(new StringJsonContent());
    }
}
