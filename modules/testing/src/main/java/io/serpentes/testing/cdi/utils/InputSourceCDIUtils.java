package io.serpentes.testing.cdi.utils;

import io.serpentes.api.input.trees.InputTree;
import io.serpentes.api.definition.trees.TypeTree;
import io.serpentes.input.sources.file_system.api.directories.InputDirectory;
import io.serpentes.input.sources.file_system.api.file_properties.InputFileProperties;
import io.serpentes.testing.cdi.extentions.input.parsers.CustomInputParsersPrecedence;
import io.serpentes.testing.cdi.extentions.input.parsers.CustomInputParsersPrecedenceCDIExtension;
import io.serpentes.testing.cdi.extentions.input.sources.file_system.file_path.*;
import io.serpentes.testing.cdi.extentions.input.sources.file_system.file_properties.*;
import io.serpentes.testing.cdi.extentions.input.sources.input_tree.InputTreeInputSourceCDIExtension;
import jakarta.enterprise.inject.se.SeContainerInitializer;

import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

public class InputSourceCDIUtils {
    private InputSourceCDIUtils() {
    }

    public static void setupInputTreeInputSource(final SeContainerInitializer seContainerInitializer, final TypeTree typeTree, final InputTree inputTree) {
        seContainerInitializer.addExtensions(new InputTreeInputSourceCDIExtension(typeTree, inputTree));
    }

    public static void setupFileSystemInputSource(final SeContainerInitializer seContainerInitializer, final Path inputFileDirectoryPath, final String inputFileName) {
        setupDefaultInputFile(seContainerInitializer, inputFileDirectoryPath, inputFileName);
        setupSupportedInputFiles(seContainerInitializer, inputFileDirectoryPath, inputFileName);
    }

    public static void setupSupportedInputFiles(final SeContainerInitializer seContainerInitializer, final Path inputFileDirectoryPath, String inputFileName) {
        setupSupportedInputDirectory(seContainerInitializer, inputFileDirectoryPath);
        setupSupportedInputFileProperties(seContainerInitializer, inputFileName);
    }

    public static void setupSupportedInputFileProperties(final SeContainerInitializer seContainerInitializer, String inputFileName) {
        final Set<InputFileProperties> supportedInputFileProperties = new HashSet<>();
        supportedInputFileProperties.add(new CustomInputFileProperties(inputFileName));
        seContainerInitializer.addExtensions(new CustomInputFilePropertiesCDIExtension(supportedInputFileProperties));
    }

    public static void setupSupportedInputDirectory(final SeContainerInitializer seContainerInitializer, final Path inputFileDirectoryPath) {
        final Set<InputDirectory> supportedInputDirectory = new HashSet<>();
        supportedInputDirectory.add(new CustomInputDirectory(inputFileDirectoryPath));
        seContainerInitializer.addExtensions(new CustomInputDirectoriesCDIExtension(supportedInputDirectory));
    }

    public static void setupDefaultInputFile(final SeContainerInitializer seContainerInitializer, final Path inputFileDirectoryPath, String inputFileName) {
        seContainerInitializer.addExtensions(new CustomDefaultInputDirectoryCDIExtension(new CustomInputDirectory(inputFileDirectoryPath)));
        seContainerInitializer.addExtensions(new CustomDefaultInputFilePropertiesCDIExtension(new CustomInputFileProperties(inputFileName)));
    }

    public static void setupInputDirectoryPrecedence(final SeContainerInitializer seContainerInitializer, final Set<String> precedence) {
        seContainerInitializer.addExtensions(new CustomInputDirectoryPrecedenceCDIExtension(new CustomInputDirectoryPrecedence(precedence)));
    }

    public static void setupInputFilePropertiesPrecedence(final SeContainerInitializer seContainerInitializer, final Set<String> precedence) {
        seContainerInitializer.addExtensions(new CustomInputFilePropertiesPrecedenceCDIExtension(new CustomInputFilePropertiesPrecedence(precedence)));
    }


    public static void setupInputParsers(final SeContainerInitializer seContainerInitializer, final Set<String> precedence) {
        seContainerInitializer.addExtensions(new CustomInputParsersPrecedenceCDIExtension(new CustomInputParsersPrecedence(precedence)));
    }
}
