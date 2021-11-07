package io.serpentes.testing.cdi.utils;

import io.serpentes.api.definition.trees.TypeTree;
import io.serpentes.definition.sources.file_system.api.directories.DefinitionDirectory;
import io.serpentes.definition.sources.file_system.api.file_properties.DefinitionFileProperties;
import io.serpentes.testing.cdi.extentions.definition.parsers.CustomDefinitionParsersPrecedence;
import io.serpentes.testing.cdi.extentions.definition.parsers.CustomDefinitionParsersPrecedenceCDIExtension;
import io.serpentes.testing.cdi.extentions.definition.sources.CustomDefinitionSourcesPrecedence;
import io.serpentes.testing.cdi.extentions.definition.sources.CustomDefinitionSourcesPrecedenceCDIExtension;
import io.serpentes.testing.cdi.extentions.definition.sources.file_system.file_path.*;
import io.serpentes.testing.cdi.extentions.definition.sources.file_system.file_properties.CustomDefaultDefinitionFilePropertiesCDIExtension;
import io.serpentes.testing.cdi.extentions.definition.sources.file_system.file_properties.CustomDefinitionFileProperties;
import io.serpentes.testing.cdi.extentions.definition.sources.file_system.file_properties.CustomDEfinitionFilePropertiesCDIExtension;
import io.serpentes.testing.cdi.extentions.definition.type.tree.TypeTreeDefinitionSource;
import io.serpentes.testing.cdi.extentions.definition.type.tree.TypeTreeDefinitionSourceCDIExtension;
import jakarta.enterprise.inject.se.SeContainerInitializer;

import java.nio.file.Path;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class DefinitionSourceCDIUtils {
    private DefinitionSourceCDIUtils() {
    }

    public static void setupDefinitionSources(final SeContainerInitializer seContainerInitializer, final TypeTree typeTree) {
        setupTypeTreeDefinitionSource(seContainerInitializer, typeTree);
        setupDefinitionSourcesPrecedence(seContainerInitializer);
    }

    public static void setupDefinitionSourcesPrecedence(final SeContainerInitializer seContainerInitializer) {
        final Set<Class<?>> classes = new LinkedHashSet<>();
        classes.add(TypeTreeDefinitionSource.class);
        setupDefinitionSourcesPrecedence(seContainerInitializer, classes);
    }

    public static void setupDefinitionSourcesPrecedence(final SeContainerInitializer seContainerInitializer, final Set<Class<?>> classes) {
        seContainerInitializer.addExtensions(new CustomDefinitionSourcesPrecedenceCDIExtension(new CustomDefinitionSourcesPrecedence(classes)));
    }

    public static void setupTypeTreeDefinitionSource(final SeContainerInitializer seContainerInitializer, final TypeTree typeTree) {
        seContainerInitializer.addExtensions(new TypeTreeDefinitionSourceCDIExtension(typeTree));
    }

    public static void setupFileSystemDefinitionSource(final SeContainerInitializer seContainerInitializer, final Path definitionFileDirectoryPath, final String definitionFileName, final Set<String> precedence) {
        setupDefaultDefinitionFile(seContainerInitializer, definitionFileDirectoryPath, definitionFileName);
        setupSupportedDefinitionFiles(seContainerInitializer, definitionFileDirectoryPath, definitionFileName);
        seContainerInitializer.addExtensions(new CustomDefinitionDirectoryPrecedenceCDIExtension(new CustomDefinitionDirectoryPrecedence(precedence)));
    }

    public static void setupSupportedDefinitionFiles(final SeContainerInitializer container, final Path definitionFileDirectoryPath, final String definitionFileName) {
        setupSupportedDefinitionDirectories(container, definitionFileDirectoryPath);
        setupSupportedDefinitionFileProperties(container, definitionFileName);
    }

    public static void setupSupportedDefinitionFileProperties(final SeContainerInitializer seContainerInitializer, final String definitionFileName) {
        final Set<DefinitionFileProperties> supportedDefinitionFileProperties = new HashSet<>();
        supportedDefinitionFileProperties.add(new CustomDefinitionFileProperties(definitionFileName));
        seContainerInitializer.addExtensions(new CustomDEfinitionFilePropertiesCDIExtension(supportedDefinitionFileProperties));
    }

    public static void setupSupportedDefinitionDirectories(final SeContainerInitializer seContainerInitializer, final Path definitionFileDirectoryPath) {
        final Set<DefinitionDirectory> supportedDefinitionDirectories = new HashSet<>();
        supportedDefinitionDirectories.add(new CustomDefinitionDirectory(definitionFileDirectoryPath));
        seContainerInitializer.addExtensions(new CustomDefinitionDirectoriesCDIExtension(supportedDefinitionDirectories));
    }

    public static void setupDefaultDefinitionFile(final SeContainerInitializer seContainerInitializer, final Path definitionFileDirectoryPath, String definitionFileName) {
        seContainerInitializer.addExtensions(new CustomDefaultDefinitionDirectoryCDIExtension(new CustomDefinitionDirectory(definitionFileDirectoryPath)));
        seContainerInitializer.addExtensions(new CustomDefaultDefinitionFilePropertiesCDIExtension(new CustomDefinitionFileProperties(definitionFileName)));
    }

    public static void setupDefinitionParsers(final SeContainerInitializer seContainerInitializer, final Set<String> precedence) {
        seContainerInitializer.addExtensions(new CustomDefinitionParsersPrecedenceCDIExtension(new CustomDefinitionParsersPrecedence(precedence)));
    }
}
