package io.serpentes.testing.assertions.definition.type.tree;

import lombok.Data;
import lombok.experimental.Accessors;
import org.junit.jupiter.api.Assertions;

@Data
@Accessors(fluent = true, chain = true)
public class TypeTreeAssertion {
    private int expectedAmountOfUnknownVertices;
    private int actualAmountOfUnknownVertices;

    private int expectedAmountOfNullTypeTerminalVertices;
    private int actualAmountOfNullTypeTerminalVertices;

    private int expectedAmountOfBooleanTypeTerminalVertices;
    private int actualAmountOfBooleanTypeTerminalVertices;

    private int expectedAmountOfIntegerTypeTerminalVertices;
    private int actualAmountOfIntegerTypeTerminalVertices;

    private int expectedAmountOfFloatingPointTypeTerminalVertices;
    private int actualAmountOfFloatingPointTypeTerminalVertices;

    private int expectedAmountOfStringTypeTerminalVertices;
    private int actualAmountOfStringTypeTerminalVertices;

    private int expectedAmountOfMapTypeVertices;
    private int actualAmountOfMapTypeVertices;

    private int expectedAmountOfKeyValueEdges;
    private int actualAmountOfKeyValueEdges;

    private int expectedAmountOfCollectionTypeVertices;
    private int actualAmountOfCollectionTypeVertices;

    private int expectedAmountOfAllowedTypes;
    private int actualAmountOfAllowedTypes;

    private int expectedAmountOfCollectionItemEdges;
    private int actualAmountOfCollectionItemEdges;

    public void assertTree() {
        Assertions.assertEquals(expectedAmountOfUnknownVertices, actualAmountOfUnknownVertices);
        Assertions.assertEquals(expectedAmountOfNullTypeTerminalVertices, actualAmountOfNullTypeTerminalVertices);
        Assertions.assertEquals(expectedAmountOfBooleanTypeTerminalVertices, actualAmountOfBooleanTypeTerminalVertices);
        Assertions.assertEquals(expectedAmountOfIntegerTypeTerminalVertices, actualAmountOfIntegerTypeTerminalVertices);
        Assertions.assertEquals(expectedAmountOfFloatingPointTypeTerminalVertices, actualAmountOfFloatingPointTypeTerminalVertices);
        Assertions.assertEquals(expectedAmountOfStringTypeTerminalVertices, actualAmountOfStringTypeTerminalVertices);
        Assertions.assertEquals(expectedAmountOfMapTypeVertices, actualAmountOfMapTypeVertices);
        Assertions.assertEquals(expectedAmountOfKeyValueEdges, actualAmountOfKeyValueEdges);
        Assertions.assertEquals(expectedAmountOfCollectionTypeVertices, actualAmountOfCollectionTypeVertices);
        Assertions.assertEquals(expectedAmountOfAllowedTypes, actualAmountOfAllowedTypes);
        Assertions.assertEquals(expectedAmountOfCollectionItemEdges, actualAmountOfCollectionItemEdges);
    }
}
