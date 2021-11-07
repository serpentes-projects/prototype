package io.serpentes.api.definition.sources;

import io.serpentes.api.definition.trees.TypeTree;

public interface DefinitionSource {
    TypeTree createTypeTree();
}
