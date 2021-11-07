package io.serpentes.testing.assertions.definition.type.tree;

import io.serpentes.testing.TypeTest;
import io.serpentes.testing.assertions.definition.base.TypeDefinitionAssertion;
import io.serpentes.testing.assertions.definition.base.composite.collection.composite.MapCollectionTypeDefinitionAssertion;
import io.serpentes.testing.assertions.definition.base.composite.collection.composite.NestedCollectionTypeDefinitionAssertion;
import io.serpentes.testing.assertions.definition.base.composite.collection.primitive.*;
import io.serpentes.testing.assertions.definition.base.composite.map.MapWithAllValueTypesTypeDefinitionAssertion;
import io.serpentes.testing.assertions.definition.base.composite.map.MapWithCompositeValueTypesTypeDefinitionAssertion;
import io.serpentes.testing.assertions.definition.base.composite.map.MapWithPrimitiveValueTypesTypeDefinitionAssertion;
import io.serpentes.testing.assertions.definition.base.primitive.*;
import io.serpentes.testing.assertions.definition.content.json_schema.composite.collection.composite.MapCollectionJsonSchemaContent;
import io.serpentes.testing.assertions.definition.content.json_schema.composite.collection.composite.NestedCollectionJsonSchemaContent;
import io.serpentes.testing.assertions.definition.content.json_schema.composite.collection.primitive.*;
import io.serpentes.testing.assertions.definition.content.json_schema.composite.map.MapWithAllValueTypesJsonSchemaContent;
import io.serpentes.testing.assertions.definition.content.json_schema.composite.map.MapWithCompositeValueTypesJsonSchemaContent;
import io.serpentes.testing.assertions.definition.content.json_schema.composite.map.MapWithPrimitiveValueTypesJsonSchemaContent;
import io.serpentes.testing.assertions.definition.content.json_schema.primitive.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

//NOTE: Tests below only verify value-equivalence for a given TypeTree with itself.
class TypeTreeComparatorTest implements TypeTest {
    @Test
	@Override
    public void testEmpty() {

    }

    @Test
	@Override
    public void testNull() {
        setupAndAssert(new NullTypeDefinitionAssertion(new NullJsonSchemaContent()));
    }

    private void setupAndAssert(TypeDefinitionAssertion typeDefinitionAssertion) {
        final var typeTree = typeDefinitionAssertion.expectedTypeTree();
        final var compareTo = typeDefinitionAssertion.expectedTypeTree();

        final var typeTreeComparator = new TypeTreeComparator();

        Assertions.assertTrue(typeTreeComparator.compare(typeTree, compareTo));
    }

    @Test
	@Override
    public void testBoolean() {
        setupAndAssert(new BooleanTypeDefinitionAssertion(new BooleanJsonSchemaContent()));
    }

    @Test
	@Override
    public void testInteger() {
        setupAndAssert(new IntegerTypeDefinitionAssertion(new IntegerJsonSchemaContent()));
    }

    @Test
	@Override
    public void testFloatingPoint() {
        setupAndAssert(new FloatingPointTypeDefinitionAssertion(new FloatingPointJsonSchemaContent()));
    }

    @Test
	@Override
    public void testString() {
        setupAndAssert(new StringTypeDefinitionAssertion(new StringJsonSchemaContent()));
    }

    @Test
	@Override
    public void testEmptyMap() {

    }

    @Test
	@Override
    public void testMapWithPrimitiveValueTypesOnly() {
        setupAndAssert(new MapWithPrimitiveValueTypesTypeDefinitionAssertion(new MapWithPrimitiveValueTypesJsonSchemaContent()));
    }

    @Test
	@Override
    public void testMapWithCompositeValueTypesOnly() {
        setupAndAssert(new MapWithCompositeValueTypesTypeDefinitionAssertion(new MapWithCompositeValueTypesJsonSchemaContent()));
    }

    @Test
	@Override
    public void testMapWithAllValueTypes() {
        setupAndAssert(new MapWithAllValueTypesTypeDefinitionAssertion(new MapWithAllValueTypesJsonSchemaContent()));
    }

    @Test
	@Override
    public void testEmptyCollection() {

    }

    @Test
	@Override
    public void testNullCollection() {
        setupAndAssert(new NullCollectionTypeDefinitionAssertion(new NullCollectionJsonSchemaContent()));
    }

    @Test
	@Override
    public void testBooleanCollection() {
        setupAndAssert(new BooleanCollectionTypeDefinitionAssertion(new BooleanCollectionJsonSchemaContent()));
    }

    @Test
	@Override
    public void testIntegerCollection() {
        setupAndAssert(new IntegerCollectionTypeDefinitionAssertion(new IntegerCollectionJsonSchemaContent()));
    }

    @Test
	@Override
    public void testFloatingPointCollection() {
        setupAndAssert(new FloatingPointCollectionTypeDefinitionAssertion(new FloatingPointCollectionJsonSchemaContent()));
    }

    @Test
	@Override
    public void testStringCollection() {
        setupAndAssert(new StringCollectionTypeDefinitionAssertion(new StringCollectionJsonSchemaContent()));
    }

    @Test
	@Override
    public void testMapCollection() {
        setupAndAssert(new MapCollectionTypeDefinitionAssertion(new MapCollectionJsonSchemaContent()));
    }

    @Test
	@Override
    public void testNestedCollection() {
        setupAndAssert(new NestedCollectionTypeDefinitionAssertion(new NestedCollectionJsonSchemaContent()));
    }
}