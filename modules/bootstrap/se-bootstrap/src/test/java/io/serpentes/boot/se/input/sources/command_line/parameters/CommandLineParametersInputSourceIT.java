package io.serpentes.boot.se.input.sources.command_line.parameters;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.serpentes.boot.se.definition.sources.file_system.file_paths.DefaultDefinitionDirectory;
import io.serpentes.boot.se.definition.sources.file_system.file_properties.DefaultDefinitionFile;
import io.serpentes.testing.TypeTest;
import io.serpentes.testing.assertions.composed.file_system.composite.map.EmptyMapFileSystemAssertion;
import io.serpentes.testing.assertions.composed.file_system.composite.map.MapWithAllValueTypesFileSystemAssertion;
import io.serpentes.testing.assertions.composed.file_system.composite.map.MapWithCompositeValueTypesFileSystemAssertion;
import io.serpentes.testing.assertions.composed.file_system.composite.map.MapWithPrimitiveValueTypesFileSystemAssertion;
import io.serpentes.testing.assertions.composed.file_system.primitives.*;
import io.serpentes.testing.assertions.input.base.DeserializableInputAssertion;
import io.serpentes.testing.assertions.input.base.InputAssertion;
import io.serpentes.testing.assertions.definition.file_system.FileSystemTypeDefinitionAssertion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

class CommandLineParametersInputSourceIT implements TypeTest {
    private String currentClassPaths;
    private String currentExecutionDirectory;
    private Path outputFilePath;
    private String fileToRun;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() throws IOException {
        currentClassPaths = System.getProperty("java.class.path");
        currentExecutionDirectory = System.getProperty("user.dir");
        outputFilePath = Files.createTempFile("temp", ".json");
        fileToRun = String.join("", ClassWithCommandLineParameters.class.getCanonicalName().replace(".", "/"));
        objectMapper = new ObjectMapper();
    }

    @Test
    @Override
    public void testEmpty() {
        //TODO: Implement.
    }

    @Test
    @Override
    public void testNull() throws Exception {
        final var fileSystemAssertion = new NullFileSystemAssertion();
        ProcessBuilder javaRuntimeProcessBuilder = createJavaRunTimeProcessBuilder(
                fileSystemAssertion.getTypeDefinitionAssertion(),
                fileSystemAssertion.getInputAssertion(),
                "null");

        startAndAssertProcess(javaRuntimeProcessBuilder);
        assertValue(fileSystemAssertion.getInputAssertion(), fileSystemAssertion.getInputAssertion());
    }

    private <T> ProcessBuilder createJavaRunTimeProcessBuilder(final FileSystemTypeDefinitionAssertion<?> typeDefinitionAssertion, final DeserializableInputAssertion<?> inputAssertion, final String... valueParameters) {
        try {
            final var absoluteDefinitionPath = Paths.get(new DefaultDefinitionDirectory().getPath().toString(), new DefaultDefinitionFile().getName()).toAbsolutePath();
            typeDefinitionAssertion.createDefinitionFileAndWriteContent(absoluteDefinitionPath);
        } catch (IOException e) {
            Assertions.fail();
        }

        final String[] mandatoryParameters = {"java",
                "-cp", currentClassPaths,
//                "-agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=*:5006",
                "-Dtype.definition.path=" + typeDefinitionAssertion.getDefinitionPath().toAbsolutePath(),
                "-Doutput.file=" + outputFilePath.toAbsolutePath(),
                "-Doutput.class.name=" + inputAssertion.getType().getTypeName(),
                fileToRun};
        final ArrayList<String> parameters = new ArrayList<>();
        parameters.addAll(Arrays.asList(mandatoryParameters));
        parameters.addAll(Arrays.asList(valueParameters));
        final ProcessBuilder processBuilder = new ProcessBuilder(parameters);
        processBuilder.directory(new File(currentExecutionDirectory));
        processBuilder.inheritIO();
        return processBuilder;
    }

    private void startAndAssertProcess(final ProcessBuilder javaRuntimeProcessBuilder) throws IOException, InterruptedException {
        final Process process = javaRuntimeProcessBuilder.start();
        final int exitValue = process.waitFor();
        Assertions.assertEquals(0, exitValue);
    }

    private <T> void assertValue(final InputAssertion<T> inputAssertion, final DeserializableInputAssertion<T> typedInputAssertions) throws IOException {
        inputAssertion.assertValue(objectMapper.readValue(outputFilePath.toFile(), new TypeReference<>() {
            @Override
            public Type getType() {
                return typedInputAssertions.getType();
            }
        }));
    }

    @Test
    @Override
    public void testBoolean() throws Exception {
        final BooleanFileSystemAssertion fileSystemAssertion = new BooleanFileSystemAssertion();
        ProcessBuilder javaRuntimeProcessBuilder = createJavaRunTimeProcessBuilder(
                fileSystemAssertion.getTypeDefinitionAssertion(),
                fileSystemAssertion.getInputAssertion(),
                "true");

        startAndAssertProcess(javaRuntimeProcessBuilder);
        assertValue(fileSystemAssertion.getInputAssertion(), fileSystemAssertion.getInputAssertion());
    }

    @Test
    @Override
    public void testInteger() throws Exception {
        final IntegerFileSystemAssertion fileSystemAssertion = new IntegerFileSystemAssertion();
        ProcessBuilder javaRuntimeProcessBuilder = createJavaRunTimeProcessBuilder(
                fileSystemAssertion.getTypeDefinitionAssertion(),
                fileSystemAssertion.getInputAssertion(),
                "1");

        startAndAssertProcess(javaRuntimeProcessBuilder);
        assertValue(fileSystemAssertion.getInputAssertion(), fileSystemAssertion.getInputAssertion());
    }

