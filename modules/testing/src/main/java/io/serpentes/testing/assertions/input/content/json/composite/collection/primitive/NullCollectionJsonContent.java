package io.serpentes.testing.assertions.input.content.json.composite.collection.primitive;

import io.serpentes.testing.assertions.input.content.api.DeserializationType;
import io.serpentes.testing.assertions.input.content.api.composite.CollectionContent;
import io.serpentes.testing.assertions.input.content.json.composite.JSONUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class NullCollectionJsonContent implements CollectionContent<Object> {
    private static final Collection<Object> COLLECTION;
    private static final Collection<String> STRING_COLLECTION;

    static {
        Collection<Object> internalCollection = new ArrayList<>();
        internalCollection.add(null);
        COLLECTION = Collections.unmodifiableCollection(internalCollection);
        STRING_COLLECTION = JSONUtils.toUnmodifiableStringCollection(internalCollection);
    }

    public static final String JSON = "[null]";

    @Override
    public Collection<String> primitivesBeforeParsing() {
        return STRING_COLLECTION;
    }

    @Override
    public Collection<String> compositesBeforeParsing() {
        return new ArrayList<>();
    }

    @Override
    public Collection<String> allBeforeParsing() {
        return STRING_COLLECTION;
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
    public Collection<Object> afterDeserializing() {
        return COLLECTION;
    }

    @Override
    public Type asType() {
        return new DeserializationType<Collection<Object>>().getType();
    }
}
