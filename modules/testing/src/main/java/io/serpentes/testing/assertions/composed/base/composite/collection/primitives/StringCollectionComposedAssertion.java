package io.serpentes.testing.assertions.composed.base.composite.collection.primitives;

import io.serpentes.testing.assertions.composed.ComposedAssertion;
import io.serpentes.testing.assertions.input.content.json.composite.collection.primitive.StringCollectionJsonContent;
import io.serpentes.testing.assertions.input.base.composite.collection.primitive.StringCollectionInputAssertion;
import io.serpentes.testing.assertions.definition.content.json_schema.composite.collection.primitive.StringCollectionJsonSchemaContent;
import io.serpentes.testing.assertions.definition.base.composite.collection.primitive.StringCollectionTypeDefinitionAssertion;

public class StringCollectionComposedAssertion implements ComposedAssertion {
    @Override
    public StringCollectionTypeDefinitionAssertion getTypeDefinitionAssertion() {
        return new StringCollectionTypeDefinitionAssertion(new StringCollectionJsonSchemaContent());
    }

    @Override
    public StringCollectionInputAssertion getInputAssertion() {
        return new StringCollectionInputAssertion(new StringCollectionJsonContent());
    }
}
