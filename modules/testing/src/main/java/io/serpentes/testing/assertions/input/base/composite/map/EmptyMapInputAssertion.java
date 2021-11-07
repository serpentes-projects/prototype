package io.serpentes.testing.assertions.input.base.composite.map;

import io.serpentes.api.input.trees.InputTree;
import io.serpentes.input.trees.base.DefaultInputTree;
import io.serpentes.input.trees.base.vertices.branch.DefaultMapInputVertex;
import io.serpentes.testing.assertions.input.base.DefaultInputVertexFactory;
import io.serpentes.testing.assertions.input.content.api.Content;
import io.serpentes.testing.assertions.input.content.api.composite.MapContent;
import io.serpentes.testing.assertions.input.content.json.composite.map.EmptyMapJsonContent;
import io.serpentes.testing.pojos.ClassWithAllValueTypes;
import org.junit.jupiter.api.Assertions;

import java.lang.reflect.Type;
import java.util.Collection;

public class EmptyMapInputAssertion implements MapInputAssertion<ClassWithAllValueTypes> {
    private final MapContent<ClassWithAllValueTypes> content;

    public EmptyMapInputAssertion() {
        this(new EmptyMapJsonContent());
    }

    public EmptyMapInputAssertion(MapContent<ClassWithAllValueTypes> content) {
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
    public void assertValue(ClassWithAllValueTypes value) {
        Assertions.assertNull(value.getANull());
        Assertions.assertNull(value.getABoolean());
        Assertions.assertNull(value.getInteger());
        Assertions.assertNull(value.getFloatingPoint());
        Assertions.assertNull(value.getString());
        Assertions.assertNull(value.getCollection());
        Assertions.assertNull(value.getMap());
    }

    @Override
    public InputTree expectedInputTree() {
        return new DefaultInputTree(expectedRootInputVertex());
    }

    @Override
    public DefaultMapInputVertex expectedRootInputVertex() {
        return DefaultInputVertexFactory.createMapVertex();
    }

    @Override
    public void assertInputTree(InputTree actualInputTree) {
        //FIXME
    }
}
