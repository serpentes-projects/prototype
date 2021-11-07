package io.serpentes.configuration.factories.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.serpentes.testing.TypeTest;
import io.serpentes.testing.assertions.composed.ComposedAssertion;
import io.serpentes.testing.assertions.composed.base.composite.collection.EmptyCollectionComposedAssertion;
import io.serpentes.testing.assertions.composed.base.composite.collection.primitives.*;
import io.serpentes.testing.assertions.composed.base.composite.map.EmptyMapComposedAssertion;
import io.serpentes.testing.assertions.composed.base.composite.map.MapWithAllValueTypesComposedAssertion;
import io.serpentes.testing.assertions.composed.base.composite.map.MapWithCompositeValueTypesComposedAssertion;
import io.serpentes.testing.assertions.composed.base.composite.map.MapWithPrimitiveValueTypesComposedAssertion;
import io.serpentes.testing.assertions.composed.base.primitives.*;
import io.serpentes.testing.cdi.extentions.input.sources.input_tree.ExtensionInputSource;
import io.serpentes.testing.cdi.extentions.input.sources.input_tree.InputTreeInputSource;
import io.serpentes.testing.cdi.utils.CDIUtils;
import io.serpentes.testing.cdi.utils.InputSourceCDIUtils;
import io.serpentes.testing.cdi.utils.DefinitionSourceCDIUtils;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import jakarta.enterprise.util.AnnotationLiteral;
import org.jboss.weld.environment.se.Weld;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashSet;
import java.util.Set;

public class JacksonTreeBuilderTest implements TypeTest {
    private SeContainerInitializer seContainerInitializer;

    @BeforeEach
    private void setup() {
        seContainerInitializer = new Weld();
    }

    @Test
    @Override
    public void testEmpty() {
    }

    @Test
    @Override
    public void testNull() {
        final JacksonTreeFactory jacksonTreeFactory = getJacksonTreeFactory(new NullComposedAssertion());
        final var jsonNode = jacksonTreeFactory.create(new ObjectMapper());
        Assertions.assertTrue(jsonNode.isNull());
    }

    private JacksonTreeFactory getJacksonTreeFactory(ComposedAssertion composedAssertion) {
        //TODO: Capture this in a default setup sequence.
        final Set<String> definitionParsersPrecedence = new LinkedHashSet<>();
        definitionParsersPrecedence.add("application/json");
        DefinitionSourceCDIUtils.setupDefinitionParsers(seContainerInitializer, definitionParsersPrecedence);

        final var expectedTypeTree = composedAssertion.getTypeDefinitionAssertion().expectedTypeTree();
        DefinitionSourceCDIUtils.setupDefinitionSources(seContainerInitializer, expectedTypeTree);

        final Set<String> inputParsersPrecedence = new LinkedHashSet<>();
        inputParsersPrecedence.add("application/json");
        InputSourceCDIUtils.setupInputParsers(seContainerInitializer, inputParsersPrecedence);

        final var inputTree = composedAssertion.getInputAssertion().expectedInputTree();
        InputSourceCDIUtils.setupInputTreeInputSource(seContainerInitializer, expectedTypeTree, inputTree);

        final var seContainer = CDIUtils.createSeContainer(seContainerInitializer);
        CDIUtils.stockEmptyInputStore( seContainer);
        final var inputTreeInputSource = seContainer.select(InputTreeInputSource.class, new AnnotationLiteral<ExtensionInputSource>() {
        }).get();
        inputTreeInputSource.addValuesToInputStore();

        return seContainer.select(JacksonTreeFactory.class).get();
    }

    @Test
    @Override
    public void testBoolean() {
        final var composedAssertion = new BooleanComposedAssertion();
        final JacksonTreeFactory jacksonTreeFactory = getJacksonTreeFactory(composedAssertion);
        final var jsonNode = jacksonTreeFactory.create(new ObjectMapper());
        Assertions.assertTrue(jsonNode.isBoolean());
        Assertions.assertEquals(composedAssertion.getInputAssertion().getContent().beforeParsing(), jsonNode.toString());
    }

    @Test
    @Override
    public void testInteger() {
        final var composedAssertion = new IntegerComposedAssertion();
        final JacksonTreeFactory jacksonTreeFactory = getJacksonTreeFactory(composedAssertion);
        final var jsonNode = jacksonTreeFactory.create(new ObjectMapper());
        Assertions.assertTrue(jsonNode.isInt());
        Assertions.assertEquals(composedAssertion.getInputAssertion().getContent().beforeParsing(), jsonNode.toString());
    }

    @Test
    @Override
    public void testFloatingPoint() {
        final var composedAssertion = new FloatingPointComposedAssertion();
        final JacksonTreeFactory jacksonTreeFactory = getJacksonTreeFactory(composedAssertion);
        final var jsonNode = jacksonTreeFactory.create(new ObjectMapper());
        Assertions.assertTrue(jsonNode.isDouble());
        Assertions.assertEquals(composedAssertion.getInputAssertion().getContent().beforeParsing(), jsonNode.toString());
    }

    @Test
    @Override
    public void testString() {
        final var composedAssertion = new StringComposedAssertion();
        final JacksonTreeFactory jacksonTreeFactory = getJacksonTreeFactory(composedAssertion);
        final var jsonNode = jacksonTreeFactory.create(new ObjectMapper());
        Assertions.assertTrue(jsonNode.isTextual());
        Assertions.assertEquals(composedAssertion.getInputAssertion().getContent().beforeParsing(), jsonNode.toString());
    }

