package io.serpentes.testing.assertions.composed.base.composite.map;


import io.serpentes.testing.assertions.composed.ComposedAssertion;
import io.serpentes.testing.assertions.input.content.json.composite.map.values.MapWithPrimitiveValueTypesJsonContent;
import io.serpentes.testing.assertions.input.base.composite.map.MapWithPrimitiveValueTypesInputAssertion;
import io.serpentes.testing.assertions.definition.content.json_schema.composite.map.MapWithPrimitiveValueTypesJsonSchemaContent;
import io.serpentes.testing.assertions.definition.base.composite.map.MapWithPrimitiveValueTypesTypeDefinitionAssertion;

public class MapWithPrimitiveValueTypesComposedAssertion implements ComposedAssertion {
    @Override
    public MapWithPrimitiveValueTypesTypeDefinitionAssertion getTypeDefinitionAssertion() {
        return new MapWithPrimitiveValueTypesTypeDefinitionAssertion(new MapWithPrimitiveValueTypesJsonSchemaContent());
    }

    @Override
    public MapWithPrimitiveValueTypesInputAssertion getInputAssertion() {
        return new MapWithPrimitiveValueTypesInputAssertion(new MapWithPrimitiveValueTypesJsonContent());
    }
}
