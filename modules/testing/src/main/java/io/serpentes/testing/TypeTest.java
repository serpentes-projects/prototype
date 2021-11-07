package io.serpentes.testing;

public interface TypeTest {
    void testEmpty() throws Exception;

    void testNull() throws Exception;

    void testBoolean() throws Exception;

    void testInteger() throws Exception;

    void testFloatingPoint() throws Exception;

    void testString() throws Exception;

    void testEmptyMap() throws Exception;

    void testMapWithPrimitiveValueTypesOnly() throws Exception;

    void testMapWithCompositeValueTypesOnly() throws Exception;

    void testMapWithAllValueTypes() throws Exception;

    void testEmptyCollection() throws Exception;

    void testNullCollection() throws Exception;

    void testBooleanCollection() throws Exception;

    void testIntegerCollection() throws Exception;

    void testFloatingPointCollection() throws Exception;

    void testStringCollection() throws Exception;

    void testMapCollection() throws Exception;

    void testNestedCollection() throws Exception;
}
