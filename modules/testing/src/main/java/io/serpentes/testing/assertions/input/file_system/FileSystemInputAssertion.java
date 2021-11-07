package io.serpentes.testing.assertions.input.file_system;

import io.serpentes.input.sources.file_system.api.directories.InputDirectory;
import io.serpentes.input.sources.file_system.api.file_properties.InputFileProperties;
import io.serpentes.testing.assertions.input.base.InputAssertion;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.text.DateFormat;
import java.util.Date;

//TODO: Remove duplication.
public interface FileSystemInputAssertion<T> extends InputAssertion<T> {
    default Path getInputPath() {
        return Paths.get(getInputDirectory().getPath().toString(), getInputFileProperties().getName());
    }

    InputDirectory getInputDirectory();

    InputFileProperties getInputFileProperties();

    default Path createInputFile(final FileAttribute<?>... fileAttributes) throws IOException {
        return this.createInputFile(this.getInputPath().toAbsolutePath(), fileAttributes);
    }

    default Path createInputFile(final Path path, final FileAttribute<?>... fileAttributes) throws IOException {
        if (path != null && path.isAbsolute()) {
            if (!Files.exists(path)) {
                final Path parentDirectoryPath = path.getParent();
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

    default Path createTemporaryInputFile(final FileAttribute<?>... fileAttributes) throws IOException {
        final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT);
        final String date = dateFormat.format(new Date());
        final String suffix = getInputFileProperties().getName() + "-input-" + date;
        return createTempInputFile("serpentes-test", suffix, fileAttributes);
    }

    default Path createTempInputFile(final String prefix, final String suffix, final FileAttribute<?>... fileAttributes) throws IOException {
        return Files.createTempFile(prefix, suffix, fileAttributes);
    }

    default Path createInputFileAndWriteContent(final Path path, final FileAttribute<?>... fileAttributes) throws IOException {
        final var inputFile = createInputFile(path, fileAttributes);
        writeInputContentToFile(path, getContent().beforeParsing());
        return inputFile;
    }

    default void writeInputContentToFile(final Path path, final String content) throws IOException {
        Files.writeString(path, content, StandardCharsets.UTF_8);
    }
}
