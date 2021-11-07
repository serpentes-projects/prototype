package io.serpentes.testing.assertions.input.content.json.composite.collection.composite;

import io.serpentes.testing.assertions.input.content.api.DeserializationType;
import io.serpentes.testing.assertions.input.content.api.composite.CollectionContent;
import io.serpentes.testing.assertions.input.content.json.composite.map.EmptyMapJsonContent;
import io.serpentes.testing.pojos.ClassWithAllValueTypes;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class MapCollectionJsonContent implements CollectionContent<ClassWithAllValueTypes> {
    //TODO: Change this to AllValueTypes
    private static final EmptyMapJsonContent EMPTY_MAP_JSON_CONTENT = new EmptyMapJsonContent();
    private static final Collection<ClassWithAllValueTypes> COLLECTION;
    private static final Collection<String> STRING_COLLECTION;

    static {
        final Collection<ClassWithAllValueTypes> internalCollection = new ArrayList<>();
        internalCollection.add(EMPTY_MAP_JSON_CONTENT.afterDeserializing());
        COLLECTION = Collections.unmodifiableCollection(internalCollection);
        final Collection<String> internalStringCollection = new ArrayList<>();
        internalStringCollection.add("{}");
        STRING_COLLECTION = Collections.unmodifiableCollection(internalStringCollection);
    }

    public static final String JSON = "[{}]";

    @Override
    public Collection<String> primitivesBeforeParsing() {
        return new ArrayList<>();
    }

    @Override
    public Collection<String> compositesBeforeParsing() {
        return STRING_COLLECTION;
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
    public Collection<ClassWithAllValueTypes> afterDeserializing() {
        return COLLECTION;
    }

    @Override
    public Type asType() {
        return new DeserializationType<Collection<ClassWithAllValueTypes>>().getType();
    }
}
