package io.serpentes.configuration.factories.jackson;

import com.fasterxml.jackson.core.type.TypeReference;
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
import io.serpentes.testing.assertions.input.base.InputAssertion;
import io.serpentes.testing.cdi.extentions.input.sources.input_tree.ExtensionInputSource;
import io.serpentes.testing.cdi.extentions.input.sources.input_tree.InputTreeInputSource;
import io.serpentes.testing.cdi.utils.CDIUtils;
import io.serpentes.testing.cdi.utils.DefinitionSourceCDIUtils;
import io.serpentes.testing.cdi.utils.InputSourceCDIUtils;
import io.serpentes.testing.pojos.ClassWithAllValueTypes;
import io.serpentes.testing.pojos.ClassWithCompositeValueTypes;
import io.serpentes.testing.pojos.ClassWithPrimitiveValueTypes;
import jakarta.enterprise.util.AnnotationLiteral;
import org.jboss.weld.environment.se.Weld;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

class JacksonConfigurationFactoryTest implements TypeTest {
    final Weld weld = new Weld();

    @Test
    @Override
    public void testEmpty() {
        //TODO: Implement.
    }

    @Test
    @Override
    public void testNull() throws IOException {
        final var composedAssertion = new NullComposedAssertion();
        final var jacksonConfigurationFactory = setup(composedAssertion);
        composedAssertion.getInputAssertion().assertValue(jacksonConfigurationFactory.create(Object.class));
    }

    private <T> JacksonConfigurationFactory setup(final ComposedAssertion composedAssertion) {
        final var typeDefinitionAssertion = composedAssertion.getTypeDefinitionAssertion();
        final InputAssertion<?> inputAssertion = composedAssertion.getInputAssertion();

        final var expectedTypeTree = typeDefinitionAssertion.expectedTypeTree();
        DefinitionSourceCDIUtils.setupDefinitionSources(weld, expectedTypeTree);
        final Set<String> definitionParsersPrecedence = new LinkedHashSet<>();
        definitionParsersPrecedence.add("application/json");
        DefinitionSourceCDIUtils.setupDefinitionParsers(weld, definitionParsersPrecedence);
        InputSourceCDIUtils.setupInputTreeInputSource(weld, expectedTypeTree, inputAssertion.expectedInputTree());
        final Set<String> inputParsersPrecedence = new LinkedHashSet<>();
        inputParsersPrecedence.add("application/json");
        InputSourceCDIUtils.setupInputParsers(weld, inputParsersPrecedence);
        final var weldContainer = CDIUtils.createSeContainer(weld);
        CDIUtils.stockEmptyInputStore(weldContainer);
        final var inputTreeInputSource = weldContainer.select(InputTreeInputSource.class, new AnnotationLiteral<ExtensionInputSource>() {
        }).get();
        inputTreeInputSource.addValuesToInputStore();

        return weldContainer.select(JacksonConfigurationFactory.class).get();
    }

    @Test
    @Override
    public void testBoolean() throws IOException {
        final var composedAssertion = new BooleanComposedAssertion();
        final var jacksonConfigurationFactory = setup(composedAssertion);
        composedAssertion.getInputAssertion().assertValue(jacksonConfigurationFactory.create(Boolean.class));
    }

    @Test
    @Override
    public void testInteger() throws IOException {
        final var composedAssertion = new IntegerComposedAssertion();
        final var jacksonConfigurationFactory = setup(composedAssertion);
        composedAssertion.getInputAssertion().assertValue(jacksonConfigurationFactory.create(Integer.class));
    }

    @Test
    @Override
    public void testFloatingPoint() throws IOException {
        final var composedAssertion = new FloatingPointComposedAssertion();
        final var jacksonConfigurationFactory = setup(composedAssertion);
        composedAssertion.getInputAssertion().assertValue(jacksonConfigurationFactory.create(Double.class));
    }

    @Test
    @Override
    public void testString() throws IOException {
        final var composedAssertion = new StringComposedAssertion();
        final var jacksonConfigurationFactory = setup(composedAssertion);
        composedAssertion.getInputAssertion().assertValue(jacksonConfigurationFactory.create(String.class));
    }

    @Test
    @Override
    public void testEmptyMap() throws IOException {
        final var emptyMapComposedAssertion = new EmptyMapComposedAssertion();
        final var jacksonConfigurationFactory = setup(emptyMapComposedAssertion);
        emptyMapComposedAssertion.getInputAssertion().assertValue(jacksonConfigurationFactory.create(ClassWithAllValueTypes.class));
    }

