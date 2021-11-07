package io.serpentes.testing.assertions.input.base.composite.collection.primitive;

import io.serpentes.api.input.trees.InputTree;
import io.serpentes.input.trees.base.DefaultInputTree;
import io.serpentes.input.trees.base.vertices.branch.DefaultCollectionInputVertex;
import io.serpentes.testing.assertions.input.base.DefaultInputVertexFactory;
import io.serpentes.testing.assertions.input.base.composite.AssertionUtils;
import io.serpentes.testing.assertions.input.base.composite.collection.CollectionInputAssertion;
import io.serpentes.testing.assertions.input.content.api.Content;
import io.serpentes.testing.assertions.input.content.api.composite.CollectionContent;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.stream.Collectors;

public class IntegerCollectionInputAssertion implements CollectionInputAssertion<Integer> {
    private final CollectionContent<Integer> content;

    public IntegerCollectionInputAssertion(CollectionContent<Integer> content) {
        this.content = content;
    }

    @Override
    public Type getType() {
        return content.asType();
    }

    @Override
    public Content<Collection<Integer>> getContent() {
        return content;
    }

    @Override
    public Collection<Integer> defaultExpectedValue() {
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
    public void assertValue(final Collection<Integer> value) {
        AssertionUtils.assertEquals(content.afterDeserializing(), value);
    }

    @Override
    public InputTree expectedInputTree() {
        return new DefaultInputTree(expectedRootInputVertex());
    }

    @Override
    public DefaultCollectionInputVertex expectedRootInputVertex() {
        return DefaultInputVertexFactory.createCollectionVertex(
                content.allBeforeParsing().stream()
                        .map(value -> new DefaultInputTree(DefaultInputVertexFactory.createIntegerTerminalVertex(value)))
                        .collect(Collectors.toUnmodifiableList())
                        .toArray(new DefaultInputTree[]{})
        );
    }

    @Override
    public void assertInputTree(InputTree actualInputTree) {
        //FIXME
    }
}
