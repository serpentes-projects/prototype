package io.serpentes.testing.assertions.input.base.composite.collection;

import io.serpentes.api.input.trees.InputTree;
import io.serpentes.input.trees.base.DefaultInputTree;
import io.serpentes.input.trees.base.vertices.branch.DefaultCollectionInputVertex;
import io.serpentes.testing.assertions.input.content.api.Content;
import io.serpentes.testing.assertions.input.content.json.composite.collection.EmptyCollectionJsonContent;

import java.lang.reflect.Type;
import java.util.Collection;

public class EmptyCollectionInputAssertion implements CollectionInputAssertion<Object> {
    private final EmptyCollectionJsonContent content;

    public EmptyCollectionInputAssertion() {
        this(new EmptyCollectionJsonContent());
    }

    public EmptyCollectionInputAssertion(EmptyCollectionJsonContent content) {
        this.content = content;
    }

    @Override
    public Content<Collection<Object>> getContent() {
        return content;
    }

    @Override
    public Collection<Object> defaultExpectedValue() {
        return content.afterDeserializing();
    }

    @Override
    public Type getType() {
        return content.asType();
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
    public InputTree expectedInputTree() {
        return new DefaultInputTree(expectedRootInputVertex());
    }

    @Override
    public DefaultCollectionInputVertex expectedRootInputVertex() {
        return new DefaultCollectionInputVertex();
    }

    @Override
    public void assertInputTree(InputTree actualInputTree) {
        //FIXME
    }

}
