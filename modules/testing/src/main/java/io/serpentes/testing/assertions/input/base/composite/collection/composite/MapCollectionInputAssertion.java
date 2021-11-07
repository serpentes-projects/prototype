package io.serpentes.testing.assertions.input.base.composite.collection.composite;

import io.serpentes.api.input.trees.InputTree;
import io.serpentes.api.input.trees.vertices.branch.CollectionInputVertex;
import io.serpentes.input.trees.base.DefaultInputTree;
import io.serpentes.testing.assertions.input.base.DefaultInputVertexFactory;
import io.serpentes.testing.assertions.input.base.composite.AssertionUtils;
import io.serpentes.testing.assertions.input.base.composite.collection.CollectionInputAssertion;
import io.serpentes.testing.assertions.input.content.api.composite.CollectionContent;
import io.serpentes.testing.pojos.ClassWithAllValueTypes;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.stream.Collectors;

public class MapCollectionInputAssertion implements CollectionInputAssertion<ClassWithAllValueTypes> {
    private final CollectionContent<ClassWithAllValueTypes> content;

    public MapCollectionInputAssertion(CollectionContent<ClassWithAllValueTypes> content) {
        this.content = content;
    }

    @Override
    public Type getType() {
        return content.asType();
    }

    @Override
    public CollectionContent<ClassWithAllValueTypes> getContent() {
        return content;
    }

    @Override
    public Collection<ClassWithAllValueTypes> defaultExpectedValue() {
        return content.afterDeserializing();
    }

    @Override
    public Collection<String> primitiveValues() {
        return content.primitivesBeforeParsing();
    }

    @Override
    public Collection<String> compositeValues() {
        return content.compositesBeforeParsing();
    }

    @Override
    public Collection<String> allValues() {
        return content.allBeforeParsing();
    }

    @Override
    public void assertValue(final Collection<ClassWithAllValueTypes> value) {
        AssertionUtils.assertEquals(content.afterDeserializing(), value);
    }

    @Override
    public InputTree expectedInputTree() {
        return new DefaultInputTree(expectedRootInputVertex());
    }

    @Override
    public CollectionInputVertex expectedRootInputVertex() {
        return DefaultInputVertexFactory.createCollectionVertex(
                content.allBeforeParsing().stream()
                        .map(value -> new DefaultInputTree(DefaultInputVertexFactory.createMapVertex()))
                        .collect(Collectors.toUnmodifiableList())
                        .toArray(new DefaultInputTree[]{})
        );
    }

    public void assertInputTree(InputTree actualInputTree) {
        //FIXME
    }
}
