package io.serpentes.testing.assertions.input.base;

import io.serpentes.api.input.trees.edges.KeyValueInputEdge;
import io.serpentes.api.input.trees.vertices.InputVertex;
import io.serpentes.input.trees.base.edges.DefaultCollectionItemInputEdge;
import io.serpentes.input.trees.base.edges.DefaultKeyValueInputEdge;
import io.serpentes.input.trees.base.vertices.branch.DefaultCollectionInputVertex;
import io.serpentes.input.trees.base.vertices.branch.DefaultMapInputVertex;
import io.serpentes.input.trees.base.vertices.terminal.*;

public class DefaultInputVertexFactory {
    private DefaultInputVertexFactory() {
    }

    public static DefaultNullInputTerminalVertex createNullTerminalVertex() {
        final var terminalVertex = new DefaultNullInputTerminalVertex();
        terminalVertex.setValue("null");
        return terminalVertex;
    }

    public static DefaultBooleanInputTerminalVertex createBooleanTerminalVertex(final String value) {
        final var terminalVertex = new DefaultBooleanInputTerminalVertex();
        terminalVertex.setValue(value);
        return terminalVertex;
    }

    public static DefaultIntegerInputTerminalVertex createIntegerTerminalVertex(String value) {
        final var terminalVertex = new DefaultIntegerInputTerminalVertex();
        terminalVertex.setValue(value);
        return terminalVertex;
    }

    public static DefaultFloatingPointInputTerminalVertex createFloatingPointTerminalVertex(String value) {
        final var terminalVertex = new DefaultFloatingPointInputTerminalVertex();
        terminalVertex.setValue(value);
        return terminalVertex;
    }

    public static DefaultStringInputTerminalVertex createStringTerminalVertex(final String value) {
        final var terminalVertex = new DefaultStringInputTerminalVertex();
        terminalVertex.setValue(value);
        return terminalVertex;
    }

    public static DefaultKeyValueInputEdge createKeyValueEdge(final String key, final InputVertex value) {
        return new DefaultKeyValueInputEdge(key, value);
    }

    public static DefaultMapInputVertex createMapVertex(final KeyValueInputEdge... keyValueEdges) {
        final var mapInputVertex = DefaultInputVertexFactory.createMapVertex();
        for (KeyValueInputEdge keyValueEdge : keyValueEdges) {
            mapInputVertex.addEdge(keyValueEdge);
        }
        return mapInputVertex;
    }

    public static DefaultMapInputVertex createMapVertex() {
        return new DefaultMapInputVertex();
    }

    public static DefaultCollectionInputVertex createCollectionVertex(final DefaultCollectionItemInputEdge... collectionItemEdges) {
        final var collectionVertex = DefaultInputVertexFactory.createCollectionVertex();
        for (var collectionItemInputEdge : collectionItemEdges) {
            collectionVertex.addEdge(collectionItemInputEdge);
        }
        return collectionVertex;
    }

    public static DefaultCollectionInputVertex createCollectionVertex() {
        return new DefaultCollectionInputVertex();
    }

    public static DefaultCollectionInputVertex createCollectionVertex(final InputVertex... inputVertices) {
        final var collectionVertex = DefaultInputVertexFactory.createCollectionVertex();
        for (int i = 0; i < inputVertices.length; i++) {
            final var inputVertex = inputVertices[i];
            collectionVertex.addEdge(DefaultInputVertexFactory.createCollectionItemEdge(i, inputVertex));
        }
        return collectionVertex;
    }


    public static DefaultCollectionItemInputEdge createCollectionItemEdge(final int index, final InputVertex item) {
        return new DefaultCollectionItemInputEdge(index, item);
    }
}
