package io.serpentes.testing.assertions.composed.base.composite.map;

import io.serpentes.testing.assertions.composed.ComposedAssertion;
import io.serpentes.testing.assertions.input.content.json.composite.map.values.MapWithCompositeValueTypesJsonContent;
import io.serpentes.testing.assertions.input.base.composite.map.MapWithCompositeValueTypesInputAssertion;
import io.serpentes.testing.assertions.definition.content.json_schema.composite.map.MapWithCompositeValueTypesJsonSchemaContent;
import io.serpentes.testing.assertions.definition.base.composite.map.MapWithCompositeValueTypesTypeDefinitionAssertion;

public class MapWithCompositeValueTypesComposedAssertion implements ComposedAssertion {
    @Override
    public MapWithCompositeValueTypesTypeDefinitionAssertion getTypeDefinitionAssertion() {
        return new MapWithCompositeValueTypesTypeDefinitionAssertion(new MapWithCompositeValueTypesJsonSchemaContent());
    }

    @Override
    public MapWithCompositeValueTypesInputAssertion getInputAssertion() {
        return new MapWithCompositeValueTypesInputAssertion(new MapWithCompositeValueTypesJsonContent());
    }
}
