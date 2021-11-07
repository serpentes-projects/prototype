package io.serpentes.definition.parsers.json;

import io.serpentes.api.definition.parsers.DefinitionParsingException;
import io.serpentes.testing.TypeTest;
import io.serpentes.testing.assertions.definition.content.json_schema.EmptyJsonSchemaContent;
import io.serpentes.testing.assertions.definition.content.json_schema.composite.collection.EmptyCollectionJsonSchemaContent;
import io.serpentes.testing.assertions.definition.content.json_schema.composite.collection.composite.MapCollectionJsonSchemaContent;
import io.serpentes.testing.assertions.definition.content.json_schema.composite.collection.composite.NestedCollectionJsonSchemaContent;
import io.serpentes.testing.assertions.definition.content.json_schema.composite.collection.primitive.*;
import io.serpentes.testing.assertions.definition.content.json_schema.composite.map.EmptyMapJsonSchemaContent;
import io.serpentes.testing.assertions.definition.content.json_schema.composite.map.MapWithAllValueTypesJsonSchemaContent;
import io.serpentes.testing.assertions.definition.content.json_schema.composite.map.MapWithCompositeValueTypesJsonSchemaContent;
import io.serpentes.testing.assertions.definition.content.json_schema.composite.map.MapWithPrimitiveValueTypesJsonSchemaContent;
import io.serpentes.testing.assertions.definition.content.json_schema.primitive.*;
import io.serpentes.testing.assertions.definition.file_system.FileSystemEmptyTypeDefinitionAssertion;
import io.serpentes.testing.assertions.definition.file_system.FileSystemTypeDefinitionAssertion;
import io.serpentes.testing.assertions.definition.file_system.composite.collection.FileSystemEmptyCollectionTypeDefinitionAssertion;
import io.serpentes.testing.assertions.definition.file_system.composite.collection.composite.FileSystemMapCollectionTypeDefinitionAssertion;
import io.serpentes.testing.assertions.definition.file_system.composite.collection.composite.FileSystemNestedCollectionTypeDefinitionAssertion;
import io.serpentes.testing.assertions.definition.file_system.composite.collection.primitive.*;
import io.serpentes.testing.assertions.definition.file_system.composite.map.FileSystemEmptyMapTypeDefinitionAssertion;
import io.serpentes.testing.assertions.definition.file_system.composite.map.FileSystemMapWithAllValueTypesTypeDefinitionAssertion;
import io.serpentes.testing.assertions.definition.file_system.composite.map.FileSystemMapWithCompositeValueTypesTypeDefinitionAssertion;
import io.serpentes.testing.assertions.definition.file_system.composite.map.FileSystemMapWithPrimitiveValueTypesTypeDefinitionAssertion;
import io.serpentes.testing.assertions.definition.file_system.primitive.*;
import io.serpentes.testing.assertions.definition.type.tree.TypeTreeAssertion;
import io.serpentes.testing.assertions.definition.type.tree.TypeTreeVertexCounter;
import org.junit.jupiter.api.Test;

class DefaultJSONDefinitionParserTest implements TypeTest {

    @Test
    @Override
    public void testEmpty() throws Exception {
        final var typeTreeAssertion = new TypeTreeAssertion().expectedAmountOfUnknownVertices(1);
        assertTree(new FileSystemEmptyTypeDefinitionAssertion(new EmptyJsonSchemaContent()), typeTreeAssertion);
    }

    private void assertTree(final FileSystemTypeDefinitionAssertion<?> typeDefinitionAssertion, final TypeTreeAssertion typeTreeAssertion) throws DefinitionParsingException {
        final var defaultJSONSchemaParser = new DefaultJSONDefinitionParser();
        final var typeTree = defaultJSONSchemaParser.parse(typeDefinitionAssertion.getDefinitionContent());
        final var typeTreeVertexCounter = new TypeTreeVertexCounter(typeTreeAssertion);
        typeTree.accept(typeTreeVertexCounter);
        typeTreeAssertion.assertTree();
    }

