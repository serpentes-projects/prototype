package io.serpentes.testing.cdi.extentions.definition.type.tree;

import io.serpentes.api.definition.sources.DefinitionSource;
import io.serpentes.api.definition.trees.TypeTree;

public class TypeTreeDefinitionSource implements DefinitionSource {
    private final TypeTree typeTree;

    public TypeTreeDefinitionSource(final TypeTree typeTree) {
        this.typeTree = typeTree;
    }

    @Override
    public TypeTree createTypeTree() {
        return typeTree;
    }
}

