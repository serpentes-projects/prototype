package io.serpentes.testing.assertions.definition.file_system;

import io.serpentes.definition.sources.file_system.api.directories.DefinitionDirectory;
import io.serpentes.definition.sources.file_system.api.file_properties.DefinitionFileProperties;
import io.serpentes.testing.assertions.definition.base.TypeDefinitionAssertion;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.text.DateFormat;
import java.util.Date;

public interface FileSystemTypeDefinitionAssertion<T> extends TypeDefinitionAssertion {
    default Path getDefinitionPath() {
        return Paths.get(getDefinitionDirectory().getPath().toString(), getDefinitionFileProperties().getName()).toAbsolutePath();
    }

    DefinitionDirectory getDefinitionDirectory();

    DefinitionFileProperties getDefinitionFileProperties();

    default Path createDefinitionFile(final FileAttribute<?>... fileAttributes) throws IOException {
        return this.createDefinitionFile(this.getDefinitionPath(), fileAttributes);
    }

    default Path createDefinitionFile(final Path path, final FileAttribute<?>... fileAttributes) throws IOException {
        if (path != null && path.isAbsolute()) {
            if (!Files.exists(path)) {
                final var parentDirectoryPath = path.getParent();
                if (parentDirectoryPath != null) {
                    Files.createDirectories(parentDirectoryPath);
                }
                return Files.createFile(path, fileAttributes);
            } else {
                return path;
            }
        }
        throw new IllegalArgumentException("Path is either null or not absolute: " + path);
    }

    default Path createTemporaryDefinitionFile(final FileAttribute<?>... fileAttributes) throws IOException {
        final var dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT);
        final var date = dateFormat.format(new Date());
        final var suffix = getDefinitionFileProperties().getName() + "-definition-" + date;
        return this.createTemporaryDefinitionFile("serpentes-test", suffix, fileAttributes);
    }

    default Path createTemporaryDefinitionFile(final String prefix, final String suffix, final FileAttribute<?>... fileAttributes) throws IOException {
        return Files.createTempFile(prefix, suffix, fileAttributes);
    }

    default Path createDefinitionFileAndWriteContent(final FileAttribute<?>... fileAttributes) throws IOException {
        final var definitionFile = createDefinitionFile(this.getDefinitionPath(), fileAttributes);
        writeDefinitionContentToFile(this.getDefinitionPath(), getDefinitionContent());
        return definitionFile;
    }

    default Path createDefinitionFileAndWriteContent(final Path path, final FileAttribute<?>... fileAttributes) throws IOException {
        final var definitionFile = createDefinitionFile(path, fileAttributes);
        writeDefinitionContentToFile(path, getDefinitionContent());
        return definitionFile;
    }

    default void writeDefinitionContentToFile(final Path path, final String content) throws IOException {
        Files.writeString(path, content, StandardCharsets.UTF_8);
    }
}
