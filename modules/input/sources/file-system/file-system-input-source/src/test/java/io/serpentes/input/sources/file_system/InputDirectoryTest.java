package io.serpentes.input.sources.file_system;

import io.serpentes.input.sources.file_system.implementation.FileSystemInputSource;
import io.serpentes.input.stores.base.InputStore;
import io.serpentes.testing.assertions.composed.file_system.composite.map.MapWithAllValueTypesFileSystemAssertion;
import io.serpentes.testing.cdi.utils.CDIUtils;
import io.serpentes.testing.cdi.utils.DefinitionSourceCDIUtils;
import io.serpentes.testing.cdi.utils.InputSourceCDIUtils;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashSet;
import java.util.Set;

@Tags({
        @Tag("feat:retrieval"),
        @Tag("feat:retrieval:input-sources"),
        @Tag("feat:retrieval:input-sources:file-system")
})
public class InputDirectoryTest {

    @Test
    @Tags({
            @Tag("feat:retrieval"),
            @Tag("feat:retrieval:input"),
            @Tag("feat:retrieval:input:sources"),
            @Tag("feat:retrieval:input:sources:file-system"),
            @Tag("feat:retrieval:input:sources:file-system:directories"),
            @Tag("feat:retrieval:input:sources:file-system:directories:custom"),
            @Tag("feat:retrieval:input:sources:file-system:file"),
            @Tag("feat:retrieval:input:sources:file-system:file:properties"),
            @Tag("feat:retrieval:input:sources:file-system:file:properties:custom")
    })
    public void retrieveInputFromDefaultFileLocation() throws IOException {
        final var mapWithAllValueTypesFileSystemAssertion = new MapWithAllValueTypesFileSystemAssertion();

        final var definitionFileDirectoryPath = Paths.get("src", "main", "resources", "configuration", "definition").toAbsolutePath();
        final var definitionFileName = "serpentes.schema.json";
        final var fullDefinitionPath = Paths.get(definitionFileDirectoryPath.toString(), definitionFileName);

        final var inputFileDirectoryPath = Paths.get("src", "main", "resources", "configuration", "file_system").toAbsolutePath();
        final var inputFileName = "serpentes.json";
        final var fullInputPath = Paths.get(inputFileDirectoryPath.toString(), inputFileName);

        final var typeDefinitionAssertion = mapWithAllValueTypesFileSystemAssertion.getTypeDefinitionAssertion();
        typeDefinitionAssertion.createDefinitionFileAndWriteContent(fullDefinitionPath);
        mapWithAllValueTypesFileSystemAssertion.getInputAssertion().createInputFileAndWriteContent(fullInputPath);

        final var weld = new Weld();

        DefinitionSourceCDIUtils.setupDefinitionSources(weld, typeDefinitionAssertion.expectedTypeTree());
        final Set<String> definitionParserPrecedence = new LinkedHashSet<>();
        definitionParserPrecedence.add("application/json");
        DefinitionSourceCDIUtils.setupDefinitionParsers(weld, definitionParserPrecedence);

        final Set<String> inputParserPrecedence = new LinkedHashSet<>();
        inputParserPrecedence.add("application/json");
        InputSourceCDIUtils.setupInputParsers(weld, inputParserPrecedence);
        InputSourceCDIUtils.setupFileSystemInputSource(weld, inputFileDirectoryPath, inputFileName);
        final Set<String> inputDirectoryPrecedence = new LinkedHashSet<>();
        inputDirectoryPrecedence.add("custom");
        InputSourceCDIUtils.setupInputDirectoryPrecedence(weld, inputDirectoryPrecedence);
        final Set<String> inputFilePropertiesPrecedence = new LinkedHashSet<>();
        inputFilePropertiesPrecedence.add("custom");
        InputSourceCDIUtils.setupInputFilePropertiesPrecedence(weld, inputFilePropertiesPrecedence);
        final WeldContainer weldContainer = CDIUtils.createSeContainer(weld);


        CDIUtils.stockEmptyInputStore(weldContainer);

        final var fileSystemInputSource = weldContainer.select(FileSystemInputSource.class).get();
        fileSystemInputSource.addValuesToInputStore();
        final var inputStore = weldContainer.select(InputStore.class).get();

        //FIXME: No assertions

        Files.delete(fullDefinitionPath);
        Files.delete(fullInputPath);
        weld.shutdown();
    }
}
