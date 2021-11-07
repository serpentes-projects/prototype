package io.serpentes.testing.assertions.input.content.json.composite.collection.primitive;

import io.serpentes.testing.assertions.input.content.api.DeserializationType;
import io.serpentes.testing.assertions.input.content.api.composite.CollectionContent;
import io.serpentes.testing.assertions.input.content.json.composite.JSONUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class FloatingPointCollectionJsonContent implements CollectionContent<Double> {
    private static final Collection<Double> COLLECTION;
    private static final Collection<String> STRING_COLLECTION;

    static {
        final ArrayList<Double> internalCollection = new ArrayList<>();
        internalCollection.add(-1.1);
        internalCollection.add(0.0);
        internalCollection.add(1.1);
        COLLECTION = Collections.unmodifiableCollection(internalCollection);
        STRING_COLLECTION = JSONUtils.toUnmodifiableStringCollection(COLLECTION);
    }

    public static final String JSON = "[-1.1,0.0,1.1]";

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
    public Collection<Double> afterDeserializing() {
        return COLLECTION;
    }

    @Override
    public Type asType() {
        return new DeserializationType<Collection<Double>>().getType();
    }
}
