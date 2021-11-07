package io.serpentes.definition.trees.base;

import io.serpentes.testing.TypeTest;
import io.serpentes.testing.assertions.definition.base.TypeDefinitionAssertion;
import io.serpentes.testing.assertions.definition.base.composite.collection.composite.MapCollectionTypeDefinitionAssertion;
import io.serpentes.testing.assertions.definition.base.composite.collection.composite.NestedCollectionTypeDefinitionAssertion;
import io.serpentes.testing.assertions.definition.base.composite.collection.primitive.*;
import io.serpentes.testing.assertions.definition.base.composite.map.EmptyMapTypeDefinitionAssertion;
import io.serpentes.testing.assertions.definition.base.composite.map.MapWithAllValueTypesTypeDefinitionAssertion;
import io.serpentes.testing.assertions.definition.base.composite.map.MapWithCompositeValueTypesTypeDefinitionAssertion;
import io.serpentes.testing.assertions.definition.base.composite.map.MapWithPrimitiveValueTypesTypeDefinitionAssertion;
import io.serpentes.testing.assertions.definition.base.primitive.*;
import io.serpentes.testing.assertions.definition.type.tree.TypeTreeComparator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TypeVertexClonerTest implements TypeTest {

    @Test
	@Override
    public void testEmpty() {
        //TODO: Implement.
    }

    @Test
	@Override
    public void testNull() {
        cloneAndAssert(new NullTypeDefinitionAssertion(null));
    }

    private void cloneAndAssert(TypeDefinitionAssertion typeDefinitionAssertion) {
        final var typeVertexCloner = new TypeVertexCloner();
        final var expectedTypeTree = typeDefinitionAssertion.expectedTypeTree();
        final var clone = typeVertexCloner.clone(expectedTypeTree);
        Assertions.assertNotSame(expectedTypeTree, clone);
        final var typeTreeComparator = new TypeTreeComparator();
        Assertions.assertTrue(typeTreeComparator.compare(expectedTypeTree, clone));
        //FIXME: This does not test that the root vertex or any of the children are cloned objects.
    }

    @Test
	@Override
    public void testBoolean() {
        cloneAndAssert(new BooleanTypeDefinitionAssertion(null));
    }

    @Test
	@Override
    public void testInteger() {
        cloneAndAssert(new IntegerTypeDefinitionAssertion(null));
    }

    @Test
	@Override
    public void testFloatingPoint() {
        cloneAndAssert(new FloatingPointTypeDefinitionAssertion(null));
    }

    @Test
	@Override
    public void testString() {
        cloneAndAssert(new StringTypeDefinitionAssertion(null));
    }

    @Test
	@Override
    public void testEmptyMap() {
        cloneAndAssert(new EmptyMapTypeDefinitionAssertion(null));
    }

    @Test
	@Override
    public void testMapWithPrimitiveValueTypesOnly() {
        cloneAndAssert(new MapWithPrimitiveValueTypesTypeDefinitionAssertion(null));
    }

    @Test
	@Override
    public void testMapWithCompositeValueTypesOnly() {
        cloneAndAssert(new MapWithCompositeValueTypesTypeDefinitionAssertion(null));
    }

    @Test
	@Override
    public void testMapWithAllValueTypes() {
        cloneAndAssert(new MapWithAllValueTypesTypeDefinitionAssertion(null));
    }

    @Test
	@Override
    public void testEmptyCollection() {

    }

    @Test
	@Override
    public void testNullCollection() {
        cloneAndAssert(new NullCollectionTypeDefinitionAssertion(null));
    }

    @Test
	@Override
    public void testBooleanCollection() {
        cloneAndAssert(new BooleanCollectionTypeDefinitionAssertion(null));
    }

    @Test
	@Override
    public void testIntegerCollection() {
        cloneAndAssert(new IntegerCollectionTypeDefinitionAssertion(null));
    }

    @Test
	@Override
    public void testFloatingPointCollection() {
        cloneAndAssert(new FloatingPointCollectionTypeDefinitionAssertion(null));
    }

    @Test
	@Override
    public void testStringCollection() {
        cloneAndAssert(new StringCollectionTypeDefinitionAssertion(null));
    }

    @Test
	@Override
    public void testMapCollection() {
        cloneAndAssert(new MapCollectionTypeDefinitionAssertion(null));
    }

    @Test
	@Override
    public void testNestedCollection() {
        cloneAndAssert(new NestedCollectionTypeDefinitionAssertion(null));
    }
}