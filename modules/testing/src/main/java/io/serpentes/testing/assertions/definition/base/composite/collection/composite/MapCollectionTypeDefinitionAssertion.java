package io.serpentes.testing.assertions.definition.base.composite.collection.composite;

import io.serpentes.api.definition.trees.TypeTree;
import io.serpentes.api.definition.trees.vertices.TypeVertex;
import io.serpentes.definition.trees.base.DefaultTypeTree;
import io.serpentes.testing.assertions.definition.content.api.composite.CollectionDefinitionContent;
import io.serpentes.testing.assertions.definition.type.tree.DefaultTypeVertexFactory;
import io.serpentes.testing.assertions.definition.base.composite.collection.GenericBasedCollectionTypeDefinition;
import io.serpentes.testing.pojos.ClassWithAllValueTypes;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class MapCollectionTypeDefinitionAssertion implements GenericBasedCollectionTypeDefinition<ClassWithAllValueTypes> {
    private static final Collection<String> stringCollection;

    static {
        final Collection<String> internalStringCollection = new ArrayList<>();
        internalStringCollection.add("{}");
        stringCollection = Collections.unmodifiableCollection(internalStringCollection);
    }

    private final CollectionDefinitionContent collectionDefinitionContent;

    public MapCollectionTypeDefinitionAssertion(CollectionDefinitionContent collectionDefinitionContent) {
        this.collectionDefinitionContent = collectionDefinitionContent;
    }


    @Override
    public Type getType() {
        return collectionDefinitionContent.asType();
    }

    @Override
    //TODO: Add to InputAssertion
    public Collection<String> primitiveValues() {
        return new ArrayList<>();
    }

    @Override
    public Collection<String> compositeValues() {
        return stringCollection;
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
                        DefaultTypeVertexFactory.createMapVertex()
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
