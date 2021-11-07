package io.serpentes.examples.input.sources.custom;

import io.serpentes.examples.input.sources.custom.api.DefaultURL;
import jakarta.inject.Inject;

import java.net.URL;
import java.util.List;

public class URLs {
    private final URL defaultUrl;

    @Inject
    public URLs(@DefaultURL URL defaultUrl){

        this.defaultUrl = defaultUrl;
    }

    List<URL> getURLs(){
       return List.of(defaultUrl);
    }
}
