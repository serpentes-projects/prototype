package io.serpentes.testing.assertions.input.content.json.composite.collection.primitive;

import io.serpentes.testing.assertions.input.content.api.DeserializationType;
import io.serpentes.testing.assertions.input.content.api.composite.CollectionContent;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

public class IntegerCollectionJsonContent implements CollectionContent<Integer> {
    private static final Collection<Integer> COLLECTION;
    private static final Collection<String> STRING_COLLECTION;

    static {
        Collection<Integer> internalCollection = new ArrayList<>();
        internalCollection.add(-1);
        internalCollection.add(0);
        internalCollection.add(1);
        COLLECTION = Collections.unmodifiableCollection(internalCollection);
        STRING_COLLECTION = COLLECTION.stream().map(String::valueOf).collect(Collectors.toUnmodifiableList());
    }

    public static final String JSON = "[-1,0,1]";

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
    public Collection<Integer> afterDeserializing() {
        return COLLECTION;
    }

    @Override
    public Type asType() {
        return new DeserializationType<Collection<Integer>>().getType();
    }
}
