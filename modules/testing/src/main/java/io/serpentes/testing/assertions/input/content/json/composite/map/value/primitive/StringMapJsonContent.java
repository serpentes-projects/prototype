package io.serpentes.testing.assertions.input.content.json.composite.map.value.primitive;

import io.serpentes.testing.assertions.input.content.api.composite.MapContent;
import io.serpentes.testing.pojos.ClassWithPrimitiveValueTypes;

import java.lang.reflect.Type;
import java.util.*;

public class StringMapJsonContent implements MapContent<ClassWithPrimitiveValueTypes> {
    static final Map<String, String> map;

    static {
        map = Map.of("string", "string");
    }

    public static final String JSON = "{\n" +
            "\"string\":\"string\"\n" +
            "}";
    public static final String ESCAPED_JSON = "{\n" +
            "\"string\":string\n" +
            "}";

    @Override
    public Collection<String> primitivesBeforeParsing() {
        return map.values();
    }

    @Override
    public Collection<String> compositesBeforeParsing() {
        return new ArrayList<>();
    }

    @Override
    public Collection<String> allBeforeParsing() {
        return map.values();
    }

    @Override
    public Collection<String> primitiveKeyNames() {
        return map.keySet();
    }

    @Override
    public Collection<String> compositeKeyNames() {
        return new ArrayList<>();
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
    public ClassWithPrimitiveValueTypes afterDeserializing() {
        final ClassWithPrimitiveValueTypes classWithPrimitiveValueTypes = new ClassWithPrimitiveValueTypes();
        classWithPrimitiveValueTypes.setANull(null);
        classWithPrimitiveValueTypes.setABoolean(true);
        classWithPrimitiveValueTypes.setInteger(1);
        classWithPrimitiveValueTypes.setFloatingPoint(1.1);
        classWithPrimitiveValueTypes.setString("string");
        return classWithPrimitiveValueTypes;
    }

    @Override
    public Type asType() {
        return ClassWithPrimitiveValueTypes.class;
    }
}
