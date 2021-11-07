package io.serpentes.boot.se.input.sources;

import io.serpentes.api.annotations.input.sources.InputSourcesPrecedence;
import io.serpentes.api.precedence.ClassBasedPrecedence;
import io.serpentes.input.sources.command_line.parameters.CommandLineParametersInputSource;
import io.serpentes.input.sources.environment_variables.EnvironmentVariablesInputSource;
import io.serpentes.input.sources.file_system.implementation.FileSystemInputSource;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.LinkedHashSet;

@InputSourcesPrecedence
public class DefaultInputSourcePrecedence implements ClassBasedPrecedence {
    @Override
    public Class<? extends Annotation> getAnnotation() {
        return null;
    }

    @Override
    public LinkedHashSet<Class<?>> getPrecedence() {
        return new LinkedHashSet<>(Arrays.asList(FileSystemInputSource.class, EnvironmentVariablesInputSource.class, CommandLineParametersInputSource.class));
    }
}
