package io.serpentes.testing.assertions.definition.type.tree;

import io.serpentes.api.definition.trees.edges.KeyValueTypeEdge;
import io.serpentes.api.definition.trees.vertices.TypeVertex;
import io.serpentes.definition.trees.base.edges.DefaultCollectionItemTypeEdge;
import io.serpentes.definition.trees.base.edges.DefaultKeyValueTypeEdge;
import io.serpentes.definition.trees.base.vertices.branch.DefaultCollectionTypeVertex;
import io.serpentes.definition.trees.base.vertices.branch.DefaultMapTypeVertex;
import io.serpentes.definition.trees.base.vertices.terminal.*;

public class DefaultTypeVertexFactory {
    DefaultTypeVertexFactory() {
    }

    //TODO: Remove setValue calls for TypeVertex.
    public static DefaultNullTypeVertex createNullTerminalVertex() {
        final var terminalVertex = new DefaultNullTypeVertex();
        terminalVertex.setValue("null");
        return terminalVertex;
    }

    public static DefaultBooleanTypeVertex createBooleanTerminalVertex() {
        final var terminalVertex = new DefaultBooleanTypeVertex();
        terminalVertex.setValue("boolean");
        return terminalVertex;
    }

    public static DefaultIntegerTypeVertex createIntegerTerminalVertex() {
        final var terminalVertex = new DefaultIntegerTypeVertex();
        terminalVertex.setValue("integer");
        return terminalVertex;
    }

    public static DefaultFloatingPointTypeVertex createFloatingPointTerminalVertex() {
        final var terminalVertex = new DefaultFloatingPointTypeVertex();
        terminalVertex.setValue("float");
        return terminalVertex;
    }

    public static DefaultStringTypeVertex createStringTerminalVertex() {
        final var terminalVertex = new DefaultStringTypeVertex();
        terminalVertex.setValue("string");
        return terminalVertex;
    }

    public static DefaultKeyValueTypeEdge createKeyValueEdge(final String key, final TypeVertex value) {
        return new DefaultKeyValueTypeEdge(key, value);
    }

    public static DefaultMapTypeVertex createMapVertex(final KeyValueTypeEdge... keyValueEdges) {
        final var mapVertex = DefaultTypeVertexFactory.createMapVertex();
        for (var keyValueEdge : keyValueEdges) {
            mapVertex.addEdge(keyValueEdge);
        }
        return mapVertex;
    }

    public static DefaultMapTypeVertex createMapVertex() {
        return new DefaultMapTypeVertex();
    }

    public static DefaultCollectionTypeVertex createCollectionVertex(final DefaultCollectionItemTypeEdge... collectionItemEdges) {
        final var collectionVertex = DefaultTypeVertexFactory.createCollectionVertex();
        for (var collectionItemTypeEdge : collectionItemEdges) {
            collectionVertex.addEdge(collectionItemTypeEdge);
        }
        return collectionVertex;
    }

    public static DefaultCollectionTypeVertex createCollectionVertex() {
        return new DefaultCollectionTypeVertex();
    }

    public static DefaultCollectionTypeVertex createCollectionVertex(final TypeVertex... typeVertices) {
        final var collectionVertex = DefaultTypeVertexFactory.createCollectionVertex();
        for (var typeVertex : typeVertices) {
            collectionVertex.addAllowedVertexType(typeVertex);
        }
        return collectionVertex;
    }


    public static DefaultCollectionItemTypeEdge createCollectionItemEdge(final int index, final TypeVertex item) {
        return new DefaultCollectionItemTypeEdge(index, item);
    }
}
