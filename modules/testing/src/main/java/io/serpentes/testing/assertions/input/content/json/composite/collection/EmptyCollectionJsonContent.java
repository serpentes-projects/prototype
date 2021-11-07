package io.serpentes.testing.assertions.input.content.json.composite.collection;

import io.serpentes.testing.assertions.input.content.api.DeserializationType;
import io.serpentes.testing.assertions.input.content.api.composite.CollectionContent;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

public class EmptyCollectionJsonContent implements CollectionContent<Object> {
    public static final String JSON = "[]";

    @Override
    public Collection<String> primitivesBeforeParsing() {
        return new ArrayList<>();
    }

    @Override
    public Collection<String> compositesBeforeParsing() {
        return new ArrayList<>();
    }

    @Override
    public Collection<String> allBeforeParsing() {
        return new ArrayList<>();
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
        return new ArrayList<>();
    }

    @Override
    public Type asType() {
        return new DeserializationType<Collection<Object>>().getType();
    }
}
