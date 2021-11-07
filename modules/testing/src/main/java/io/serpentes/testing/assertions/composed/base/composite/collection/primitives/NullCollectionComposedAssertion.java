package io.serpentes.testing.assertions.composed.base.composite.collection.primitives;

import io.serpentes.testing.assertions.composed.ComposedAssertion;
import io.serpentes.testing.assertions.input.content.json.composite.collection.primitive.NullCollectionJsonContent;
import io.serpentes.testing.assertions.input.base.composite.collection.primitive.NullCollectionInputAssertion;
import io.serpentes.testing.assertions.definition.content.json_schema.composite.collection.primitive.NullCollectionJsonSchemaContent;
import io.serpentes.testing.assertions.definition.base.composite.collection.primitive.NullCollectionTypeDefinitionAssertion;

public class NullCollectionComposedAssertion implements ComposedAssertion {
    @Override
    public NullCollectionTypeDefinitionAssertion getTypeDefinitionAssertion() {
        return new NullCollectionTypeDefinitionAssertion(new NullCollectionJsonSchemaContent());
    }

    @Override
    public NullCollectionInputAssertion getInputAssertion() {
        return new NullCollectionInputAssertion(new NullCollectionJsonContent());
    }
}
