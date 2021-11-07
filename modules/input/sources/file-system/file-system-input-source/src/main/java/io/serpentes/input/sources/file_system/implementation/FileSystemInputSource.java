package io.serpentes.input.sources.file_system.implementation;

import io.serpentes.api.input.sources.InputSource;
import jakarta.inject.Inject;

public class FileSystemInputSource implements InputSource {
    private final FileSystemStoreClerk fileSystemStoreClerk;

    @Inject
    public FileSystemInputSource(final FileSystemStoreClerk fileSystemStoreClerk){
        this.fileSystemStoreClerk = fileSystemStoreClerk;
    }

    @Override
    public void addValuesToInputStore() {
        fileSystemStoreClerk.stockStore();
    }
}
