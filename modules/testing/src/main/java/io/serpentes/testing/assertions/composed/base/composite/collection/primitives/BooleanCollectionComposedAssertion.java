package io.serpentes.testing.assertions.composed.base.composite.collection.primitives;

import io.serpentes.testing.assertions.composed.ComposedAssertion;
import io.serpentes.testing.assertions.input.content.json.composite.collection.primitive.BooleanCollectionJsonContent;
import io.serpentes.testing.assertions.input.base.composite.collection.primitive.BooleanCollectionInputAssertion;
import io.serpentes.testing.assertions.definition.content.json_schema.composite.collection.primitive.BooleanCollectionJsonSchemaContent;
import io.serpentes.testing.assertions.definition.base.composite.collection.primitive.BooleanCollectionTypeDefinitionAssertion;

public class BooleanCollectionComposedAssertion implements ComposedAssertion {
    @Override
    public BooleanCollectionTypeDefinitionAssertion getTypeDefinitionAssertion() {
        return new BooleanCollectionTypeDefinitionAssertion(new BooleanCollectionJsonSchemaContent());
    }

    @Override
    public BooleanCollectionInputAssertion getInputAssertion() {
        return new BooleanCollectionInputAssertion(new BooleanCollectionJsonContent());
    }
}
