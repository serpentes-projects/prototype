package io.serpentes.examples.definition.sources.jackson;

import com.fasterxml.jackson.core.type.TypeReference;
import io.serpentes.testing.TypeTest;
import io.serpentes.testing.assertions.definition.base.TypeDefinitionAssertion;
import io.serpentes.testing.assertions.definition.base.composite.collection.composite.MapCollectionTypeDefinitionAssertion;
import io.serpentes.testing.assertions.definition.base.composite.collection.composite.NestedCollectionTypeDefinitionAssertion;
import io.serpentes.testing.assertions.definition.base.composite.collection.primitive.*;
import io.serpentes.testing.assertions.definition.base.composite.map.MapWithAllValueTypesTypeDefinitionAssertion;
import io.serpentes.testing.assertions.definition.base.composite.map.MapWithCompositeValueTypesTypeDefinitionAssertion;
import io.serpentes.testing.assertions.definition.base.composite.map.MapWithPrimitiveValueTypesTypeDefinitionAssertion;
import io.serpentes.testing.assertions.definition.base.primitive.BooleanTypeDefinitionAssertion;
import io.serpentes.testing.assertions.definition.base.primitive.FloatingPointTypeDefinitionAssertion;
import io.serpentes.testing.assertions.definition.base.primitive.IntegerTypeDefinitionAssertion;
import io.serpentes.testing.assertions.definition.base.primitive.StringTypeDefinitionAssertion;
import io.serpentes.testing.assertions.definition.content.json_schema.composite.collection.composite.MapCollectionJsonSchemaContent;
import io.serpentes.testing.assertions.definition.content.json_schema.composite.collection.composite.NestedCollectionJsonSchemaContent;
import io.serpentes.testing.assertions.definition.content.json_schema.composite.collection.primitive.*;
import io.serpentes.testing.assertions.definition.content.json_schema.composite.map.MapWithAllValueTypesJsonSchemaContent;
import io.serpentes.testing.assertions.definition.content.json_schema.composite.map.MapWithCompositeValueTypesJsonSchemaContent;
import io.serpentes.testing.assertions.definition.content.json_schema.composite.map.MapWithPrimitiveValueTypesJsonSchemaContent;
import io.serpentes.testing.assertions.definition.content.json_schema.primitive.BooleanJsonSchemaContent;
import io.serpentes.testing.assertions.definition.content.json_schema.primitive.FloatingPointJsonSchemaContent;
import io.serpentes.testing.assertions.definition.content.json_schema.primitive.IntegerJsonSchemaContent;
import io.serpentes.testing.assertions.definition.content.json_schema.primitive.StringJsonSchemaContent;
import io.serpentes.testing.assertions.definition.type.tree.TypeTreeComparator;
import io.serpentes.testing.pojos.ClassWithAllValueTypes;
import io.serpentes.testing.pojos.ClassWithCompositeValueTypes;
import io.serpentes.testing.pojos.ClassWithPrimitiveValueTypes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

class PojoParserTest implements TypeTest {

    @Test
    @Override
    public void testEmpty() {

    }

    @Test
    @Override
    public void testNull() {

    }

    @Test
    @Override
    public void testBoolean() {
        assertTypeTree(Boolean.class, new BooleanTypeDefinitionAssertion(new BooleanJsonSchemaContent()));
    }

    private void assertTypeTree(final Type type, final TypeDefinitionAssertion TypeDefinitionAssertion) {
        final var actualTypeTree = new PojoParser(type).buildTree();
        final var expectedTypeTree = TypeDefinitionAssertion.expectedTypeTree();
        Assertions.assertTrue(new TypeTreeComparator().compare(expectedTypeTree, actualTypeTree));
    }

    @Test
    @Override
    public void testInteger() {
        assertTypeTree(Integer.class, new IntegerTypeDefinitionAssertion(new IntegerJsonSchemaContent()));
    }

    @Test
    @Override
    public void testFloatingPoint() {
        assertTypeTree(Double.class, new FloatingPointTypeDefinitionAssertion(new FloatingPointJsonSchemaContent()));
    }

    @Test
    @Override
    public void testString() {
        assertTypeTree(String.class, new StringTypeDefinitionAssertion(new StringJsonSchemaContent()));
    }

    @Test
    @Override
    public void testEmptyMap() {

    }

    @Test
    @Override
    public void testMapWithPrimitiveValueTypesOnly() {
        assertTypeTree(ClassWithPrimitiveValueTypes.class, new MapWithPrimitiveValueTypesTypeDefinitionAssertion(new MapWithPrimitiveValueTypesJsonSchemaContent()));
    }

    @Test
    @Override
    public void testMapWithCompositeValueTypesOnly() {
        assertTypeTree(ClassWithCompositeValueTypes.class, new MapWithCompositeValueTypesTypeDefinitionAssertion(new MapWithCompositeValueTypesJsonSchemaContent()));
    }

    @Test
    @Override
    public void testMapWithAllValueTypes() {
        assertTypeTree(ClassWithAllValueTypes.class, new MapWithAllValueTypesTypeDefinitionAssertion(new MapWithAllValueTypesJsonSchemaContent()));
    }

    @Test
    @Override
    public void testEmptyCollection() {

    }

    @Test
    @Override
    public void testNullCollection() {
        assertTypeTree(ArrayList.class, new NullCollectionTypeDefinitionAssertion(new NullCollectionJsonSchemaContent()));
    }

    @Test
    @Override
    public void testBooleanCollection() {
        final TypeReference<ArrayList<Boolean>> typeReference = new TypeReference<>() {
        };
        assertTypeTree(typeReference.getType(), new BooleanCollectionTypeDefinitionAssertion(new BooleanCollectionJsonSchemaContent()));
    }

    @Test
    @Override
    public void testIntegerCollection() {
        final TypeReference<ArrayList<Integer>> typeReference = new TypeReference<>() {
        };
        assertTypeTree(typeReference.getType(), new IntegerCollectionTypeDefinitionAssertion(new IntegerCollectionJsonSchemaContent()));
    }

    @Test
    @Override
    public void testFloatingPointCollection() {
        final TypeReference<ArrayList<Double>> typeReference = new TypeReference<>() {
        };
        assertTypeTree(typeReference.getType(), new FloatingPointCollectionTypeDefinitionAssertion(new FloatingPointCollectionJsonSchemaContent()));
    }

    @Test
    @Override
    public void testStringCollection() {
        final TypeReference<ArrayList<String>> typeReference = new TypeReference<>() {
        };
        assertTypeTree(typeReference.getType(), new StringCollectionTypeDefinitionAssertion(new StringCollectionJsonSchemaContent()));
    }

    @Test
    @Override
    public void testMapCollection() {
        final TypeReference<Collection<Map>> typeReference = new TypeReference<>() {
        };
        assertTypeTree(typeReference.getType(), new MapCollectionTypeDefinitionAssertion(new MapCollectionJsonSchemaContent()));
    }

    @Test
    @Override
    public void testNestedCollection() {
        final TypeReference<Collection<Collection>> typeReference = new TypeReference<>() {
        };
        assertTypeTree(typeReference.getType(), new NestedCollectionTypeDefinitionAssertion(new NestedCollectionJsonSchemaContent()));
    }

    static class SomeClass {
        public Integer integer;
        public String string;
        public FieldClass fieldClass;
        public Collection<String> strings;
    }

    static class FieldClass {
        public Boolean aBoolean;
        public Double floatingPoint;
        public Map<String, String> map;
    }
}