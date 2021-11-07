package io.serpentes.testing.assertions.input.content.json.composite.map.value.composite;

import io.serpentes.testing.assertions.input.content.api.composite.MapContent;
import io.serpentes.testing.pojos.ClassWithCompositeValueTypes;

import java.lang.reflect.Type;
import java.util.*;

public class CollectionMapJsonContent implements MapContent<ClassWithCompositeValueTypes> {
    static final Map<String, String> map;

    static {
        map = Map.of("collection", "[]");
    }

    public static final String JSON = "{\n" +
            "\"collection\":[],\n" +
            "}";

    @Override
    public Collection<String> primitiveKeyNames() {
        return new ArrayList<>();
    }

    @Override
    public Collection<String> compositeKeyNames() {
        return map.keySet();
    }

    @Override
    public Collection<String> allKeyNames() {
        return map.keySet();
    }

    @Override
    public Collection<String> primitivesBeforeParsing() {
        return new ArrayList<>();
    }

    @Override
    public Collection<String> compositesBeforeParsing() {
        return map.values();
    }

    @Override
    public Collection<String> allBeforeParsing() {
        return map.values();
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
    public ClassWithCompositeValueTypes afterDeserializing() {
        final ClassWithCompositeValueTypes classWithCompositeValueTypes = new ClassWithCompositeValueTypes();
        classWithCompositeValueTypes.setCollection(new ArrayList<>());
        return classWithCompositeValueTypes;
    }

    @Override
    public Type asType() {
        return ClassWithCompositeValueTypes.class;
    }
}
