package io.serpentes.examples.input.sources.custom;

import io.serpentes.examples.input.sources.custom.api.DefaultURL;
import jakarta.enterprise.inject.Produces;

import java.net.MalformedURLException;
import java.net.URL;

public class DefaultURLProducer {
    @Produces
    @DefaultURL
    public URL produce() throws MalformedURLException {
        return new URL("https://raw.githubusercontent.com/lodash/lodash/master/package.json");
    }
}
