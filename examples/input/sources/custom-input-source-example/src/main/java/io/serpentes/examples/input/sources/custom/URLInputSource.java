package io.serpentes.examples.input.sources.custom;

import io.serpentes.api.input.sources.InputSource;
import jakarta.inject.Inject;

public class URLInputSource implements InputSource {
    private final URLStoreClerk urlStoreClerk;

    @Inject
    public URLInputSource(final URLStoreClerk urlStoreClerk) {
        this.urlStoreClerk = urlStoreClerk;
    }

    @Override
    public void addValuesToInputStore() {
        urlStoreClerk.stockStore();
    }
}
