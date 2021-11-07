package io.serpentes.testing.assertions.input.base.composite.map;

import io.serpentes.api.input.trees.InputTree;
import io.serpentes.input.trees.base.DefaultInputTree;
import io.serpentes.input.trees.base.edges.DefaultKeyValueInputEdge;
import io.serpentes.input.trees.base.vertices.branch.DefaultMapInputVertex;
import io.serpentes.testing.assertions.input.base.DefaultInputVertexFactory;
import io.serpentes.testing.assertions.input.content.api.Content;
import io.serpentes.testing.assertions.input.content.api.composite.MapContent;
import io.serpentes.testing.pojos.ClassWithAllValueTypes;

import java.lang.reflect.Type;
import java.util.Collection;

public class MapWithAllValueTypesInputAssertion implements MapInputAssertion<ClassWithAllValueTypes> {
    private final MapContent<ClassWithAllValueTypes> content;

    public MapWithAllValueTypesInputAssertion(MapContent<ClassWithAllValueTypes> content) {
        this.content = content;
    }

    @Override
    public Content<ClassWithAllValueTypes> getContent() {
        return content;
    }

    @Override
    public Type getType() {
        return content.asType();
    }

    @Override
    public ClassWithAllValueTypes defaultExpectedValue() {
        return content.afterDeserializing();
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
    public InputTree expectedInputTree() {
        return new DefaultInputTree(expectedRootInputVertex());
    }

    @Override
    public DefaultMapInputVertex expectedRootInputVertex() {
        final DefaultMapInputVertex defaultMapInputVertex = new DefaultMapInputVertex();
        addKeyValueEdges(new MapWithPrimitiveValueTypesInputAssertion().expectedRootInputVertex(), defaultMapInputVertex);
        addKeyValueEdges(new MapWithCompositeValueTypesInputAssertion().expectedRootInputVertex(), defaultMapInputVertex);
        return defaultMapInputVertex;
    }

    private void addKeyValueEdges(DefaultMapInputVertex source, DefaultMapInputVertex target) {
        source.getEdges().forEach(keyValueInputEdge -> {
            final DefaultKeyValueInputEdge defaultKeyValueInputEdge = DefaultInputVertexFactory.createKeyValueEdge(keyValueInputEdge.getKey(), keyValueInputEdge.getValue());
            target.addEdge(defaultKeyValueInputEdge);
        });
    }

    @Override
    public void assertInputTree(InputTree actualInputTree) {
        //FIXME
    }
}
