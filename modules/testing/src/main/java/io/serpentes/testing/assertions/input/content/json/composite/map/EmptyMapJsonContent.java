package io.serpentes.testing.assertions.input.content.json.composite.map;

import io.serpentes.testing.assertions.input.content.api.composite.MapContent;
import io.serpentes.testing.pojos.ClassWithAllValueTypes;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

public class EmptyMapJsonContent implements MapContent<ClassWithAllValueTypes> {
    public static final String JSON = "{}";

    @Override
    public Collection<String> primitiveKeyNames() {
        return new ArrayList<>();
    }

    @Override
    public Collection<String> compositeKeyNames() {
        return new ArrayList<>();
    }

    @Override
    public Collection<String> allKeyNames() {
        return new ArrayList<>();
    }

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
    public ClassWithAllValueTypes afterDeserializing() {
        return new ClassWithAllValueTypes();
    }

    @Override
    public Type asType() {
        return ClassWithAllValueTypes.class;
    }
}
