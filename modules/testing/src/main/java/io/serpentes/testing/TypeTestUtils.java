package io.serpentes.testing;

import io.serpentes.testing.pojos.ClassWithAllValueTypes;
import org.junit.jupiter.api.Assertions;

public class TypeTestUtils {
    private TypeTestUtils() {
    }

    public static void assertObject(ClassWithAllValueTypes classWithAllValueTypes) {
        Assertions.assertNotNull(classWithAllValueTypes);
        Assertions.assertTrue(classWithAllValueTypes.getABoolean());
        Assertions.assertEquals(1, classWithAllValueTypes.getInteger());
        Assertions.assertNull(classWithAllValueTypes.getANull());
        Assertions.assertEquals(1.1, classWithAllValueTypes.getFloatingPoint());
        Assertions.assertEquals("string", classWithAllValueTypes.getString());
        Assertions.assertEquals(0, classWithAllValueTypes.getMap().size());
        Assertions.assertEquals(0, classWithAllValueTypes.getCollection().size());
    }
}
