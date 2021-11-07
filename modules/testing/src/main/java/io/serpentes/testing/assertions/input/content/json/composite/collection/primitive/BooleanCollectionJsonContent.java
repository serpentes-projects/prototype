package io.serpentes.testing.assertions.input.content.json.composite.collection.primitive;

import io.serpentes.testing.assertions.input.content.api.DeserializationType;
import io.serpentes.testing.assertions.input.content.api.composite.CollectionContent;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

public class BooleanCollectionJsonContent implements CollectionContent<Boolean> {
    private static final Collection<Boolean> COLLECTION;
    private static final Collection<String> STRING_COLLECTION;

    static {
        Collection<Boolean> internalCollection = new ArrayList<>();
        internalCollection.add(true);
        internalCollection.add(false);
        COLLECTION = Collections.unmodifiableCollection(internalCollection);
        STRING_COLLECTION = COLLECTION.stream().map(String::valueOf).collect(Collectors.toUnmodifiableList());
    }

    public static final String JSON = "[true,false]";

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
    public Collection<Boolean> afterDeserializing() {
        return COLLECTION;
    }

    @Override
    public Type asType() {
        return new DeserializationType<Collection<Boolean>>().getType();
    }
}