    @Test
    @Override
    public void testMapWithPrimitiveValueTypesOnly() throws IOException {
        final var mapWithPrimitiveValueTypesComposedAssertion = new MapWithPrimitiveValueTypesComposedAssertion();
        final var jacksonConfigurationFactory = setup(mapWithPrimitiveValueTypesComposedAssertion);
        mapWithPrimitiveValueTypesComposedAssertion.getInputAssertion().assertValue(jacksonConfigurationFactory.create(ClassWithPrimitiveValueTypes.class));
    }

    @Test
    @Override
    public void testMapWithCompositeValueTypesOnly() throws IOException {
        final var fileSystemObjectCompositePropertyTypesAssertion = new MapWithCompositeValueTypesComposedAssertion();
        final var jacksonConfigurationFactory = setup(fileSystemObjectCompositePropertyTypesAssertion);
        fileSystemObjectCompositePropertyTypesAssertion.getInputAssertion().assertValue(jacksonConfigurationFactory.create(ClassWithCompositeValueTypes.class));
    }

    @Test
    @Override
    public void testMapWithAllValueTypes() throws IOException {
        final var composedAssertion = new MapWithAllValueTypesComposedAssertion();
        final var jacksonConfigurationFactory = setup(composedAssertion);
        composedAssertion.getInputAssertion().assertValue(jacksonConfigurationFactory.create(ClassWithAllValueTypes.class));
    }

    @Test
    @Override
    public void testEmptyCollection() throws IOException {
        final var composedAssertion = new EmptyCollectionComposedAssertion();
        final var jacksonConfigurationFactory = setup(composedAssertion);
        composedAssertion.getInputAssertion().assertValue(jacksonConfigurationFactory.create(new TypeReference<>() {
        }));
    }

    @Test
    @Override
    public void testNullCollection() throws IOException {
        final var composedAssertion = new NullCollectionComposedAssertion();
        final var jacksonConfigurationFactory = setup(composedAssertion);
        composedAssertion.getInputAssertion().assertValue(jacksonConfigurationFactory.create(new TypeReference<>() {
        }));
    }

    @Test
    @Override
    public void testBooleanCollection() throws Exception {
        final var composedAssertion = new BooleanCollectionComposedAssertion();
        final var jacksonConfigurationFactory = setup(composedAssertion);
        composedAssertion.getInputAssertion().assertValue(jacksonConfigurationFactory.create(new TypeReference<>() {
        }));
    }

    @Test
    @Override
    public void testIntegerCollection() throws Exception {
        final var composedAssertion = new IntegerCollectionComposedAssertion();
        final var jacksonConfigurationFactory = setup(composedAssertion);
        composedAssertion.getInputAssertion().assertValue(jacksonConfigurationFactory.create(new TypeReference<>() {
        }));
    }

    @Test
    @Override
    public void testFloatingPointCollection() throws Exception {
        final var composedAssertion = new FloatingPointCollectionComposedAssertion();
        final var jacksonConfigurationFactory = setup(composedAssertion);
        composedAssertion.getInputAssertion().assertValue(jacksonConfigurationFactory.create(new TypeReference<>() {
        }));
    }

    @Test
    @Override
    public void testStringCollection() throws Exception {
        final var composedAssertion = new StringCollectionComposedAssertion();
        final var jacksonConfigurationFactory = setup(composedAssertion);
        composedAssertion.getInputAssertion().assertValue(jacksonConfigurationFactory.create(new TypeReference<>() {
        }));
    }

    @Test
    @Override
    public void testMapCollection() throws IOException {
        final var composedAssertion = new MapCollectionComposedAssertion();
        final var jacksonConfigurationFactory = setup(composedAssertion);
        composedAssertion.getInputAssertion().assertValue(jacksonConfigurationFactory.create(new TypeReference<>() {
        }));
    }

    @Test
    @Override
    public void testNestedCollection() throws IOException {
        final var composedAssertion = new NestedCollectionComposedAssertion();
        final var jacksonConfigurationFactory = setup(composedAssertion);
        composedAssertion.getInputAssertion().assertValue(jacksonConfigurationFactory.create(new TypeReference<>() {
        }));
    }

    @AfterEach
    private void tearDown() {
        weld.shutdown();
    }
}

