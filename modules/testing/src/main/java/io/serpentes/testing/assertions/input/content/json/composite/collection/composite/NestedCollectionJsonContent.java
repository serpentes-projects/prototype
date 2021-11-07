package io.serpentes.testing.assertions.input.content.json.composite.collection.composite;

import io.serpentes.testing.assertions.input.content.api.DeserializationType;
import io.serpentes.testing.assertions.input.content.api.composite.CollectionContent;
import io.serpentes.testing.assertions.input.content.json.composite.JSONUtils;
import io.serpentes.testing.assertions.input.content.json.composite.collection.EmptyCollectionJsonContent;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class NestedCollectionJsonContent implements CollectionContent<Collection<?>> {
    public static final String JSON = "[[]]";
    private static final EmptyCollectionJsonContent EMPTY_COLLECTION_JSON_CONTENT = new EmptyCollectionJsonContent();
    private static final Collection<Collection<?>> collection;
    private static final Collection<String> stringCollection;

    static {
        final Collection<Collection<?>> internalCollection = new ArrayList<>();
        internalCollection.add(EMPTY_COLLECTION_JSON_CONTENT.afterDeserializing());
        collection = Collections.unmodifiableCollection(internalCollection);
        final Collection<String> internalStringCollection = new ArrayList<>();
        internalStringCollection.add(JSONUtils.wrapInSquareBrackets(""));
        stringCollection = Collections.unmodifiableCollection(internalStringCollection);
    }

    @Override
    public Collection<String> primitivesBeforeParsing() {
        return new ArrayList<>();
    }

    @Override
    public Collection<String> compositesBeforeParsing() {
        return stringCollection;
    }

    @Override
    public Collection<String> allBeforeParsing() {
        return stringCollection;
    }

    @Override
    public String beforeParsing() {
        return JSON;
    }

    @Override
    public String afterParsing() {
        return JSON;
    }

    @Override
    public Collection<Collection<?>> afterDeserializing() {
        return collection;
    }

    @Override
    public Type asType() {
        return new DeserializationType<Collection<Collection<?>>>().getType();
    }
}
