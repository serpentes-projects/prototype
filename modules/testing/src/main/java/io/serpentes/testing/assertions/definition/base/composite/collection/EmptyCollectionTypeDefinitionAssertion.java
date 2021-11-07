package io.serpentes.testing.assertions.definition.base.composite.collection;

import io.serpentes.api.definition.trees.TypeTree;
import io.serpentes.api.definition.trees.vertices.TypeVertex;
import io.serpentes.definition.trees.base.DefaultTypeTree;
import io.serpentes.definition.trees.base.vertices.branch.DefaultCollectionTypeVertex;
import io.serpentes.testing.assertions.definition.content.api.composite.CollectionDefinitionContent;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

public class EmptyCollectionTypeDefinitionAssertion implements CollectionTypeDefinitionAssertion {
    private final CollectionDefinitionContent collectionDefinitionContent;

    public EmptyCollectionTypeDefinitionAssertion(CollectionDefinitionContent collectionDefinitionContent) {
        this.collectionDefinitionContent = collectionDefinitionContent;
    }

    @Override
    public String getDefinitionContent() {
        return collectionDefinitionContent.beforeParsing();
    }

    @Override
    public TypeTree expectedTypeTree() {
        return new DefaultTypeTree(new DefaultCollectionTypeVertex());
    }

    @Override
    public TypeVertex expectedRootTypeVertex() {
        return null;
    }

    @Override
    public void assertTypeTree(TypeTree actualTypeTree) {

    }

    @Override
    public Type getType() {
        return collectionDefinitionContent.asType();
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
