package io.serpentes.testing.assertions.composed.base.composite.collection;

import io.serpentes.testing.assertions.composed.ComposedAssertion;
import io.serpentes.testing.assertions.input.base.composite.collection.EmptyCollectionInputAssertion;
import io.serpentes.testing.assertions.input.content.json.composite.collection.EmptyCollectionJsonContent;
import io.serpentes.testing.assertions.definition.base.composite.collection.EmptyCollectionTypeDefinitionAssertion;
import io.serpentes.testing.assertions.definition.content.json_schema.composite.collection.EmptyCollectionJsonSchemaContent;

public class EmptyCollectionComposedAssertion implements ComposedAssertion {
    @Override
    public EmptyCollectionTypeDefinitionAssertion getTypeDefinitionAssertion() {
        return new EmptyCollectionTypeDefinitionAssertion(new EmptyCollectionJsonSchemaContent());
    }

    @Override
    public EmptyCollectionInputAssertion getInputAssertion() {
        return new EmptyCollectionInputAssertion(new EmptyCollectionJsonContent());
    }
}
