package io.serpentes.input.sources.command_line.parameters;

import io.serpentes.api.definition.sources.DefinitionSources;
import io.serpentes.api.definition.trees.vertices.TypeVertex;
import io.serpentes.api.input.trees.vertices.InputVertex;
import io.serpentes.input.sources.command_line.parameters.providers.cdi_extension.CommandLineParametersCDIExtension;
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
import io.serpentes.testing.assertions.definition.type.tree.TypeTreeIterator;
import io.serpentes.testing.assertions.input.tree.TypeBasedInputVertexAssertion;
import io.serpentes.testing.assertions.input.tree.TypeToInputMapper;
import io.serpentes.testing.cdi.utils.CDIUtils;
import io.serpentes.testing.cdi.utils.DefinitionSourceCDIUtils;
import io.serpentes.testing.cdi.utils.InputSourceCDIUtils;
import jakarta.enterprise.inject.se.SeContainer;
import org.jboss.weld.environment.se.Weld;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

class CommandLineParametersStoreClerkTest implements TypeTest {
    @Test
    @Override
    public void testEmpty() {
        //TODO: Implement this.
    }

    @Test
    @Override
    public void testNull() {
        assertValue(new NullComposedAssertion());
    }

    private void assertValue(final ComposedAssertion composedAssertion) {
        final var typeDefinitionAssertion = composedAssertion.getTypeDefinitionAssertion();
        final var weld = new Weld();
        DefinitionSourceCDIUtils.setupDefinitionSources(weld, typeDefinitionAssertion.expectedTypeTree());
        final Set<String> definitionParserPrecedence = new LinkedHashSet<>();
        definitionParserPrecedence.add("application/json");
        DefinitionSourceCDIUtils.setupDefinitionParsers(weld, definitionParserPrecedence);

        final Set<String> inputParserPrecedence = new LinkedHashSet<>();
        inputParserPrecedence.add("application/json");
        InputSourceCDIUtils.setupInputParsers(weld, inputParserPrecedence);

        // TODO: Ensure that users don't have to add this CDIExtension themselves.
        // Should be handled by the SEBootstrap module.
        weld.addExtension(new CommandLineParametersCDIExtension(new String[]{composedAssertion.getInputAssertion().getContent().beforeParsing()}));

        final var container = CDIUtils.createSeContainer(weld);
        final var inputStore = setup(container
        );

        final var definitionSources = container.select(DefinitionSources.class).get();
        final var typeTree = definitionSources.getTypeTree();
        final var typeTreeIterator = new TypeTreeIterator(typeTree);


        final var typeBasedInputVertexAssertion = new TypeBasedInputVertexAssertion();
        final var typeToInputMapper = new TypeToInputMapper();
        final Map<TypeVertex, InputVertex> map = typeToInputMapper.map(typeTree, composedAssertion.getInputAssertion().expectedInputTree());
        while (typeTreeIterator.hasNext()) {
            final var typeVertex = typeTreeIterator.next();
            final var expectedInputVertex = map.get(typeVertex);
            final var actualValues = inputStore.get(typeVertex);
            final var actualInputTree = actualValues.findFirstNonNullValue();
            typeBasedInputVertexAssertion.assertEquals(typeVertex, expectedInputVertex, actualInputTree);
        }
    }

    private InputStore setup(SeContainer container) {
        CDIUtils.stockEmptyInputStore(container);
        final var commandLineArgumentsInputSource = container.select(CommandLineParametersStoreClerk.class).get();
        commandLineArgumentsInputSource.stockStore();
        return container.select(InputStore.class).get();
    }

    @Test
    @Override
    public void testBoolean() {
        assertValue(new BooleanComposedAssertion());
    }

    @Test
    @Override
    public void testInteger() {
        assertValue(new IntegerComposedAssertion());
    }

    @Test
    @Override
    public void testFloatingPoint() {
        assertValue(new FloatingPointComposedAssertion());
    }

    @Test
    @Override
    public void testString() {
        assertValue(new StringComposedAssertion());
    }

    @Test
    @Override
    public void testEmptyMap() {
        assertValue(new EmptyMapComposedAssertion());
    }

    @Test
    @Override
    public void testMapWithPrimitiveValueTypesOnly() {
        assertValue(new MapWithPrimitiveValueTypesComposedAssertion());
    }

    @Test
    @Override
    public void testMapWithCompositeValueTypesOnly() {
        assertValue(new MapWithCompositeValueTypesComposedAssertion());
    }

    @Test
    @Override
    public void testMapWithAllValueTypes() {
        assertValue(new MapWithAllValueTypesComposedAssertion());
    }

    @Test
    @Override
    public void testEmptyCollection() {
        assertValue(new EmptyCollectionComposedAssertion());
    }

    @Test
    @Override
    public void testNullCollection() {
        assertValue(new NullCollectionComposedAssertion());
    }

    @Test
    @Override
    public void testBooleanCollection() {
        assertValue(new BooleanCollectionComposedAssertion());
    }

    @Test
    @Override
    public void testIntegerCollection() {
        assertValue(new IntegerCollectionComposedAssertion());
    }

    @Test
    @Override
    public void testFloatingPointCollection() {
        assertValue(new FloatingPointCollectionComposedAssertion());
    }

    @Test
    @Override
    public void testStringCollection() {
        assertValue(new StringCollectionComposedAssertion());
    }

    @Test
    @Override
    public void testMapCollection() {
        assertValue(new MapCollectionComposedAssertion());
    }

    @Test
    @Override
    public void testNestedCollection() {
        assertValue(new NestedCollectionComposedAssertion());
    }
}