package io.serpentes.testing.assertions.input.base.composite.map;

import io.serpentes.api.input.trees.InputTree;
import io.serpentes.input.trees.base.DefaultInputTree;
import io.serpentes.input.trees.base.edges.DefaultKeyValueInputEdge;
import io.serpentes.input.trees.base.vertices.branch.DefaultMapInputVertex;
import io.serpentes.testing.assertions.input.base.InputAssertion;
import io.serpentes.testing.assertions.input.base.composite.collection.EmptyCollectionInputAssertion;
import io.serpentes.testing.assertions.input.content.api.Content;
import io.serpentes.testing.assertions.input.content.api.composite.MapContent;
import io.serpentes.testing.assertions.input.content.json.composite.map.values.MapWithCompositeValueTypesJsonContent;
import io.serpentes.testing.pojos.ClassWithCompositeValueTypes;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;

public class MapWithCompositeValueTypesInputAssertion implements MapInputAssertion<ClassWithCompositeValueTypes> {
    static final Map<String, String> map;

    static {
        map = Map.of("collection", "[]", "map", "{}");
    }

    private final MapContent<ClassWithCompositeValueTypes> content;

    public MapWithCompositeValueTypesInputAssertion() {
        this(new MapWithCompositeValueTypesJsonContent());
    }

    public MapWithCompositeValueTypesInputAssertion(MapContent<ClassWithCompositeValueTypes> content) {
        this.content = content;
    }

    @Override
    public Content<ClassWithCompositeValueTypes> getContent() {
        return content;
    }

    @Override
    public Type getType() {
        return content.asType();
    }

    @Override
    public ClassWithCompositeValueTypes defaultExpectedValue() {
        return content.afterDeserializing();
    }

    @Override
    public Collection<String> primitiveKeyNames() {
        return content.primitiveKeyNames();
    }

    @Override
    public Collection<String> compositeKeyNames() {
        return content.compositeKeyNames();
    }

    @Override
    public Collection<String> allKeyNames() {
        return content.allKeyNames();
    }

    @Override
    public Collection<String> primitiveValues() {
        return content.primitivesBeforeParsing();
    }

    @Override
    public Collection<String> compositeValues() {
        return content.compositesBeforeParsing();
    }

    @Override
    public Collection<String> allValues() {
        return content.allBeforeParsing();
    }

    @Override
    public InputTree expectedInputTree() {
        return new DefaultInputTree(expectedRootInputVertex());
    }

    @Override
    public DefaultMapInputVertex expectedRootInputVertex() {
        final var defaultMapInputVertex = new DefaultMapInputVertex();
        addInputKeyValueEdge("collection", new EmptyCollectionInputAssertion(), defaultMapInputVertex);
        addInputKeyValueEdge("map", new EmptyMapInputAssertion(), defaultMapInputVertex);
        return defaultMapInputVertex;
    }

    private void addInputKeyValueEdge(String keyName, InputAssertion<?> inputAssertion, DefaultMapInputVertex defaultMapInputVertex) {
        final var defaultKeyValueInputEdge = new DefaultKeyValueInputEdge(keyName, inputAssertion.expectedInputTree());
        defaultMapInputVertex.addEdge(defaultKeyValueInputEdge);
    }

    @Override
    public void assertInputTree(InputTree actualInputTree) {
        //FIXME
    }
}
