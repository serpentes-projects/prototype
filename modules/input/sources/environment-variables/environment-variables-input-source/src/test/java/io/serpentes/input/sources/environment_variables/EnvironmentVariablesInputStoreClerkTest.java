package io.serpentes.input.sources.environment_variables;

import io.serpentes.input.stores.base.InputStore;
import io.serpentes.testing.TypeTest;
import io.serpentes.testing.assertions.composed.ComposedAssertion;
import io.serpentes.testing.assertions.composed.base.composite.collection.EmptyCollectionComposedAssertion;
import io.serpentes.testing.assertions.composed.base.composite.collection.composite.MapCollectionComposedAssertion;
import io.serpentes.testing.assertions.composed.base.composite.collection.composite.NestedCollectionComposedAssertion;
import io.serpentes.testing.assertions.composed.base.composite.collection.primitives.*;
import io.serpentes.testing.assertions.composed.base.composite.map.EmptyMapComposedAssertion;
import io.serpentes.testing.assertions.composed.base.composite.map.MapWithAllValueTypesComposedAssertion;
import io.serpentes.testing.assertions.composed.base.composite.map.MapWithCompositeValueTypesComposedAssertion;
import io.serpentes.testing.assertions.composed.base.composite.map.MapWithPrimitiveValueTypesComposedAssertion;
import io.serpentes.testing.assertions.composed.base.primitives.*;
import io.serpentes.testing.assertions.input.content.json.composite.collection.EmptyCollectionJsonContent;
import io.serpentes.testing.assertions.input.content.json.composite.collection.composite.MapCollectionJsonContent;
import io.serpentes.testing.assertions.input.content.json.composite.collection.composite.NestedCollectionJsonContent;
import io.serpentes.testing.assertions.input.content.json.composite.collection.primitive.*;
import io.serpentes.testing.assertions.input.content.json.composite.map.EmptyMapJsonContent;
import io.serpentes.testing.assertions.input.content.json.composite.map.values.MapWithAllValueTypesJsonContent;
import io.serpentes.testing.assertions.input.content.json.composite.map.values.MapWithCompositeValueTypesJsonContent;
import io.serpentes.testing.assertions.input.content.json.composite.map.values.MapWithPrimitiveValueTypesJsonContent;
import io.serpentes.testing.assertions.input.content.json.primitive.*;
import io.serpentes.testing.cdi.utils.CDIUtils;
import io.serpentes.testing.cdi.utils.DefinitionSourceCDIUtils;
import io.serpentes.testing.cdi.utils.InputSourceCDIUtils;
import org.jboss.weld.environment.se.Weld;
import org.junit.jupiter.api.Test;
import org.junitpioneer.jupiter.SetEnvironmentVariable;

import java.util.LinkedHashSet;
import java.util.Set;

class EnvironmentVariablesInputStoreClerkTest implements TypeTest {
    @Test
    @Override
    public void testEmpty() {
        //TODO: Implement.
    }

    @Test
    @Override
    @SetEnvironmentVariable(key = "BOA", value = NullJsonContent.JSON)
    public void testNull() {
        final ComposedAssertion nullComposedAssertion = new NullComposedAssertion();
        assertValue(nullComposedAssertion);
    }

    private static void assertValue(final ComposedAssertion composedAssertion) {
        final var typeDefinitionAssertion = composedAssertion.getTypeDefinitionAssertion();

        final var weld = new Weld();
        DefinitionSourceCDIUtils.setupDefinitionSources(weld, typeDefinitionAssertion.expectedTypeTree());
        final Set<String> definitionParsersPrecedence = new LinkedHashSet<>();
        definitionParsersPrecedence.add("application/json");
        DefinitionSourceCDIUtils.setupDefinitionParsers(weld, definitionParsersPrecedence);

        final Set<String> inputParsersPrecedence = new LinkedHashSet<>();
        inputParsersPrecedence.add("application/json");
        InputSourceCDIUtils.setupInputParsers(weld, inputParsersPrecedence);

        TestUtils.setupEnvironmentVariableInputSource(weld);
        final var container = CDIUtils.createSeContainer(weld);
        CDIUtils.stockEmptyInputStore(container);
        final InputStore InputStore = TestUtils.stockInputValueStoreWithEnvironmentVariableValues(
                container
        );

        TestUtils.assertInputValues(composedAssertion, InputStore, CDIUtils.getPreferredTypeTree(container));
    }

    @Test
    @Override
    @SetEnvironmentVariable(key = "BOA", value = BooleanJsonContent.JSON)
    public void testBoolean() {
        assertValue(new BooleanComposedAssertion());
    }

    @Test
    @Override
    @SetEnvironmentVariable(key = "BOA", value = IntegerJsonContent.JSON)
    public void testInteger() {
        assertValue(new IntegerComposedAssertion());
    }

