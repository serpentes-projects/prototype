package io.serpentes.api.input.parsers;

import io.serpentes.api.annotations.input.parsers.InputParser;
import io.serpentes.api.annotations.input.parsers.InputParsersPrecedence;
import io.serpentes.api.annotations.parsers.ContentType;
import io.serpentes.api.input.trees.InputTree;
import io.serpentes.api.precedence.NameBasedPrecedence;
import jakarta.enterprise.inject.Instance;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Singleton
//TODO: Consider adding this as an interface to the api.
public class InputParsers {
    private final Set<io.serpentes.api.input.parsers.InputParser> orderedInputParsers = new LinkedHashSet<>();

    @Inject
    public InputParsers(@InputParser final Instance<io.serpentes.api.input.parsers.InputParser> inputParsers, @InputParsersPrecedence final Instance<NameBasedPrecedence> precedence) {
        //TODO: If there are no input parsers throw an exception.
        this.orderInputSourcesAccordingToPrecedence(inputParsers, precedence);
    }

    private void orderInputSourcesAccordingToPrecedence(final Instance<io.serpentes.api.input.parsers.InputParser> inputParsers, final Instance<NameBasedPrecedence> precedence) {
        for (final String namedContentType : precedence.get().getPrecedence()) {
            for (io.serpentes.api.input.parsers.InputParser inputParser : inputParsers) {
                final List<ContentType> annotations = List.of(inputParser.getClass().getAnnotationsByType(ContentType.class));
                for (ContentType contentTypeAnnotation : annotations) {
                    if (contentTypeAnnotation.value().equals(namedContentType) || List.of(contentTypeAnnotation.aliases()).contains(namedContentType)) {
                        orderedInputParsers.add(inputParser);
                    }
                }

            }
        }
    }

    public InputTree parse(String inputValue) {
        final List<io.serpentes.api.input.parsers.InputParser> inputParsers = List.of(orderedInputParsers.toArray(new io.serpentes.api.input.parsers.InputParser[]{}));
        for (io.serpentes.api.input.parsers.InputParser inputParser : inputParsers) {
            try {
                return inputParser.parse(inputValue);
            } catch (InputParsingException e) {
                // TODO: Handle this.
            }
        }
        //TODO: Handle this properly.
        return null;
    }
}
