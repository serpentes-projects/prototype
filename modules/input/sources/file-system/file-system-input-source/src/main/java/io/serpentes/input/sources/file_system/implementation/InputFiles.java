package io.serpentes.input.sources.file_system.implementation;

import io.serpentes.input.sources.file_system.implementation.file_paths.InputDirectories;
import io.serpentes.input.sources.file_system.implementation.file_properties.InputFilesProperties;
import jakarta.inject.Inject;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class InputFiles {

    private final InputDirectories inputDirectories;
    private final InputFilesProperties inputFilesProperties;

    @Inject
    public InputFiles(final InputDirectories inputDirectories, final InputFilesProperties inputFilesProperties){
        this.inputDirectories = inputDirectories;
        this.inputFilesProperties = inputFilesProperties;
    }

    public List<Path> getPaths(){
        final var filePaths = new ArrayList<Path>();
        for (final var path : inputDirectories.getPaths()) {
            for (final var name : inputFilesProperties.getNames()) {
                 filePaths.add( Paths.get(path.toString(), name));
            }
        }
        return filePaths;
    }
}