    @Test
    @Override
    @SetEnvironmentVariable(key = "BOA", value = FloatingPointJsonContent.JSON)
    public void testFloatingPoint() {
        assertValue(new FloatingPointComposedAssertion());
    }

    @Test
    @Override
    @SetEnvironmentVariable(key = "BOA", value = StringJsonContent.JSON)
    public void testString() {
        assertValue(new StringComposedAssertion());
    }

    @Test
    @Override
    @SetEnvironmentVariable(key = "BOA", value = EmptyMapJsonContent.JSON)
    public void testEmptyMap() {
        assertValue(new EmptyMapComposedAssertion());
    }

    @Test
    @Override
    //TODO: Implement specificity overruling.
    @SetEnvironmentVariable(key = "BOA", value = MapWithPrimitiveValueTypesJsonContent.JSON)
    @SetEnvironmentVariable(key = "BOA_NULL", value = NullJsonContent.JSON)
    @SetEnvironmentVariable(key = "BOA_BOOLEAN", value = BooleanJsonContent.JSON)
    @SetEnvironmentVariable(key = "BOA_INTEGER", value = IntegerJsonContent.JSON)
    @SetEnvironmentVariable(key = "BOA_FLOAT", value = FloatingPointJsonContent.JSON)
    @SetEnvironmentVariable(key = "BOA_STRING", value = StringJsonContent.JSON)
    public void testMapWithPrimitiveValueTypesOnly() {
        assertValue(new MapWithPrimitiveValueTypesComposedAssertion());
    }

    @Test
    @Override
    //TODO: Implement specificity overruling.
    @SetEnvironmentVariable(key = "BOA", value = MapWithCompositeValueTypesJsonContent.JSON)
    @SetEnvironmentVariable(key = "BOA_MAP", value = EmptyMapJsonContent.JSON)
    @SetEnvironmentVariable(key = "BOA_COLLECTION", value = EmptyCollectionJsonContent.JSON)
    public void testMapWithCompositeValueTypesOnly() {
        assertValue(new MapWithCompositeValueTypesComposedAssertion());
    }

    @Test
    @Override
    //TODO: Implement specificity overruling.
    @SetEnvironmentVariable(key = "BOA", value = MapWithAllValueTypesJsonContent.JSON)
    @SetEnvironmentVariable(key = "BOA_NULL", value = NullJsonContent.JSON)
    @SetEnvironmentVariable(key = "BOA_BOOLEAN", value = BooleanJsonContent.JSON)
    @SetEnvironmentVariable(key = "BOA_INTEGER", value = IntegerJsonContent.JSON)
    @SetEnvironmentVariable(key = "BOA_FLOAT", value = FloatingPointJsonContent.JSON)
    @SetEnvironmentVariable(key = "BOA_STRING", value = StringJsonContent.JSON)
    @SetEnvironmentVariable(key = "BOA_MAP", value = EmptyMapJsonContent.JSON)
    @SetEnvironmentVariable(key = "BOA_COLLECTION", value = EmptyCollectionJsonContent.JSON)
    public void testMapWithAllValueTypes() {
        assertValue(new MapWithAllValueTypesComposedAssertion());
    }

    @Test
    @Override
    @SetEnvironmentVariable(key = "BOA", value = EmptyCollectionJsonContent.JSON)
    public void testEmptyCollection() {
        assertValue(new EmptyCollectionComposedAssertion());
    }

    @Test
    @Override
    @SetEnvironmentVariable(key = "BOA", value = NullCollectionJsonContent.JSON)
    public void testNullCollection() {
        assertValue(new NullCollectionComposedAssertion());
    }

    @Test
    @Override
    @SetEnvironmentVariable(key = "BOA", value = BooleanCollectionJsonContent.JSON)
    public void testBooleanCollection() {
        assertValue(new BooleanCollectionComposedAssertion());
    }

    @Test
    @Override
    @SetEnvironmentVariable(key = "BOA", value = IntegerCollectionJsonContent.JSON)
    public void testIntegerCollection() {
        assertValue(new IntegerCollectionComposedAssertion());
    }

    @Test
    @Override
    @SetEnvironmentVariable(key = "BOA", value = FloatingPointCollectionJsonContent.JSON)
    public void testFloatingPointCollection() {
        assertValue(new FloatingPointCollectionComposedAssertion());
    }

    @Test
    @Override
    @SetEnvironmentVariable(key = "BOA", value = StringCollectionJsonContent.JSON)
    public void testStringCollection() {
        assertValue(new StringCollectionComposedAssertion());
    }

    @Test
    @Override
    @SetEnvironmentVariable(key = "BOA", value = MapCollectionJsonContent.JSON)
    public void testMapCollection() {
        assertValue(new MapCollectionComposedAssertion());
    }

    @Test
    @Override
    @SetEnvironmentVariable(key = "BOA", value = NestedCollectionJsonContent.JSON)
    public void testNestedCollection() {
        assertValue(new NestedCollectionComposedAssertion());
    }
}