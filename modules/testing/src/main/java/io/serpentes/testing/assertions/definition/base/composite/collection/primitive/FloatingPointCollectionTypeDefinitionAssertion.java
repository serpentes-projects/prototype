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
import java.util.Collections;

public class FloatingPointCollectionTypeDefinitionAssertion implements GenericBasedCollectionTypeDefinition<Double> {
    private static final Collection<Double> collection;
    private static final Collection<String> stringCollection;

    static {
        final ArrayList<Double> internalCollection = new ArrayList<>();
        internalCollection.add(-1.1);
        internalCollection.add(0.0);
        internalCollection.add(1.1);
        collection = Collections.unmodifiableCollection(internalCollection);
        stringCollection = JSONUtils.toUnmodifiableStringCollection(collection);
    }

    private final CollectionDefinitionContent collectionDefinitionContent;

    public FloatingPointCollectionTypeDefinitionAssertion(CollectionDefinitionContent collectionDefinitionContent) {
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
                        DefaultTypeVertexFactory.createFloatingPointTerminalVertex()
                )
        );
    }

    @Override
    public TypeVertex expectedRootTypeVertex() {
        return null;
    }

    @Override
    public void assertTypeTree(TypeTree actualTypeTree) {

    }
}
