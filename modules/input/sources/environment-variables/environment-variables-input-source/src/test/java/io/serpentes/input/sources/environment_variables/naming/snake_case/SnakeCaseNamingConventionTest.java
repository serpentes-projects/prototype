package io.serpentes.input.sources.environment_variables.naming.snake_case;

import io.serpentes.input.sources.environment_variables.TestUtils;
import io.serpentes.input.sources.environment_variables.naming.EnvironmentVariableKeyNameStore;
import io.serpentes.input.sources.environment_variables.naming.KeyNameSourceValues;
import io.serpentes.testing.assertions.definition.file_system.composite.map.FileSystemMapWithAllValueTypesTypeDefinitionAssertion;
import io.serpentes.testing.cdi.utils.CDIUtils;
import io.serpentes.testing.cdi.utils.DefinitionSourceCDIUtils;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.junit.jupiter.api.*;
import org.junitpioneer.jupiter.SetEnvironmentVariable;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class SnakeCaseNamingConventionTest {

    @Test
    @DisplayName("Feature(retrieval): 'Attempts to determine input values using a default naming convention.' (SnakeCase)")
    @Tags({
            @Tag("feat:retrieval"),
            @Tag("feat:retrieval:input"),
            @Tag("feat:retrieval:input:sources"),
            @Tag("feat:retrieval:input:sources:environment-variables"),
            @Tag("feat:retrieval:input:sources:environment-variables:naming"),
            @Tag("feat:retrieval:input:sources:environment-variables:naming:default")
    })
    public void determineNamesUsingDefaultNamingConvention() {
        final var typeDefinitionAssertion = new FileSystemMapWithAllValueTypesTypeDefinitionAssertion(null);
        final Weld weld = new Weld();
        DefinitionSourceCDIUtils.setupDefinitionSources(weld, typeDefinitionAssertion.expectedTypeTree());
        final Set<String> definitionParsersPrecedence = new LinkedHashSet<>();
        definitionParsersPrecedence.add("application/json");
        DefinitionSourceCDIUtils.setupDefinitionParsers(weld, definitionParsersPrecedence);
        TestUtils.setupEnvironmentVariableInputSource(weld);

        final WeldContainer weldContainer = CDIUtils.createSeContainer(weld);
        CDIUtils.stockEmptyInputStore(weldContainer);

        SnakeCaseKeyNamingConvention snakeCase = weldContainer.select(SnakeCaseKeyNamingConvention.class).get();
        snakeCase.addEnvironmentVariableNamesToStore();
        final EnvironmentVariableKeyNameStore environmentVariableKeyNameStore = weldContainer.select(EnvironmentVariableKeyNameStore.class).get();

        Set<String> names = getNames(environmentVariableKeyNameStore);

        Assertions.assertEquals(8, names.size());
        Assertions.assertTrue(names.contains("BOA"));
        Assertions.assertTrue(names.contains("BOA_NULL"));
        Assertions.assertTrue(names.contains("BOA_BOOLEAN"));
        Assertions.assertTrue(names.contains("BOA_INTEGER"));
        Assertions.assertTrue(names.contains("BOA_FLOAT"));
        Assertions.assertTrue(names.contains("BOA_STRING"));
        Assertions.assertTrue(names.contains("BOA_MAP"));
        Assertions.assertTrue(names.contains("BOA_COLLECTION"));

        weld.shutdown();
    }

    private Set<String> getNames(EnvironmentVariableKeyNameStore environmentVariableKeyNameStore) {
        Set<String> names = new HashSet<>();
        for (KeyNameSourceValues value : environmentVariableKeyNameStore.values()) {
            names.addAll(value.getNames());
        }
        return names;
    }

    @Test
    @DisplayName("Feature(retrieval): 'Attempts to determine input values using a default naming convention with minimum collection-item look-ahead in the environment.' (SnakeCase)")
    @Tags({
            @Tag("feat:retrieval"),
            @Tag("feat:retrieval:input:sources"),
            @Tag("feat:retrieval:input:sources:environment-variables"),
            @Tag("feat:retrieval:input:sources:environment-variables:naming"),
            @Tag("feat:retrieval:input:sources:environment-variables:naming:default")
    })
    @SetEnvironmentVariable(key="BOA_COLLECTION_0",value= "null")
    public void determineNamesUsingDefaultNamingConventionWithMinimumCollectionItemLookAhead() {
        final var typeDefinitionAssertion = new FileSystemMapWithAllValueTypesTypeDefinitionAssertion(null);
        final Weld weld = new Weld();
        DefinitionSourceCDIUtils.setupDefinitionSources(weld, typeDefinitionAssertion.expectedTypeTree());
        final Set<String> defintionParsersPrecedence = new LinkedHashSet<>();
        defintionParsersPrecedence.add("application/json");
        DefinitionSourceCDIUtils.setupDefinitionParsers(weld, defintionParsersPrecedence);
        TestUtils.setupEnvironmentVariableInputSource(weld);

        final WeldContainer weldContainer = CDIUtils.createSeContainer(weld);
        CDIUtils.stockEmptyInputStore(weldContainer);

        SnakeCaseKeyNamingConvention snakeCase = weldContainer.select(SnakeCaseKeyNamingConvention.class).get();
        snakeCase.addEnvironmentVariableNamesToStore();
        final EnvironmentVariableKeyNameStore environmentVariableKeyNameStore = weldContainer.select(EnvironmentVariableKeyNameStore.class).get();

        Collection<String> names = getNames(environmentVariableKeyNameStore);

        Assertions.assertEquals(9, names.size());
        Assertions.assertTrue(names.contains("BOA"));
        Assertions.assertTrue(names.contains("BOA_NULL"));
        Assertions.assertTrue(names.contains("BOA_BOOLEAN"));
        Assertions.assertTrue(names.contains("BOA_INTEGER"));
        Assertions.assertTrue(names.contains("BOA_FLOAT"));
        Assertions.assertTrue(names.contains("BOA_STRING"));
        Assertions.assertTrue(names.contains("BOA_MAP"));
        Assertions.assertTrue(names.contains("BOA_COLLECTION"));
        Assertions.assertTrue(names.contains("BOA_COLLECTION_0"));

        weld.shutdown();
    }

    @Test
    @DisplayName("Feature(retrieval): 'Attempts to determine input values using a default naming convention with maximum collection-item look-ahead in the environment.' (SnakeCase)")
    @Tags({
            @Tag("feat:retrieval"),
            @Tag("feat:retrieval:input:sources"),
            @Tag("feat:retrieval:input:sources:environment-variables"),
            @Tag("feat:retrieval:input:sources:environment-variables:naming"),
            @Tag("feat:retrieval:input:sources:environment-variables:naming:default")
    })
    @SetEnvironmentVariable(key="BOA_COLLECTION_49",value= "null")
    public void determineNamesUsingDefaultNamingConventionWithMaximumCollectionItemLookAhead() {
        final var typeDefinitionAssertion = new FileSystemMapWithAllValueTypesTypeDefinitionAssertion(null);
        final Weld weld = new Weld();
        DefinitionSourceCDIUtils.setupDefinitionSources(weld, typeDefinitionAssertion.expectedTypeTree());
        final Set<String> definitionParsersPrecedence = new LinkedHashSet<>();
        definitionParsersPrecedence.add("application/json");
        DefinitionSourceCDIUtils.setupDefinitionParsers(weld, definitionParsersPrecedence);
        TestUtils.setupEnvironmentVariableInputSource(weld);

        final WeldContainer weldContainer = CDIUtils.createSeContainer(weld);
        CDIUtils.stockEmptyInputStore(weldContainer);

        SnakeCaseKeyNamingConvention snakeCase = weldContainer.select(SnakeCaseKeyNamingConvention.class).get();
        snakeCase.addEnvironmentVariableNamesToStore();
        final EnvironmentVariableKeyNameStore environmentVariableKeyNameStore = weldContainer.select(EnvironmentVariableKeyNameStore.class).get();

        Collection<String> names = getNames(environmentVariableKeyNameStore);

        Assertions.assertEquals(58, names.size());
        Assertions.assertTrue(names.contains("BOA"));
        Assertions.assertTrue(names.contains("BOA_NULL"));
        Assertions.assertTrue(names.contains("BOA_BOOLEAN"));
        Assertions.assertTrue(names.contains("BOA_INTEGER"));
        Assertions.assertTrue(names.contains("BOA_FLOAT"));
        Assertions.assertTrue(names.contains("BOA_STRING"));
        Assertions.assertTrue(names.contains("BOA_MAP"));
        Assertions.assertTrue(names.contains("BOA_COLLECTION"));
        Assertions.assertTrue(names.contains("BOA_COLLECTION_0"));
        Assertions.assertTrue(names.contains("BOA_COLLECTION_49"));
        Assertions.assertFalse(names.contains("BOA_COLLECTION_50"));

        weld.shutdown();
    }
}