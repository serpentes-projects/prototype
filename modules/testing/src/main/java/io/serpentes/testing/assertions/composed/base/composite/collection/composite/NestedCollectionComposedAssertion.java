package io.serpentes.testing.assertions.composed.base.composite.collection.composite;

import io.serpentes.testing.assertions.composed.ComposedAssertion;
import io.serpentes.testing.assertions.input.base.composite.collection.composite.NestedCollectionInputAssertion;
import io.serpentes.testing.assertions.input.content.json.composite.collection.composite.NestedCollectionJsonContent;
import io.serpentes.testing.assertions.definition.base.composite.collection.composite.NestedCollectionTypeDefinitionAssertion;
import io.serpentes.testing.assertions.definition.content.json_schema.composite.collection.composite.NestedCollectionJsonSchemaContent;

public class NestedCollectionComposedAssertion implements ComposedAssertion {
    @Override
    public NestedCollectionTypeDefinitionAssertion getTypeDefinitionAssertion() {
        return new NestedCollectionTypeDefinitionAssertion(new NestedCollectionJsonSchemaContent());
    }

    @Override
    public NestedCollectionInputAssertion getInputAssertion() {
        return new NestedCollectionInputAssertion(new NestedCollectionJsonContent());
    }
}
