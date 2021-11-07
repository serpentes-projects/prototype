package io.serpentes.boot.se.definition.sources;

import io.serpentes.api.annotations.definition.sources.DefinitionSourcesPrecedence;
import io.serpentes.api.annotations.definition.sources.Source;
import io.serpentes.api.precedence.ClassBasedPrecedence;
import io.serpentes.definition.sources.file_system.FileSystemDefinitionSource;

import java.lang.annotation.Annotation;
import java.util.Collections;
import java.util.LinkedHashSet;

@DefinitionSourcesPrecedence
public class DefaultDefinitionSourcesPrecedence implements ClassBasedPrecedence {
    @Override
    public Class<? extends Annotation> getAnnotation() {
        return Source.class;
    }

    @Override
    public LinkedHashSet<Class<?>> getPrecedence() {
        return new LinkedHashSet<>(Collections.singletonList(FileSystemDefinitionSource.class));
    }
}
