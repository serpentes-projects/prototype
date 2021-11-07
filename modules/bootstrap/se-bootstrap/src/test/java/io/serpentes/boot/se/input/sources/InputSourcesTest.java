package io.serpentes.boot.se.input.sources;

import io.serpentes.boot.se.definition.sources.file_system.file_paths.DefaultDefinitionDirectory;
import io.serpentes.boot.se.definition.sources.file_system.file_properties.DefaultDefinitionFile;
import io.serpentes.boot.se.input.sources.file_system.file_paths.DefaultInputDirectory;
import io.serpentes.boot.se.input.sources.file_system.file_properties.DefaultInputFile;
import io.serpentes.api.definition.sources.DefinitionSources;
import io.serpentes.api.input.trees.InputTree;
import io.serpentes.api.input.trees.vertices.terminal.InputTerminalVertex;
import io.serpentes.input.sources.command_line.parameters.providers.cdi_extension.CommandLineParametersCDIExtension;
import io.serpentes.input.stores.base.InputStore;
import io.serpentes.testing.assertions.composed.file_system.primitives.StringFileSystemAssertion;
import io.serpentes.testing.assertions.definition.type.tree.TypeTreeIterator;
import io.serpentes.testing.assertions.input.base.composite.JSONUtils;
import io.serpentes.testing.assertions.input.tree.InputTreeIterator;
import io.serpentes.testing.cdi.utils.CDIUtils;
import org.jboss.weld.environment.se.Weld;
import org.junit.jupiter.api.*;
import org.junitpioneer.jupiter.SetEnvironmentVariable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class InputSourcesTest {
    final Weld weld = new Weld();
    final String environmentVariableValue = "env-var";

    @Test
    @Tags({
            @Tag("feat:retrieval"),
            @Tag("feat:retrieval:input:sources"),
            @Tag("feat:retrieval:input:sources:precedence")
    })
    @SetEnvironmentVariable(key = "BOA", value = "\"" + environmentVariableValue + "\"")
    public void testDefaultPrecedence() throws IOException {
        // Assemble
        final var commandLineParameterValue = "command-line";
        final String[] parameters = {JSONUtils.wrapInQuotationMarks(commandLineParameterValue)};
        // TODO: Ensure that users don't have to add this CDIExtension themselves.
        // Should be handled by the SEBootstrap module.
        weld.addExtension(new CommandLineParametersCDIExtension(parameters));
        final var weldContainer = CDIUtils.createSeContainer(weld);
        final var stringFileSystemAssertion = new StringFileSystemAssertion();

        final var typeDefinitionAssertion = stringFileSystemAssertion.getTypeDefinitionAssertion();
        final var absoluteDefinitionPath = Paths.get(new DefaultDefinitionDirectory().getPath().toString(), new DefaultDefinitionFile().getName()).toAbsolutePath();
        typeDefinitionAssertion.createDefinitionFileAndWriteContent(absoluteDefinitionPath);

        final var inputAssertion = stringFileSystemAssertion.getInputAssertion();
        final Path absoluteInputPath = Paths.get(new DefaultInputDirectory().getPath().toString(), new DefaultInputFile().getName());
        inputAssertion.createInputFileAndWriteContent(absoluteInputPath);

        final String fileSystemValue = JSONUtils.unWrapQuotationMarks(inputAssertion.getContent().beforeParsing());

        CDIUtils.stockEmptyInputStore(weldContainer);
        final InputSources inputSources = weldContainer.select(InputSources.class).get();

        // Act
        inputSources.addValuesToInputStore();

        // Assert
        final String[] expectedValues = new String[]{commandLineParameterValue, environmentVariableValue, fileSystemValue};
        final Iterator<String> expectedInputIterator = Arrays.stream(expectedValues).iterator();

        final var definitionSources = weldContainer.select(DefinitionSources.class).get();
        final var typeTree = definitionSources.getTypeTree();
        final var typeTreeIterator = new TypeTreeIterator(typeTree);
        final var expectedTypeVertex = typeTreeIterator.next();
        final var inputStore = weldContainer.select(InputStore.class).get();
        final var actualInputValues = inputStore.get(expectedTypeVertex);
        final Iterator<List<InputTree>> actualInputValuesIterator = actualInputValues.getOrderedInputSourceValues().iterator();

        Assertions.assertEquals(3, actualInputValues.getInputSourceValues().size());

        while (expectedInputIterator.hasNext() && actualInputValuesIterator.hasNext()) {
            final List<InputTree> next = actualInputValuesIterator.next();
            final var actualInputValueIterator = new InputTreeIterator(next.get(0));
            final InputTerminalVertex actualInputVertex = (InputTerminalVertex) actualInputValueIterator.next();
            Assertions.assertEquals(expectedInputIterator.next(), actualInputVertex.getValue());
        }

        final InputTree firstNonNullValue = actualInputValues.findFirstNonNullValue();
        final InputTreeIterator inputTreeIterator = new InputTreeIterator(firstNonNullValue);
        final InputTerminalVertex terminalVertex = (InputTerminalVertex) inputTreeIterator.next();

        Assertions.assertEquals(commandLineParameterValue, terminalVertex.getValue());

        Files.deleteIfExists(absoluteDefinitionPath);
        Files.deleteIfExists(absoluteInputPath);
    }

    @AfterEach
    private void tearDown() {
        weld.shutdown();
    }
}