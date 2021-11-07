package io.serpentes.input.sources.file_system.implementation.file_properties;

import io.serpentes.api.precedence.NameBasedPrecedence;
import io.serpentes.input.sources.file_system.annotations.file_properties.DefaultInputFileProperties;
import io.serpentes.input.sources.file_system.annotations.file_properties.InputFilePropertiesPrecedence;
import io.serpentes.input.sources.file_system.annotations.file_properties.SupportedInputFileProperties;
import io.serpentes.input.sources.file_system.api.file_properties.InputFileProperties;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class InputFilesProperties {
    private final InputFileProperties defaultFileProperties;
    private final Instance<Set<InputFileProperties>> allSupportedFileProperties;
    private final NameBasedPrecedence precedence;

    @Inject
    public InputFilesProperties(@DefaultInputFileProperties InputFileProperties defaultFileProperties,
                                @SupportedInputFileProperties Instance<Set<InputFileProperties>> allSupportedFileProperties,
                                @InputFilePropertiesPrecedence NameBasedPrecedence precedence) {
        this.defaultFileProperties = defaultFileProperties;
        this.allSupportedFileProperties = allSupportedFileProperties;
        this.precedence = precedence;
    }

    public List<String> getNames() {
        final var fileNames = new ArrayList<String>();
        fileNames.add(defaultFileProperties.getName());
        final var allFileProperties = new ArrayList<InputFileProperties>();
        for (final Set<InputFileProperties> supportedFileProperties : allSupportedFileProperties) {
            allFileProperties.addAll(supportedFileProperties);
        }

        final var nameBasedPrecedence = new ArrayList<>(this.precedence.getPrecedence());
        for (int i = nameBasedPrecedence.size() - 1; i >= 0; i--) {
            final var name = nameBasedPrecedence.get(i);
            for (final var inputFileProperties : allFileProperties) {
                if (name.equals(inputFileProperties.getName())) {
                    fileNames.add(i, inputFileProperties.getName());
                }
            }
        }

        return fileNames;
    }
}
