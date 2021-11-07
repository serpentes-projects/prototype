package io.serpentes.testing.assertions.composed.base.composite.collection.composite;

import io.serpentes.testing.assertions.composed.ComposedAssertion;
import io.serpentes.testing.assertions.input.base.composite.collection.composite.MapCollectionInputAssertion;
import io.serpentes.testing.assertions.input.content.json.composite.collection.composite.MapCollectionJsonContent;
import io.serpentes.testing.assertions.definition.base.composite.collection.composite.MapCollectionTypeDefinitionAssertion;
import io.serpentes.testing.assertions.definition.content.json_schema.composite.collection.composite.MapCollectionJsonSchemaContent;

public class MapCollectionComposedAssertion implements ComposedAssertion {
    @Override
    public MapCollectionTypeDefinitionAssertion getTypeDefinitionAssertion() {
        return new MapCollectionTypeDefinitionAssertion(new MapCollectionJsonSchemaContent());
    }

    @Override
    public MapCollectionInputAssertion getInputAssertion() {
        return new MapCollectionInputAssertion(new MapCollectionJsonContent());
    }
}