    @Test
    @Override
    public void testFloatingPoint() throws Exception {
        final FloatingPointFileSystemAssertion fileSystemAssertion = new FloatingPointFileSystemAssertion();
        ProcessBuilder javaRuntimeProcessBuilder = createJavaRunTimeProcessBuilder(
                fileSystemAssertion.getTypeDefinitionAssertion(),
                fileSystemAssertion.getInputAssertion(),
                "1.1");

        startAndAssertProcess(javaRuntimeProcessBuilder);
        assertValue(fileSystemAssertion.getInputAssertion(), fileSystemAssertion.getInputAssertion());
    }

    @Test
    @Override
    public void testString() throws Exception {
        final StringFileSystemAssertion fileSystemAssertion = new StringFileSystemAssertion();
        ProcessBuilder javaRuntimeProcessBuilder = createJavaRunTimeProcessBuilder(
                fileSystemAssertion.getTypeDefinitionAssertion(),
                fileSystemAssertion.getInputAssertion(),
                createCommandLineJsonString("string"));

        startAndAssertProcess(javaRuntimeProcessBuilder);
        assertValue(fileSystemAssertion.getInputAssertion(), fileSystemAssertion.getInputAssertion());
    }

    private String createCommandLineJsonString(final String value) {
        return "\"\"\"" + value + "\"\"\"";
    }

    @Test
    @Override
    public void testEmptyMap() throws Exception {
        final EmptyMapFileSystemAssertion fileSystemAssertion = new EmptyMapFileSystemAssertion();
        ProcessBuilder javaRuntimeProcessBuilder = createJavaRunTimeProcessBuilder(
                fileSystemAssertion.getTypeDefinitionAssertion(),
                fileSystemAssertion.getInputAssertion(),
                "{}");

        startAndAssertProcess(javaRuntimeProcessBuilder);
        assertValue(fileSystemAssertion.getInputAssertion(), fileSystemAssertion.getInputAssertion());
    }

    @Test
    @Override
    public void testMapWithPrimitiveValueTypesOnly() throws Exception {
        //TODO: Add support for multiple parameters
        final var fileSystemAssertion = new MapWithPrimitiveValueTypesFileSystemAssertion();
        ProcessBuilder javaRuntimeProcessBuilder = createJavaRunTimeProcessBuilder(
                fileSystemAssertion.getTypeDefinitionAssertion(),
                fileSystemAssertion.getInputAssertion(),
                "{" +
                        createCommandLineJsonString("null") + ":null," +
                        createCommandLineJsonString("boolean") + ":true," +
                        createCommandLineJsonString("integer") + ":1," +
                        createCommandLineJsonString("float") + ":1.1," +
                        createCommandLineJsonString("string") + ":" + createCommandLineJsonString("string") +
                        "}");

        startAndAssertProcess(javaRuntimeProcessBuilder);
        assertValue(fileSystemAssertion.getInputAssertion(), fileSystemAssertion.getInputAssertion());
    }

    @Test
    @Override
    public void testMapWithCompositeValueTypesOnly() throws Exception {
        final var fileSystemAssertion = new MapWithCompositeValueTypesFileSystemAssertion();
        ProcessBuilder javaRuntimeProcessBuilder = createJavaRunTimeProcessBuilder(
                fileSystemAssertion.getTypeDefinitionAssertion(),
                fileSystemAssertion.getInputAssertion(),
                "{" +
                        createCommandLineJsonString("map") + ":{}," +
                        createCommandLineJsonString("collection") + ":[]" +
                        "}");

        startAndAssertProcess(javaRuntimeProcessBuilder);
        assertValue(fileSystemAssertion.getInputAssertion(), fileSystemAssertion.getInputAssertion());
    }

    @Test
    @Override
    public void testMapWithAllValueTypes() throws Exception {
        final var fileSystemAssertion = new MapWithAllValueTypesFileSystemAssertion();
        ProcessBuilder javaRuntimeProcessBuilder = createJavaRunTimeProcessBuilder(
                fileSystemAssertion.getTypeDefinitionAssertion(),
                fileSystemAssertion.getInputAssertion(),
                "{" +
                        createCommandLineJsonString("null") + ":null," +
                        createCommandLineJsonString("boolean") + ":true," +
                        createCommandLineJsonString("integer") + ":1," +
                        createCommandLineJsonString("float") + ":1.1," +
                        createCommandLineJsonString("string") + ":" + createCommandLineJsonString("string") +","+
                        createCommandLineJsonString("map") + ":{}," +
                        createCommandLineJsonString("collection") + ":[]" +
                        "}");

        startAndAssertProcess(javaRuntimeProcessBuilder);
        assertValue(fileSystemAssertion.getInputAssertion(), fileSystemAssertion.getInputAssertion());
    }

    @Test
    @Override
    public void testEmptyCollection() {
    }

    @Test
    @Override
    public void testNullCollection() {

    }

    @Test
    @Override
    public void testBooleanCollection() {

    }

    @Test
    @Override
    public void testIntegerCollection() {

    }

    @Test
    @Override
    public void testFloatingPointCollection() {

    }

    @Test
    @Override
    public void testStringCollection() {

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

