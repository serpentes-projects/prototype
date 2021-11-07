package io.serpentes.input.parsers.json;

import io.serpentes.api.input.parsers.InputParsingException;
import io.serpentes.input.trees.base.DefaultInputTree;

import io.serpentes.testing.TypeTest;
import io.serpentes.testing.assertions.composed.base.EmptyComposedAssertion;
import io.serpentes.testing.assertions.composed.base.composite.collection.EmptyCollectionComposedAssertion;
import io.serpentes.testing.assertions.composed.base.composite.collection.composite.MapCollectionComposedAssertion;
import io.serpentes.testing.assertions.composed.base.composite.collection.composite.NestedCollectionComposedAssertion;
import io.serpentes.testing.assertions.composed.base.composite.collection.primitives.*;
import io.serpentes.testing.assertions.composed.base.composite.map.EmptyMapComposedAssertion;
import io.serpentes.testing.assertions.composed.base.composite.map.MapWithAllValueTypesComposedAssertion;
import io.serpentes.testing.assertions.composed.base.composite.map.MapWithCompositeValueTypesComposedAssertion;
import io.serpentes.testing.assertions.composed.base.composite.map.MapWithPrimitiveValueTypesComposedAssertion;
import io.serpentes.testing.assertions.composed.base.primitives.*;
import io.serpentes.testing.assertions.input.base.InputAssertion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DefaultJSONInputParserTest implements TypeTest {
    @Test
    @Override
    public void testEmpty() throws Exception {
        testInputVertex(new EmptyComposedAssertion().getInputAssertion());
    }

    @Test
    @Override
    public void testNull() throws Exception {
        testInputVertex(new NullComposedAssertion().getInputAssertion());
    }

    @Test
    @Override
    public void testBoolean() throws Exception {
        testInputVertex(new BooleanComposedAssertion().getInputAssertion());
    }

    @Test
    @Override
    public void testInteger() throws Exception {
        testInputVertex(new IntegerComposedAssertion().getInputAssertion());
    }

    @Test
    @Override
    public void testFloatingPoint() throws Exception {
        testInputVertex(new FloatingPointComposedAssertion().getInputAssertion());
    }

    @Test
    @Override
    public void testString() throws Exception {
        testInputVertex(new StringComposedAssertion().getInputAssertion());
    }

    @Test
    @Override
    public void testEmptyMap() throws Exception {
        testInputVertex(new EmptyMapComposedAssertion().getInputAssertion());
    }

    @Test
    @Override
    public void testMapWithPrimitiveValueTypesOnly() throws Exception {
        testInputVertex(new MapWithPrimitiveValueTypesComposedAssertion().getInputAssertion());
    }

    @Test
    @Override
    public void testMapWithCompositeValueTypesOnly() throws Exception {
        testInputVertex(new MapWithCompositeValueTypesComposedAssertion().getInputAssertion());
    }

    @Test
    @Override
    public void testMapWithAllValueTypes() throws Exception {
        testInputVertex(new MapWithAllValueTypesComposedAssertion().getInputAssertion());
    }

    @Test
    @Override
    public void testEmptyCollection() throws Exception {
        testInputVertex(new EmptyCollectionComposedAssertion().getInputAssertion());
    }

    @Test
    @Override
    public void testNullCollection() throws Exception {
        testInputVertex(new NullCollectionComposedAssertion().getInputAssertion());
    }

    @Test
    @Override
    public void testBooleanCollection() throws Exception {
        testInputVertex(new BooleanCollectionComposedAssertion().getInputAssertion());
    }

    @Test
    @Override
    public void testIntegerCollection() throws Exception {
        testInputVertex(new IntegerCollectionComposedAssertion().getInputAssertion());
    }

    @Test
    @Override
    public void testFloatingPointCollection() throws Exception {
        testInputVertex(new FloatingPointCollectionComposedAssertion().getInputAssertion());
    }

    @Test
    @Override
    public void testStringCollection() throws Exception {
        testInputVertex(new StringCollectionComposedAssertion().getInputAssertion());
    }

    @Test
    @Override
    public void testMapCollection() throws Exception {
        testInputVertex(new MapCollectionComposedAssertion().getInputAssertion());
    }

    @Test
    @Override
    public void testNestedCollection() throws Exception {
        testInputVertex(new NestedCollectionComposedAssertion().getInputAssertion());
    }

    private void testInputVertex(InputAssertion<?> inputAssertion) throws InputParsingException {
        DefaultInputTree actualInputTree = new DefaultJSONInputParser().parse(inputAssertion.getContent().beforeParsing());
        Assertions.assertEquals(inputAssertion.expectedInputTree(), actualInputTree);
    }
}