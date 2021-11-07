package io.serpentes.testing.assertions.input.content.json.composite.map.values;

import io.serpentes.testing.assertions.input.content.api.composite.MapContent;
import io.serpentes.testing.pojos.ClassWithAllValueTypes;

import java.lang.reflect.Type;
import java.util.*;

public class MapWithAllValueTypesJsonContent implements MapContent<ClassWithAllValueTypes> {
    static final Map<String, String> map;

    static {
        final Map<String, String> internalMap = new HashMap<>();
        internalMap.putAll(MapWithPrimitiveValueTypesJsonContent.map);
        internalMap.putAll(MapWithCompositeValueTypesJsonContent.map);
        map = Collections.unmodifiableMap(internalMap);
    }

    public static final String JSON = "{" +
            "\"null\":null," +
            "\"boolean\":true," +
            "\"integer\":1," +
            "\"float\":1.1," +
            "\"string\":\"string\"," +
            "\"collection\":[]," +
            "\"map\":{}" +
            "}";
    public static final String ESCAPED_JSON = "{" +
            "\"null\":null," +
            "\"boolean\":true," +
            "\"integer\":1," +
            "\"float\":1.1," +
            "\"string\":string," +
            "\"collection\":[]," +
            "\"map\":{}" +
            "}";

    @Override
    public Collection<String> primitivesBeforeParsing() {
        return MapWithPrimitiveValueTypesJsonContent.map.values();
    }

    @Override
    public Collection<String> compositesBeforeParsing() {
        return MapWithCompositeValueTypesJsonContent.map.values();
    }

    @Override
    public Collection<String> allBeforeParsing() {
        return map.values();
    }

    @Override
    public Collection<String> primitiveKeyNames() {
        return MapWithPrimitiveValueTypesJsonContent.map.keySet();
    }

    @Override
    public Collection<String> compositeKeyNames() {
        return MapWithCompositeValueTypesJsonContent.map.keySet();
    }

    @Override
    public Collection<String> allKeyNames() {
        return map.keySet();
    }

    @Override
    public String beforeParsing() {
        return JSON;
    }

    @Override
    public String afterParsing() {
        return ESCAPED_JSON;
    }

    @Override
    public ClassWithAllValueTypes afterDeserializing() {
        final var classWithAllValueTypes = new ClassWithAllValueTypes();
        classWithAllValueTypes.setANull(null);
        classWithAllValueTypes.setABoolean(true);
        classWithAllValueTypes.setInteger(1);
        classWithAllValueTypes.setFloatingPoint(1.1);
        classWithAllValueTypes.setString("string");
        classWithAllValueTypes.setMap(new HashMap<>());
        classWithAllValueTypes.setCollection(new ArrayList<>());
        return classWithAllValueTypes;
    }

    @Override
    public Type asType() {
        return ClassWithAllValueTypes.class;
    }
}
