package io.serpentes.testing.assertions.composed.base.composite.collection.primitives;

import io.serpentes.testing.assertions.composed.ComposedAssertion;
import io.serpentes.testing.assertions.input.content.json.composite.collection.primitive.IntegerCollectionJsonContent;
import io.serpentes.testing.assertions.input.base.composite.collection.primitive.IntegerCollectionInputAssertion;
import io.serpentes.testing.assertions.definition.content.json_schema.composite.collection.primitive.IntegerCollectionJsonSchemaContent;
import io.serpentes.testing.assertions.definition.base.composite.collection.primitive.IntegerCollectionTypeDefinitionAssertion;

public class IntegerCollectionComposedAssertion implements ComposedAssertion {
    @Override
    public IntegerCollectionTypeDefinitionAssertion getTypeDefinitionAssertion() {
        return new IntegerCollectionTypeDefinitionAssertion(new IntegerCollectionJsonSchemaContent());
    }

    @Override
    public IntegerCollectionInputAssertion getInputAssertion() {
        return new IntegerCollectionInputAssertion(new IntegerCollectionJsonContent());
    }
}
