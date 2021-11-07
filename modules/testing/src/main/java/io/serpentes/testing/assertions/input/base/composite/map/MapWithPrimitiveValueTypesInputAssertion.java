package io.serpentes.testing.assertions.input.base.composite.map;

import io.serpentes.api.input.trees.InputTree;
import io.serpentes.input.trees.base.DefaultInputTree;
import io.serpentes.input.trees.base.edges.DefaultKeyValueInputEdge;
import io.serpentes.input.trees.base.vertices.branch.DefaultMapInputVertex;
import io.serpentes.testing.assertions.input.base.InputAssertion;
import io.serpentes.testing.assertions.input.base.primitive.*;
import io.serpentes.testing.assertions.input.content.api.Content;
import io.serpentes.testing.assertions.input.content.api.composite.MapContent;
import io.serpentes.testing.assertions.input.content.json.composite.map.values.MapWithPrimitiveValueTypesJsonContent;
import io.serpentes.testing.pojos.ClassWithPrimitiveValueTypes;

import java.lang.reflect.Type;
import java.util.Collection;

public class MapWithPrimitiveValueTypesInputAssertion implements MapInputAssertion<ClassWithPrimitiveValueTypes> {
    private final MapContent<ClassWithPrimitiveValueTypes> content;

    public MapWithPrimitiveValueTypesInputAssertion() {
        this(new MapWithPrimitiveValueTypesJsonContent());
    }

    public MapWithPrimitiveValueTypesInputAssertion(MapContent<ClassWithPrimitiveValueTypes> content) {
        this.content = content;
    }

    @Override
    public Content<ClassWithPrimitiveValueTypes> getContent() {
        return content;
    }

    @Override
    public Type getType() {
        return content.asType();
    }

    @Override
    public ClassWithPrimitiveValueTypes defaultExpectedValue() {
        return content.afterDeserializing();
    }

    @Override
    public InputTree expectedInputTree() {
        return new DefaultInputTree(expectedRootInputVertex());
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
    public DefaultMapInputVertex expectedRootInputVertex() {
        final DefaultMapInputVertex defaultMapInputVertex = new DefaultMapInputVertex();
        addInputKeyValueEdge("null", new NullInputAssertion(), defaultMapInputVertex);
        addInputKeyValueEdge("boolean", new BooleanInputAssertion(), defaultMapInputVertex);
        addInputKeyValueEdge("integer", new IntegerInputAssertion(), defaultMapInputVertex);
        addInputKeyValueEdge("float", new FloatingPointInputAssertion(), defaultMapInputVertex);
        addInputKeyValueEdge("string", new StringInputAssertion(), defaultMapInputVertex);
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
