package io.serpentes.testing.assertions.definition.base.composite.map;

import io.serpentes.api.definition.trees.TypeTree;
import io.serpentes.api.definition.trees.vertices.TypeVertex;
import io.serpentes.definition.trees.base.DefaultTypeTree;
import io.serpentes.definition.trees.base.vertices.branch.DefaultMapTypeVertex;
import io.serpentes.testing.assertions.definition.content.api.composite.MapDefinitionContent;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

public class EmptyMapTypeDefinitionAssertion implements MapTypeDefinitionAssertion {
    private final MapDefinitionContent mapDefinitionContent;

    public EmptyMapTypeDefinitionAssertion(final MapDefinitionContent mapDefinitionContent) {
        this.mapDefinitionContent = mapDefinitionContent;
    }

    @Override
    public Type getType() {
        return mapDefinitionContent.asType();
    }

    @Override
    public String getDefinitionContent() {
        return mapDefinitionContent.beforeParsing();
    }

    @Override
    public TypeTree expectedTypeTree() {
        return new DefaultTypeTree(new DefaultMapTypeVertex());
    }

    @Override
    public TypeVertex expectedRootTypeVertex() {
        return null;
    }

    @Override
    public void assertTypeTree(TypeTree actualTypeTree) {

    }

    @Override
    public Collection<String> primitiveKeyNames() {
        return new ArrayList<>();
    }

    @Override
    public Collection<String> compositeKeyNames() {
        return new ArrayList<>();
    }

    @Override
    public Collection<String> allKeyNames() {
        return new ArrayList<>();
    }

    @Override
    public Collection<String> primitiveValues() {
        return new ArrayList<>();
    }

    @Override
    public Collection<String> compositeValues() {
        return new ArrayList<>();
    }

    @Override
    public Collection<String> allValues() {
        return new ArrayList<>();
    }
}
