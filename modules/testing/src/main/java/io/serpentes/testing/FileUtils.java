package io.serpentes.testing;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Stream;

public class FileUtils {
    private FileUtils() {
    }

    public static void copyFile(Path source, Path target) throws IOException {
        Files.createDirectories(target.getParent());
        Files.copy(source.toAbsolutePath(), target.toAbsolutePath(), StandardCopyOption.REPLACE_EXISTING);
    }

    public static String toString(Path source) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        Stream<String> stream = Files.lines(source);
        stream.forEach(s -> stringBuilder.append(s).append("\n"));
        return stringBuilder.toString();
    }

    public static void printFileContents(String header, File inputFile, PrintStream printStream) throws IOException {
        if (header != null && !header.isEmpty()) {
            printStream.println(header);
        }
        final List<String> inputLines = Files.readAllLines(inputFile.toPath());
        inputLines.forEach(printStream::println);
    }
}
