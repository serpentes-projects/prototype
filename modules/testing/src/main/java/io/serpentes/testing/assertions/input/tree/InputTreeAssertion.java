package io.serpentes.testing.assertions.input.tree;

import lombok.Data;
import lombok.experimental.Accessors;
import org.junit.jupiter.api.Assertions;

@Data
@Accessors(fluent = true, chain = true)
public class InputTreeAssertion {
    private int expectedAmountOfNullVertices;
    private int actualAmountOfNullVertices;

    private int expectedAmountOfNullInputTerminalVertices;
    private int actualAmountOfNullInputTerminalVertices;

    private int expectedAmountOfBooleanInputTerminalVertices;
    private int actualAmountOfBooleanInputTerminalVertices;

    private int expectedAmountOfIntegerInputTerminalVertices;
    private int actualAmountOfIntegerInputTerminalVertices;

    private int expectedAmountOfFloatingPointInputTerminalVertices;
    private int actualAmountOfFloatingPointInputTerminalVertices;

    private int expectedAmountOfStringInputTerminalVertices;
    private int actualAmountOfStringInputTerminalVertices;

    private int expectedAmountOfMapBranchVertices;
    private int actualAmountOfMapBranchVertices;

    private int expectedAmountOfKeyValueBranchVertices;
    private int actualAmountOfKeyValueBranchVertices;

    private int expectedAmountOfCollectionBranchVertices;
    private int actualAmountOfCollectionBranchVertices;

    private int expectedAmountOfCollectionItemBranchVertices;
    private int actualAmountOfCollectionItemBranchVertices;

    public void assertTree() {
        Assertions.assertEquals(expectedAmountOfNullVertices, actualAmountOfNullVertices);
        Assertions.assertEquals(expectedAmountOfNullInputTerminalVertices, actualAmountOfNullInputTerminalVertices);
        Assertions.assertEquals(expectedAmountOfBooleanInputTerminalVertices, actualAmountOfBooleanInputTerminalVertices);
        Assertions.assertEquals(expectedAmountOfIntegerInputTerminalVertices, actualAmountOfIntegerInputTerminalVertices);
        Assertions.assertEquals(expectedAmountOfFloatingPointInputTerminalVertices, actualAmountOfFloatingPointInputTerminalVertices);
        Assertions.assertEquals(expectedAmountOfStringInputTerminalVertices, actualAmountOfStringInputTerminalVertices);
        Assertions.assertEquals(expectedAmountOfMapBranchVertices, actualAmountOfMapBranchVertices);
        Assertions.assertEquals(expectedAmountOfKeyValueBranchVertices, actualAmountOfKeyValueBranchVertices);
        Assertions.assertEquals(expectedAmountOfCollectionBranchVertices, actualAmountOfCollectionBranchVertices);
        Assertions.assertEquals(expectedAmountOfCollectionItemBranchVertices, actualAmountOfCollectionItemBranchVertices);
    }
}
