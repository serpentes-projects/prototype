package io.serpentes.testing.assertions.definition.base.composite.map;

import io.serpentes.api.definition.trees.TypeTree;
import io.serpentes.api.definition.trees.vertices.TypeVertex;
import io.serpentes.definition.trees.base.DefaultTypeTree;
import io.serpentes.testing.assertions.definition.content.api.composite.MapDefinitionContent;
import io.serpentes.testing.assertions.definition.type.tree.DefaultTypeVertexFactory;

import java.lang.reflect.Type;
import java.util.*;

public class MapWithCompositeValueTypesTypeDefinitionAssertion implements MapTypeDefinitionAssertion {
    static final Map<String, String> map;

    static {
        map = Map.of("collection", "[]", "map", "{}");
    }

    private final MapDefinitionContent mapDefinitionContent;

    public MapWithCompositeValueTypesTypeDefinitionAssertion(final MapDefinitionContent mapDefinitionContent) {
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
                        DefaultTypeVertexFactory.createKeyValueEdge("collection", new DefaultTypeTree(DefaultTypeVertexFactory.createCollectionVertex())),
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
    public Collection<String> primitiveKeyNames() {
        return new ArrayList<>();
    }

    @Override
    //TODO: Move to input assertion?
    public Collection<String> compositeKeyNames() {
        return map.keySet();
    }

    @Override
    public Collection<String> allKeyNames() {
        return map.keySet();
    }

    @Override
    public Collection<String> primitiveValues() {
        return new ArrayList<>();
    }

    @Override
    public Collection<String> compositeValues() {
        return map.values();
    }

    @Override
    public Collection<String> allValues() {
        return map.values();
    }
}
