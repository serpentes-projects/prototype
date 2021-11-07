package io.serpentes.boot.se.input.sources;

import io.serpentes.api.annotations.input.sources.InputSourcesPrecedence;
import io.serpentes.api.input.SupportedInputSources;
import io.serpentes.api.input.sources.InputSource;
import io.serpentes.api.precedence.ClassBasedPrecedence;
import jakarta.enterprise.inject.Any;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class InputSources implements SupportedInputSources {
    private final LinkedHashSet<InputSource> orderedInputSources = new LinkedHashSet<>();

    @Inject
    public InputSources(@Any final Instance<InputSource> supportedInputSources, @InputSourcesPrecedence final ClassBasedPrecedence precedence) {
        this.orderInputSourcesAccordingToPrecedence(supportedInputSources, precedence);
    }

    private void orderInputSourcesAccordingToPrecedence(final Instance<InputSource> inputSources, final ClassBasedPrecedence precedence) {
        for (final Class<?> precedenceClass : precedence.getPrecedence()) {
            for (final InputSource inputSource : inputSources) {
                if (precedenceClass.equals(inputSource.getClass())) {
                    orderedInputSources.add(inputSource);
                }
            }
        }
    }

    public void addValuesToInputStore() {
        final List<InputSource> inputSources = new ArrayList<>(orderedInputSources);
        for (int i = inputSources.size() - 1; i >= 0; i--) {
            final InputSource inputSource = inputSources.get(i);
            inputSource.addValuesToInputStore();
        }
    }
}
