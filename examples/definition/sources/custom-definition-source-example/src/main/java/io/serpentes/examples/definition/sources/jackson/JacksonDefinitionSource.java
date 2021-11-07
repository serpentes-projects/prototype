package io.serpentes.examples.definition.sources.jackson;

import io.serpentes.api.definition.sources.DefinitionSource;
import io.serpentes.api.definition.trees.TypeTree;
import jakarta.inject.Inject;

public class JacksonDefinitionSource implements DefinitionSource {
    private final PojoParser pojoParser;

    @Inject
    public JacksonDefinitionSource(final PojoParser pojoParser){
        this.pojoParser = pojoParser;
    }

    @Override
    public TypeTree createTypeTree() {
        return pojoParser.buildTree();
    }
}