    @Test
    @Override
    public void testNull() throws Exception {
        final var typeTreeAssertion = new TypeTreeAssertion().expectedAmountOfNullTypeTerminalVertices(1);
        assertTree(new FileSystemNullTypeDefinitionAssertion(new NullJsonSchemaContent()), typeTreeAssertion);
    }

    @Test
    @Override
    public void testBoolean() throws Exception {
        final var typeTreeAssertion = new TypeTreeAssertion().expectedAmountOfBooleanTypeTerminalVertices(1);
        assertTree(new FileSystemBooleanTypeDefinitionAssertion(new BooleanJsonSchemaContent()), typeTreeAssertion);
    }

    @Test
    @Override
    public void testInteger() throws Exception {
        final var typeTreeAssertion = new TypeTreeAssertion().expectedAmountOfIntegerTypeTerminalVertices(1);
        assertTree(new FileSystemIntegerTypeDefinitionAssertion(new IntegerJsonSchemaContent()), typeTreeAssertion);
    }

    @Test
    @Override
    public void testFloatingPoint() throws Exception {
        final var typeTreeAssertion = new TypeTreeAssertion().expectedAmountOfFloatingPointTypeTerminalVertices(1);
        assertTree(new FileSystemFloatingPointTypeDefinitionAssertion(new FloatingPointJsonSchemaContent()), typeTreeAssertion);
    }

    @Test
    @Override
    public void testString() throws Exception {
        final var typeTreeAssertion = new TypeTreeAssertion().expectedAmountOfStringTypeTerminalVertices(1);
        assertTree(new FileSystemStringTypeDefinitionAssertion(new StringJsonSchemaContent()), typeTreeAssertion);
    }

    @Test
    @Override
    public void testEmptyMap() throws Exception {
        final var typeTreeAssertion = new TypeTreeAssertion()
                .expectedAmountOfMapTypeVertices(1);
        assertTree(new FileSystemEmptyMapTypeDefinitionAssertion(new EmptyMapJsonSchemaContent()), typeTreeAssertion);
    }

    @Test
    @Override
    public void testMapWithPrimitiveValueTypesOnly() throws Exception {
        final var typeTreeAssertion = new TypeTreeAssertion()
                .expectedAmountOfMapTypeVertices(1)
                .expectedAmountOfKeyValueEdges(5)
                .expectedAmountOfNullTypeTerminalVertices(1)
                .expectedAmountOfBooleanTypeTerminalVertices(1)
                .expectedAmountOfIntegerTypeTerminalVertices(1)
                .expectedAmountOfFloatingPointTypeTerminalVertices(1)
                .expectedAmountOfStringTypeTerminalVertices(1);
        assertTree(new FileSystemMapWithPrimitiveValueTypesTypeDefinitionAssertion(new MapWithPrimitiveValueTypesJsonSchemaContent()), typeTreeAssertion);
    }

    @Test
    @Override
    public void testMapWithCompositeValueTypesOnly() throws Exception {
        final var typeTreeAssertion = new TypeTreeAssertion()
                .expectedAmountOfMapTypeVertices(2)
                .expectedAmountOfKeyValueEdges(2)
                .expectedAmountOfCollectionTypeVertices(1)
                .expectedAmountOfAllowedTypes(1)
                .expectedAmountOfCollectionItemEdges(0);
        assertTree(new FileSystemMapWithCompositeValueTypesTypeDefinitionAssertion(new MapWithCompositeValueTypesJsonSchemaContent()), typeTreeAssertion);
    }

    @Test
    @Override
    public void testMapWithAllValueTypes() throws Exception {
        final var typeTreeAssertion = new TypeTreeAssertion()
                .expectedAmountOfMapTypeVertices(2)
                .expectedAmountOfKeyValueEdges(7)
                .expectedAmountOfCollectionTypeVertices(1)
                .expectedAmountOfAllowedTypes(1)
                .expectedAmountOfCollectionItemEdges(0)
                .expectedAmountOfNullTypeTerminalVertices(1)
                .expectedAmountOfBooleanTypeTerminalVertices(1)
                .expectedAmountOfIntegerTypeTerminalVertices(1)
                .expectedAmountOfFloatingPointTypeTerminalVertices(1)
                .expectedAmountOfStringTypeTerminalVertices(1);
        assertTree(new FileSystemMapWithAllValueTypesTypeDefinitionAssertion(new MapWithAllValueTypesJsonSchemaContent()), typeTreeAssertion);
    }

