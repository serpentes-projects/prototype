package io.serpentes.api.definition.parsers;

import io.serpentes.api.definition.trees.TypeTree;

public interface DefinitionParser {
    TypeTree parse(String definitionContent) throws DefinitionParsingException;
}
