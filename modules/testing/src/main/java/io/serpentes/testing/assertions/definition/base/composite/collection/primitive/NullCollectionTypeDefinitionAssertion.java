package io.serpentes.testing.assertions.definition.base.composite.collection.primitive;

import io.serpentes.api.definition.trees.TypeTree;
import io.serpentes.api.definition.trees.vertices.TypeVertex;
import io.serpentes.definition.trees.base.DefaultTypeTree;
import io.serpentes.testing.assertions.definition.content.api.composite.CollectionDefinitionContent;
import io.serpentes.testing.assertions.definition.type.tree.DefaultTypeVertexFactory;
import io.serpentes.testing.assertions.definition.base.composite.JSONUtils;
import io.serpentes.testing.assertions.definition.base.composite.collection.GenericBasedCollectionTypeDefinition;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

public class NullCollectionTypeDefinitionAssertion implements GenericBasedCollectionTypeDefinition<Object> {
    private static final Collection<String> stringCollection;

    static {
        Collection<Object> internalCollection = new ArrayList<>();
        internalCollection.add(null);
        stringCollection = JSONUtils.toUnmodifiableStringCollection(internalCollection);
    }

    private final CollectionDefinitionContent collectionDefinitionContent;

    public NullCollectionTypeDefinitionAssertion(CollectionDefinitionContent collectionDefinitionContent) {
        this.collectionDefinitionContent = collectionDefinitionContent;
    }

    @Override
    public Type getType() {
        return collectionDefinitionContent.asType();
    }

    @Override
    public Collection<String> primitiveValues() {
        return stringCollection;
    }

    @Override
    public Collection<String> compositeValues() {
        return new ArrayList<>();
    }

    @Override
    public Collection<String> allValues() {
        return stringCollection;
    }

    @Override
    public String getDefinitionContent() {
        return collectionDefinitionContent.beforeParsing();
    }

    @Override
    public TypeTree expectedTypeTree() {
        return new DefaultTypeTree(
                DefaultTypeVertexFactory.createCollectionVertex(
                        DefaultTypeVertexFactory.createNullTerminalVertex()
                ));
    }

    @Override
    public TypeVertex expectedRootTypeVertex() {
        return null;
    }

    @Override
    public void assertTypeTree(TypeTree actualTypeTree) {

    }
}