    @Test
    @Override
    public void testEmptyCollection() throws Exception {
        //TODO: Consider replacing this with a malformed definition. Currently it's using array + items = type:null.
        // Removing items or type would be the equivalent of testing an empty collection.
        final var typeTreeAssertion = new TypeTreeAssertion()
                .expectedAmountOfCollectionTypeVertices(1);
        assertTree(new FileSystemEmptyCollectionTypeDefinitionAssertion(new EmptyCollectionJsonSchemaContent()), typeTreeAssertion);
    }

    @Test
    @Override
    public void testNullCollection() throws Exception {
        final var typeTreeAssertion = new TypeTreeAssertion()
                .expectedAmountOfCollectionTypeVertices(1)
                .expectedAmountOfAllowedTypes(1)
                .expectedAmountOfCollectionItemEdges(0);
        assertTree(new FileSystemNullCollectionTypeDefinitionAssertion(new NullCollectionJsonSchemaContent()), typeTreeAssertion);
    }

    @Test
    @Override
    public void testBooleanCollection() throws Exception {
        final var typeTreeAssertion = new TypeTreeAssertion()
                .expectedAmountOfCollectionTypeVertices(1)
                .expectedAmountOfAllowedTypes(1)
                .expectedAmountOfCollectionItemEdges(0);
        assertTree(new FileSystemBooleanCollectionTypeDefinitionAssertion(new BooleanCollectionJsonSchemaContent()), typeTreeAssertion);
    }

    @Test
    @Override
    public void testIntegerCollection() throws Exception {
        final var typeTreeAssertion = new TypeTreeAssertion()
                .expectedAmountOfCollectionTypeVertices(1)
                .expectedAmountOfAllowedTypes(1)
                .expectedAmountOfCollectionItemEdges(0);
        assertTree(new FileSystemIntegerCollectionTypeDefinitionAssertion(new IntegerCollectionJsonSchemaContent()), typeTreeAssertion);
    }

    @Test
    @Override
    public void testFloatingPointCollection() throws Exception {
        final var typeTreeAssertion = new TypeTreeAssertion()
                .expectedAmountOfCollectionTypeVertices(1)
                .expectedAmountOfAllowedTypes(1)
                .expectedAmountOfCollectionItemEdges(0);
        assertTree(new FileSystemFloatingPointCollectionTypeDefinitionAssertion(new FloatingPointCollectionJsonSchemaContent()), typeTreeAssertion);
    }

    @Test
    @Override
    public void testStringCollection() throws Exception {
        final var typeTreeAssertion = new TypeTreeAssertion()
                .expectedAmountOfCollectionTypeVertices(1)
                .expectedAmountOfAllowedTypes(1)
                .expectedAmountOfCollectionItemEdges(0);
        assertTree(new FileSystemStringCollectionTypeDefinitionAssertion(new StringCollectionJsonSchemaContent()), typeTreeAssertion);
    }

    @Test
    @Override
    public void testMapCollection() throws Exception {
        final var typeTreeAssertion = new TypeTreeAssertion()
                .expectedAmountOfCollectionTypeVertices(1)
                .expectedAmountOfAllowedTypes(1)
                .expectedAmountOfCollectionItemEdges(0);
        assertTree(new FileSystemMapCollectionTypeDefinitionAssertion(new MapCollectionJsonSchemaContent()), typeTreeAssertion);
    }

    @Test
    @Override
    public void testNestedCollection() throws Exception {
        final var typeTreeAssertion = new TypeTreeAssertion()
                .expectedAmountOfCollectionTypeVertices(1)
                .expectedAmountOfAllowedTypes(1) // TODO: Visit allowed types as well? This should be 2.
                .expectedAmountOfCollectionItemEdges(0);
        assertTree(new FileSystemNestedCollectionTypeDefinitionAssertion(new NestedCollectionJsonSchemaContent()), typeTreeAssertion);
    }
}