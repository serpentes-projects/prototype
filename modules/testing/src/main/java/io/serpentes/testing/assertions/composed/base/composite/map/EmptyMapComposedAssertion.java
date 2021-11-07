package io.serpentes.testing.assertions.composed.base.composite.map;

import io.serpentes.testing.assertions.composed.ComposedAssertion;
import io.serpentes.testing.assertions.input.content.json.composite.map.EmptyMapJsonContent;
import io.serpentes.testing.assertions.input.base.composite.map.EmptyMapInputAssertion;
import io.serpentes.testing.assertions.definition.content.json_schema.composite.map.EmptyMapJsonSchemaContent;
import io.serpentes.testing.assertions.definition.base.composite.map.EmptyMapTypeDefinitionAssertion;

public class EmptyMapComposedAssertion implements ComposedAssertion {
    @Override
    public EmptyMapTypeDefinitionAssertion getTypeDefinitionAssertion() {
        return new EmptyMapTypeDefinitionAssertion(new EmptyMapJsonSchemaContent());
    }

    @Override
    public EmptyMapInputAssertion getInputAssertion() {
        return new EmptyMapInputAssertion(new EmptyMapJsonContent());
    }
}
