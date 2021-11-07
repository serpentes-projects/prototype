package io.serpentes.testing.assertions.definition.type.tree;

import io.serpentes.api.definition.trees.TypeTree;
import io.serpentes.api.definition.trees.TypeTreeVisitor;
import io.serpentes.api.definition.trees.edges.CollectionItemTypeEdge;
import io.serpentes.api.definition.trees.edges.KeyValueTypeEdge;
import io.serpentes.api.definition.trees.vertices.UnknownTypeVertex;
import io.serpentes.api.definition.trees.vertices.branch.CollectionTypeVertex;
import io.serpentes.api.definition.trees.vertices.branch.MapTypeVertex;
import io.serpentes.api.definition.trees.vertices.terminal.*;
import io.serpentes.api.definition.trees.vertices.terminal.*;
import io.serpentes.definition.trees.base.TypedRootTypeVertexRetriever;

public class TypeTreeComparator implements TypeTreeVisitor {
    private final TypedRootTypeVertexRetriever retriever = new TypedRootTypeVertexRetriever();
    private boolean equivalent = true;

    public boolean compare(final TypeTree typeTree, final TypeTree compareTo) {
        equivalent = true;
        retriever.refresh(compareTo);
        typeTree.accept(this);
        return equivalent;
    }

    @Override
    public void visit(UnknownTypeVertex unknownTypeVertex) {
    }

    @Override
    public void visit(MapTypeVertex mapTypeVertex) {
        final var compareToMap = retriever.getMapTypeVertex();
        equivalent = equivalent && mapTypeVertex.getEdges().size() == compareToMap.getEdges().size();
        for (final KeyValueTypeEdge edge : mapTypeVertex.getEdges()) {
            retriever.refresh(compareToMap.getEdge(edge.getKey()).getValue());
            edge.getValue().accept(this);
        }
    }

    @Override
    public void visit(KeyValueTypeEdge keyValueTypeEdge) {
    }

    @Override
    public void visit(CollectionTypeVertex collectionTypeVertex) {
        final var compareToCollection = retriever.getCollectionTypeVertex();
        equivalent = equivalent && collectionTypeVertex.getEdges().size() == compareToCollection.getEdges().size();
        for (CollectionItemTypeEdge edge : compareToCollection.getEdges()) {
            retriever.refresh(compareToCollection.getEdge(edge.getIndex()).getItem());
            edge.getItem().accept(this);
        }
    }

    @Override
    public void visit(CollectionItemTypeEdge collectionItemTypeEdge) {

    }

    @Override
    public void visit(NullTypeVertex nullTypeVertex) {
        final var compareTo = retriever.getNullTypeVertex();
        equivalent = equivalent && nullTypeVertex.getValue().equals(compareTo.getValue());
    }

    @Override
    public void visit(BooleanTypeVertex booleanTypeVertex) {
        final var compareTo = retriever.getBooleanTypeVertex();
        equivalent = equivalent && booleanTypeVertex.getValue().equals(compareTo.getValue());
    }

    @Override
    public void visit(IntegerTypeVertex integerTypeVertex) {
        final var compareTo = retriever.getIntegerTypeVertex();
        equivalent = equivalent && integerTypeVertex.getValue().equals(compareTo.getValue());
    }

    @Override
    public void visit(FloatingPointTypeVertex floatingPointTypeVertex) {
        final var compareTo = retriever.getFloatingPointTypeVertex();
        equivalent = equivalent && floatingPointTypeVertex.getValue().equals(compareTo.getValue());
    }

    @Override
    public void visit(StringTypeVertex stringTypeVertex) {
        final var compareTo = retriever.getStringTypeVertex();
        equivalent = equivalent && stringTypeVertex.getValue().equals(compareTo.getValue());
    }
}
