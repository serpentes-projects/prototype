package io.serpentes.api.definition.parsers;

import io.serpentes.api.annotations.definition.parsers.DefinitionParsersPrecedence;
import io.serpentes.api.annotations.parsers.ContentType;
import io.serpentes.api.definition.trees.TypeTree;
import io.serpentes.api.precedence.NameBasedPrecedence;
import jakarta.enterprise.inject.Instance;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Singleton
//TODO: Consider adding this as an interface to the api.
public class DefinitionParsers {
    private final Set<DefinitionParser> orderedDefinitionParsers = new LinkedHashSet<>();

    @Inject
    public DefinitionParsers(@io.serpentes.api.annotations.definition.parsers.DefinitionParser final Instance<DefinitionParser> definitionParsers, @DefinitionParsersPrecedence final Instance<NameBasedPrecedence> precedence) {
        this.orderDefinitionSourcesAccordingToPrecedence(definitionParsers, precedence);
    }

    private void orderDefinitionSourcesAccordingToPrecedence(final Instance<DefinitionParser> definitionParsers,final Instance<NameBasedPrecedence> precedence) {
        for (final var namedContentType : precedence.get().getPrecedence()) {
            for (var definitionParser : definitionParsers) {
                List<Annotation> annotations = List.of(definitionParser.getClass().getAnnotations());
                final List<ContentType> contentTypeAnnotations = annotations.stream().filter(annotation -> ContentType.class.equals(annotation.annotationType())).map(annotation -> (ContentType) annotation).collect(Collectors.toUnmodifiableList());
                for (var contentTypeAnnotation : contentTypeAnnotations) {
                    if (contentTypeAnnotation.value().equals(namedContentType) || List.of(contentTypeAnnotation.aliases()).contains(namedContentType)) {
                        orderedDefinitionParsers.add(definitionParser);
                    }
                }

            }
        }
    }

    public TypeTree parse(String content) {
        for (var definitionParser : orderedDefinitionParsers) {
            try {
                return definitionParser.parse(content);
            } catch (DefinitionParsingException e) {
                // TODO: Handle this.
            }
        }
        return typeTreeVisitor -> {
            //Do nothing.
        };
    }
}
