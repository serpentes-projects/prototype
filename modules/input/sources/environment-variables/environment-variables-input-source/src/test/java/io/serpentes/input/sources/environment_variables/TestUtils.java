package io.serpentes.input.sources.environment_variables;

import io.serpentes.api.definition.trees.TypeTree;
import io.serpentes.api.definition.trees.vertices.TypeVertex;
import io.serpentes.api.input.trees.InputTree;
import io.serpentes.api.input.trees.vertices.InputVertex;
import io.serpentes.input.sources.environment_variables.api.naming.EnvironmentVariableKeyNamingConvention;
import io.serpentes.input.sources.environment_variables.naming.EnvironmentVariableKeyNamingConventions;
import io.serpentes.input.sources.environment_variables.naming.snake_case.SnakeCaseKeyNamingConvention;
import io.serpentes.input.stores.base.InputSourceValues;
import io.serpentes.input.stores.base.InputStore;
import io.serpentes.testing.assertions.composed.ComposedAssertion;
import io.serpentes.testing.assertions.definition.type.tree.TypeTreeIterator;
import io.serpentes.testing.assertions.input.tree.TypeBasedInputVertexAssertion;
import io.serpentes.testing.assertions.input.tree.TypeToInputMapper;
import io.serpentes.testing.cdi.extentions.input.sources.environment_variables.naming.CustomEnvironmentVariableKeyNamingConventionPrecedence;
import io.serpentes.testing.cdi.extentions.input.sources.environment_variables.naming.CustomEnvironmentVariableKeyNamingConventionPrecedenceCDIExtension;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class TestUtils {
    private TestUtils() {
    }

    static InputStore stockInputValueStoreWithEnvironmentVariableValues(SeContainer container) {
        final var environmentVariableKeyNamingConventions = container.select(EnvironmentVariableKeyNamingConventions.class).get();
        environmentVariableKeyNamingConventions.deriveEnvironmentVariableKeyNamesFromTypeDefinition();
        final var environmentVariablesInputStoreClerk = container.select(EnvironmentVariablesInputStoreClerk.class).get();
        environmentVariablesInputStoreClerk.stockStore();
        return container.select(InputStore.class).get();
    }

    public static void assertInputValues(final ComposedAssertion composedAssertion, final InputStore inputStore, final TypeTree typeTree) {
        final var typeTreeIterator = new TypeTreeIterator(typeTree);
        final var typeBasedInputVertexAssertion = new TypeBasedInputVertexAssertion();
        final var typeToInputMapper = new TypeToInputMapper();
        final Map<TypeVertex, InputVertex> map = typeToInputMapper.map(typeTree, composedAssertion.getInputAssertion().expectedInputTree());
        while (typeTreeIterator.hasNext()) {
            final TypeVertex typeVertex = typeTreeIterator.next();
            final InputVertex expectedInputVertex = map.get(typeVertex);
            final InputSourceValues actualValues = inputStore.get(typeVertex);
            final InputTree actualInputTree = actualValues.findFirstNonNullValue();
            typeBasedInputVertexAssertion.assertEquals(typeVertex, expectedInputVertex, actualInputTree);
        }
    }

    public static void setupEnvironmentVariableInputSource(final SeContainerInitializer seContainerInitializer) {
        final Set<Class<? extends EnvironmentVariableKeyNamingConvention>> classes = new LinkedHashSet<>();
        classes.add(SnakeCaseKeyNamingConvention.class);
        seContainerInitializer.addExtensions(new CustomEnvironmentVariableKeyNamingConventionPrecedenceCDIExtension(new CustomEnvironmentVariableKeyNamingConventionPrecedence(classes)));
    }
}
