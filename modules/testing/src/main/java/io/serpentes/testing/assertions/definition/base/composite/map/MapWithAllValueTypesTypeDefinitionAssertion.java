package io.serpentes.testing.assertions.definition.base.composite.map;

import io.serpentes.api.definition.trees.TypeTree;
import io.serpentes.api.definition.trees.vertices.TypeVertex;
import io.serpentes.definition.trees.base.DefaultTypeTree;
import io.serpentes.testing.assertions.definition.content.api.composite.MapDefinitionContent;
import io.serpentes.testing.assertions.definition.type.tree.DefaultTypeVertexFactory;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MapWithAllValueTypesTypeDefinitionAssertion implements MapTypeDefinitionAssertion {
    private static final Map<String, String> internalEscapedObject = new HashMap<>();
    static final Map<String, String> escapedObject = Collections.unmodifiableMap(internalEscapedObject);

    static {
        internalEscapedObject.putAll(MapWithPrimitiveValueTypesTypeDefinitionAssertion.escapedObject);
        internalEscapedObject.putAll(MapWithCompositeValueTypesTypeDefinitionAssertion.map);
    }

    private final MapDefinitionContent mapDefinitionContent;

    public MapWithAllValueTypesTypeDefinitionAssertion(final MapDefinitionContent mapDefinitionContent) {
        this.mapDefinitionContent = mapDefinitionContent;
    }

    @Override
    public Type getType() {
        return mapDefinitionContent.asType();
    }


    @Override
    public String getDefinitionContent() {
        return mapDefinitionContent.beforeParsing();
    }

    @Override
    public TypeTree expectedTypeTree() {
        return new DefaultTypeTree(
                DefaultTypeVertexFactory.createMapVertex(
                        DefaultTypeVertexFactory.createKeyValueEdge("null", new DefaultTypeTree(DefaultTypeVertexFactory.createNullTerminalVertex())),
                        DefaultTypeVertexFactory.createKeyValueEdge("boolean", new DefaultTypeTree(DefaultTypeVertexFactory.createBooleanTerminalVertex())),
                        DefaultTypeVertexFactory.createKeyValueEdge("integer", new DefaultTypeTree(DefaultTypeVertexFactory.createIntegerTerminalVertex())),
                        DefaultTypeVertexFactory.createKeyValueEdge("float", new DefaultTypeTree(DefaultTypeVertexFactory.createFloatingPointTerminalVertex())),
                        DefaultTypeVertexFactory.createKeyValueEdge("string", new DefaultTypeTree(DefaultTypeVertexFactory.createStringTerminalVertex())),
                        DefaultTypeVertexFactory.createKeyValueEdge("collection", new DefaultTypeTree(DefaultTypeVertexFactory.createCollectionVertex(
                                DefaultTypeVertexFactory.createNullTerminalVertex()
                        ))),
                        DefaultTypeVertexFactory.createKeyValueEdge("map", new DefaultTypeTree(DefaultTypeVertexFactory.createMapVertex()))
                ));
    }

    @Override
    public TypeVertex expectedRootTypeVertex() {
        return null;
    }

    @Override
    public void assertTypeTree(TypeTree actualTypeTree) {

    }

    @Override
    public Collection<String> primitiveValues() {
        return MapWithPrimitiveValueTypesTypeDefinitionAssertion.escapedObject.values();
    }

    @Override
    public Collection<String> compositeValues() {
        return MapWithCompositeValueTypesTypeDefinitionAssertion.map.values();
    }

    @Override
    public Collection<String> allValues() {
        return escapedObject.values();
    }

    @Override
    public Collection<String> primitiveKeyNames() {
        return MapWithPrimitiveValueTypesTypeDefinitionAssertion.escapedObject.keySet();
    }

    @Override
    public Collection<String> compositeKeyNames() {
        return MapWithCompositeValueTypesTypeDefinitionAssertion.map.keySet();
    }

    @Override
    public Collection<String> allKeyNames() {
        return escapedObject.keySet();
    }
}