    @Test
    @Override
    public void testEmptyMap() {
        final var composedAssertion = new EmptyMapComposedAssertion();
        final JacksonTreeFactory jacksonTreeFactory = getJacksonTreeFactory(composedAssertion);
        final var jsonNode = jacksonTreeFactory.create(new ObjectMapper());
        Assertions.assertTrue(jsonNode.isObject());
        Assertions.assertEquals(composedAssertion.getInputAssertion().getContent().beforeParsing(), jsonNode.toString());
    }

    @Test
    @Override
    public void testMapWithPrimitiveValueTypesOnly() {
        final var composedAssertion = new MapWithPrimitiveValueTypesComposedAssertion();
        final JacksonTreeFactory jacksonTreeFactory = getJacksonTreeFactory(composedAssertion);
        final var jsonNode = jacksonTreeFactory.create(new ObjectMapper());
        Assertions.assertTrue(jsonNode.isObject());
        Assertions.assertEquals(composedAssertion.getInputAssertion().getContent().beforeParsing(), jsonNode.toString());
    }

    @Test
    @Override
    public void testMapWithCompositeValueTypesOnly() {
        final var composedAssertion = new MapWithCompositeValueTypesComposedAssertion();
        final JacksonTreeFactory jacksonTreeFactory = getJacksonTreeFactory(composedAssertion);
        final var jsonNode = jacksonTreeFactory.create(new ObjectMapper());
        Assertions.assertTrue(jsonNode.isObject());
        Assertions.assertEquals(composedAssertion.getInputAssertion().getContent().beforeParsing(), jsonNode.toString());
    }

    @Test
    @Override
    public void testMapWithAllValueTypes() {
        final var composedAssertion = new MapWithAllValueTypesComposedAssertion();
        final JacksonTreeFactory jacksonTreeFactory = getJacksonTreeFactory(composedAssertion);
        final var jsonNode = jacksonTreeFactory.create(new ObjectMapper());
        Assertions.assertTrue(jsonNode.isObject());
        Assertions.assertEquals(composedAssertion.getInputAssertion().getContent().beforeParsing(), jsonNode.toString());
    }

    @Test
    @Override
    public void testEmptyCollection() {
        final var composedAssertion = new EmptyCollectionComposedAssertion();
        final JacksonTreeFactory jacksonTreeFactory = getJacksonTreeFactory(composedAssertion);
        final var jsonNode = jacksonTreeFactory.create(new ObjectMapper());
        Assertions.assertTrue(jsonNode.isArray());
        Assertions.assertEquals(composedAssertion.getInputAssertion().getContent().beforeParsing(), jsonNode.toString());
    }

    @Test
    @Override
    public void testNullCollection() {
        final var composedAssertion = new NullCollectionComposedAssertion();
        final JacksonTreeFactory jacksonTreeFactory = getJacksonTreeFactory(composedAssertion);
        final var jsonNode = jacksonTreeFactory.create(new ObjectMapper());
        Assertions.assertTrue(jsonNode.isArray());
        Assertions.assertEquals(composedAssertion.getInputAssertion().getContent().beforeParsing(), jsonNode.toString());
    }

    @Test
    @Override
    public void testBooleanCollection() {
        final var composedAssertion = new BooleanCollectionComposedAssertion();
        final JacksonTreeFactory jacksonTreeFactory = getJacksonTreeFactory(composedAssertion);
        final var jsonNode = jacksonTreeFactory.create(new ObjectMapper());
        Assertions.assertTrue(jsonNode.isArray());
        Assertions.assertEquals(composedAssertion.getInputAssertion().getContent().beforeParsing(), jsonNode.toString());
    }

    @Test
    @Override
    public void testIntegerCollection() {
        final var composedAssertion = new IntegerCollectionComposedAssertion();
        final JacksonTreeFactory jacksonTreeFactory = getJacksonTreeFactory(composedAssertion);
        final var jsonNode = jacksonTreeFactory.create(new ObjectMapper());
        Assertions.assertTrue(jsonNode.isArray());
        Assertions.assertEquals(composedAssertion.getInputAssertion().getContent().beforeParsing(), jsonNode.toString());
    }

    @Test
    @Override
    public void testFloatingPointCollection() {
        final var composedAssertion = new FloatingPointCollectionComposedAssertion();
        final JacksonTreeFactory jacksonTreeFactory = getJacksonTreeFactory(composedAssertion);
        final var jsonNode = jacksonTreeFactory.create(new ObjectMapper());
        Assertions.assertTrue(jsonNode.isArray());
        Assertions.assertEquals(composedAssertion.getInputAssertion().getContent().beforeParsing(), jsonNode.toString());
    }

    @Test
    @Override
    public void testStringCollection() {
        final var composedAssertion = new StringCollectionComposedAssertion();
        final JacksonTreeFactory jacksonTreeFactory = getJacksonTreeFactory(composedAssertion);
        final var jsonNode = jacksonTreeFactory.create(new ObjectMapper());
        Assertions.assertTrue(jsonNode.isArray());
        Assertions.assertEquals(composedAssertion.getInputAssertion().getContent().beforeParsing(), jsonNode.toString());
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
