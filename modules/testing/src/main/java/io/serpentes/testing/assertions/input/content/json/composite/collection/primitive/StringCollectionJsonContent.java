package io.serpentes.testing.assertions.input.content.json.composite.collection.primitive;

import io.serpentes.testing.assertions.input.content.api.DeserializationType;
import io.serpentes.testing.assertions.input.content.api.composite.CollectionContent;
import io.serpentes.testing.assertions.input.content.json.composite.JSONUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class StringCollectionJsonContent implements CollectionContent<String> {
    public static final String JSON = "[\"c\",\"string\"]";
    private static final Collection<String> COLLECTION;
    private static final Collection<String> UNESCAPED_COLLECTION;

    static {
        final Collection<String> internalCollection = new ArrayList<>();
        internalCollection.add("c");
        internalCollection.add("string");
        COLLECTION = Collections.unmodifiableCollection(internalCollection);
        UNESCAPED_COLLECTION = JSONUtils.wrapInQuotationMarks(COLLECTION);
    }

    @Override
    public String beforeParsing() {
        return JSON;
    }

    @Override
    public String afterParsing() {
        return "[c,string]";
    }

    @Override
    public Collection<String> afterDeserializing() {
        return COLLECTION;
    }

    @Override
    public Type asType() {
        return new DeserializationType<Collection<String>>().getType();
    }

    @Override
    public Collection<String> primitivesBeforeParsing() {
        return COLLECTION;
    }

    @Override
    public Collection<String> compositesBeforeParsing() {
        return new ArrayList<>();
    }

    @Override
    public Collection<String> allBeforeParsing() {
        return COLLECTION;
    }
}
