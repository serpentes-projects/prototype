package io.serpentes.testing.assertions.composed.base.composite.collection.primitives;

import io.serpentes.testing.assertions.composed.ComposedAssertion;
import io.serpentes.testing.assertions.input.content.json.composite.collection.primitive.FloatingPointCollectionJsonContent;
import io.serpentes.testing.assertions.input.base.composite.collection.primitive.FloatingPointCollectionInputAssertion;
import io.serpentes.testing.assertions.definition.content.json_schema.composite.collection.primitive.FloatingPointCollectionJsonSchemaContent;
import io.serpentes.testing.assertions.definition.base.composite.collection.primitive.FloatingPointCollectionTypeDefinitionAssertion;

public class FloatingPointCollectionComposedAssertion implements ComposedAssertion {
    @Override
    public FloatingPointCollectionTypeDefinitionAssertion getTypeDefinitionAssertion() {
        return new FloatingPointCollectionTypeDefinitionAssertion(new FloatingPointCollectionJsonSchemaContent());
    }

    @Override
    public FloatingPointCollectionInputAssertion getInputAssertion() {
        return new FloatingPointCollectionInputAssertion(new FloatingPointCollectionJsonContent());
    }
}
