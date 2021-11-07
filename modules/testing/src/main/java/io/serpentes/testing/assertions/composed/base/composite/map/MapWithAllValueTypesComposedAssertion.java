package io.serpentes.testing.assertions.composed.base.composite.map;

import io.serpentes.testing.assertions.composed.ComposedAssertion;
import io.serpentes.testing.assertions.input.content.json.composite.map.values.MapWithAllValueTypesJsonContent;
import io.serpentes.testing.assertions.input.base.composite.map.MapWithAllValueTypesInputAssertion;
import io.serpentes.testing.assertions.definition.content.json_schema.composite.map.MapWithAllValueTypesJsonSchemaContent;
import io.serpentes.testing.assertions.definition.base.composite.map.MapWithAllValueTypesTypeDefinitionAssertion;

public class MapWithAllValueTypesComposedAssertion implements ComposedAssertion {
    @Override
    public MapWithAllValueTypesTypeDefinitionAssertion getTypeDefinitionAssertion() {
        return new MapWithAllValueTypesTypeDefinitionAssertion(new MapWithAllValueTypesJsonSchemaContent());
    }

    @Override
    public MapWithAllValueTypesInputAssertion getInputAssertion() {
        return new MapWithAllValueTypesInputAssertion(new MapWithAllValueTypesJsonContent());
    }
}
