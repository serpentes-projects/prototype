package io.serpentes.input.trees.base;

import io.serpentes.api.input.trees.InputTree;
import io.serpentes.api.input.trees.vertices.InputVertex;
import io.serpentes.api.input.trees.vertices.branch.CollectionInputVertex;
import io.serpentes.api.input.trees.edges.KeyValueInputEdge;
import io.serpentes.api.input.trees.vertices.branch.MapInputVertex;
import io.serpentes.testing.TypeTest;
import io.serpentes.testing.assertions.input.base.composite.map.EmptyMapInputAssertion;
import io.serpentes.testing.assertions.input.base.composite.map.MapWithCompositeValueTypesInputAssertion;
import io.serpentes.testing.assertions.input.base.composite.map.MapWithPrimitiveValueTypesInputAssertion;
import io.serpentes.testing.assertions.input.base.primitive.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Stack;

class TypedTreeComponentRetrieverTest implements TypeTest {


    @Test
    @Override
    public void testEmpty() {

    }

    @Test
    @Override
    public void testNull() {
        final InputTree inputTree = new NullInputAssertion().expectedInputTree();
        final TypedTreeComponentRetriever typedTreeComponentRetriever = new TypedTreeComponentRetriever();
        inputTree.accept(typedTreeComponentRetriever);
        final var nullInputTerminalVertex = typedTreeComponentRetriever.getNullInputTerminalVertex();
        Assertions.assertEquals("null", nullInputTerminalVertex.getValue());
    }

    @Test
    @Override
    public void testBoolean() {
        final InputTree inputTree = new BooleanInputAssertion().expectedInputTree();
        final TypedTreeComponentRetriever typedTreeComponentRetriever = new TypedTreeComponentRetriever();
        inputTree.accept(typedTreeComponentRetriever);
        final var booleanInputTerminalVertex = typedTreeComponentRetriever.getBooleanInputTerminalVertex();
        Assertions.assertEquals("true", booleanInputTerminalVertex.getValue());
    }

    @Test
    @Override
    public void testInteger() {
        final InputTree inputTree = new IntegerInputAssertion().expectedInputTree();
        final TypedTreeComponentRetriever typedTreeComponentRetriever = new TypedTreeComponentRetriever();
        inputTree.accept(typedTreeComponentRetriever);
        final var integerInputTerminalVertex = typedTreeComponentRetriever.getIntegerInputTerminalVertex();
        Assertions.assertEquals("1", integerInputTerminalVertex.getValue());
    }

    @Test
    @Override
    public void testFloatingPoint() {
        final InputTree inputTree = new FloatingPointInputAssertion().expectedInputTree();
        final TypedTreeComponentRetriever typedTreeComponentRetriever = new TypedTreeComponentRetriever();
        inputTree.accept(typedTreeComponentRetriever);
        final var floatingPointInputTerminalVertex = typedTreeComponentRetriever.getFloatingPointInputTerminalVertex();
        Assertions.assertEquals("1.1", floatingPointInputTerminalVertex.getValue());
    }

    @Test
    @Override
    public void testString() {
        final InputTree inputTree = new StringInputAssertion().expectedInputTree();
        final TypedTreeComponentRetriever typedTreeComponentRetriever = new TypedTreeComponentRetriever();
        inputTree.accept(typedTreeComponentRetriever);
        final var stringInputTerminalVertex = typedTreeComponentRetriever.getStringInputTerminalVertex();
        Assertions.assertEquals("string", stringInputTerminalVertex.getValue());
    }

    @Test
    @Override
    public void testEmptyMap() {
        final InputTree inputTree = new EmptyMapInputAssertion().expectedInputTree();
        final TypedTreeComponentRetriever typedTreeComponentRetriever = new TypedTreeComponentRetriever();
        inputTree.accept(typedTreeComponentRetriever);
        final var mapInputVertex = typedTreeComponentRetriever.getMapInputVertex();
        Assertions.assertEquals(0, mapInputVertex.getEdges().size());
    }

    @Test
    @Override
    public void testMapWithPrimitiveValueTypesOnly() {
        final InputTree inputTree = new MapWithPrimitiveValueTypesInputAssertion().expectedInputTree();
        final TypedTreeComponentRetriever typedTreeComponentRetriever = new TypedTreeComponentRetriever();
        inputTree.accept(typedTreeComponentRetriever);
        final var mapInputVertex = typedTreeComponentRetriever.getMapInputVertex();
        Assertions.assertEquals(5, mapInputVertex.getEdges().size());
        Assertions.assertNotNull(mapInputVertex.getEdge("null"));
        Assertions.assertNotNull(mapInputVertex.getEdge("boolean"));
        Assertions.assertNotNull(mapInputVertex.getEdge("integer"));
        Assertions.assertNotNull(mapInputVertex.getEdge("float"));
        Assertions.assertNotNull(mapInputVertex.getEdge("string"));
    }

    @Test
    @Override
    public void testMapWithCompositeValueTypesOnly() {
        final Stack<InputVertex> stack = new Stack<>();
        // Add tree to stack
        final InputTree inputTree = new MapWithCompositeValueTypesInputAssertion().expectedInputTree();
        stack.push(inputTree);

        final TypedTreeComponentRetriever typedTreeComponentRetriever = new TypedTreeComponentRetriever();

        // Visit map "child"
        // Retrieve parent vertex from stack.
        stack.peek().accept(typedTreeComponentRetriever);
        final MapInputVertex parent = typedTreeComponentRetriever.getMapInputVertex();

        // Retrieve input vertex for child
        final KeyValueInputEdge mapEdge = parent.getEdge("map");
        mapEdge.getValue().accept(typedTreeComponentRetriever);
        final MapInputVertex mapChildToStack = typedTreeComponentRetriever.getMapInputVertex();
        stack.push(mapChildToStack);
        stack.peek().accept(typedTreeComponentRetriever);
        final MapInputVertex mapChild = typedTreeComponentRetriever.getMapInputVertex();
        Assertions.assertNotNull(mapChild);
        Assertions.assertEquals(0, mapChild.getEdges().size());

        stack.pop();
        // visit collection "child"
        // Retrieve parent vertex from stack.
        stack.peek().accept(typedTreeComponentRetriever);
        final MapInputVertex parentNext = typedTreeComponentRetriever.getMapInputVertex();

        // Retrieve input vertex for child
        final KeyValueInputEdge collectionEdge = parentNext.getEdge("collection");
        collectionEdge.getValue().accept(typedTreeComponentRetriever);
        final CollectionInputVertex collectionChildToStack = typedTreeComponentRetriever.getCollectionInputVertex();
        stack.push(collectionChildToStack);
        stack.peek().accept(typedTreeComponentRetriever);
        final CollectionInputVertex collectionChild = typedTreeComponentRetriever.getCollectionInputVertex();
        Assertions.assertNotNull(collectionChild);
        Assertions.assertEquals(0, collectionChild.getEdges().size());
    }

    @Test
    @Override
    public void testMapWithAllValueTypes() {

    }

    @Test
    @Override
    public void testEmptyCollection() {

    }

    @Test
    @Override
    public void testNullCollection() {

    }

    @Test
    @Override
    public void testBooleanCollection() {

    }

    @Test
    @Override
    public void testIntegerCollection() {

    }

    @Test
    @Override
    public void testFloatingPointCollection() {

    }

    @Test
    @Override
    public void testStringCollection() {

    }

    @Test
    @Override
    public void testMapCollection() {

    }

    @Test
    @Override
    public void testNestedCollection() {

    }
}