package io.serpentes.testing.assertions.definition.type.tree;

import io.serpentes.api.definition.trees.TypeTreeVisitor;
import io.serpentes.api.definition.trees.edges.CollectionItemTypeEdge;
import io.serpentes.api.definition.trees.edges.KeyValueTypeEdge;
import io.serpentes.api.definition.trees.vertices.UnknownTypeVertex;
import io.serpentes.api.definition.trees.vertices.branch.CollectionTypeVertex;
import io.serpentes.api.definition.trees.vertices.branch.MapTypeVertex;
import io.serpentes.api.definition.trees.vertices.terminal.*;
import io.serpentes.api.definition.trees.vertices.terminal.*;

public class TypeTreeVertexCounter implements TypeTreeVisitor {
    private final TypeTreeAssertion typeTreeAssertion;

    public TypeTreeVertexCounter(TypeTreeAssertion typeTreeAssertion) {
        this.typeTreeAssertion = typeTreeAssertion;
    }

    @Override
    public void visit(UnknownTypeVertex unknownTypeVertex) {
        typeTreeAssertion.actualAmountOfUnknownVertices(increment(typeTreeAssertion.actualAmountOfUnknownVertices()));
    }

    private int increment(int toIncrement) {
        return ++toIncrement;
    }

    @Override
    public void visit(MapTypeVertex mapTypeVertex) {
        typeTreeAssertion.actualAmountOfMapTypeVertices(increment(typeTreeAssertion.actualAmountOfMapTypeVertices()));
        mapTypeVertex.getEdges().forEach(keyValueTypeEdge -> keyValueTypeEdge.accept(this));
    }

    @Override
    public void visit(KeyValueTypeEdge keyValueTypeEdge) {
        typeTreeAssertion.actualAmountOfKeyValueEdges(increment(typeTreeAssertion.actualAmountOfKeyValueEdges()));
        keyValueTypeEdge.getValue().accept(this);
    }

    @Override
    public void visit(CollectionTypeVertex collectionTypeVertex) {
        typeTreeAssertion.actualAmountOfCollectionTypeVertices(increment(typeTreeAssertion.actualAmountOfCollectionTypeVertices()));
        typeTreeAssertion.actualAmountOfAllowedTypes(typeTreeAssertion.actualAmountOfAllowedTypes() + collectionTypeVertex.getAllowedVertexTypes().size());
        collectionTypeVertex.getEdges().forEach(collectionItemTypeEdge -> collectionItemTypeEdge.accept(this));
    }

    @Override
    public void visit(CollectionItemTypeEdge collectionItemTypeEdge) {
        typeTreeAssertion.actualAmountOfCollectionItemEdges(increment(typeTreeAssertion.expectedAmountOfCollectionItemEdges()));
        collectionItemTypeEdge.getItem().accept(this);
    }

    @Override
    public void visit(NullTypeVertex nullTypeVertex) {
        typeTreeAssertion.actualAmountOfNullTypeTerminalVertices(increment(typeTreeAssertion.actualAmountOfNullTypeTerminalVertices()));
    }

    @Override
    public void visit(BooleanTypeVertex booleanTypeVertex) {
        typeTreeAssertion.actualAmountOfBooleanTypeTerminalVertices(increment(typeTreeAssertion.actualAmountOfBooleanTypeTerminalVertices()));
    }

    @Override
    public void visit(IntegerTypeVertex integerTypeVertex) {
        typeTreeAssertion.actualAmountOfIntegerTypeTerminalVertices(increment(typeTreeAssertion.actualAmountOfIntegerTypeTerminalVertices()));
    }

    @Override
    public void visit(FloatingPointTypeVertex floatingPointTypeVertex) {
        typeTreeAssertion.actualAmountOfFloatingPointTypeTerminalVertices(increment(typeTreeAssertion.actualAmountOfFloatingPointTypeTerminalVertices()));
    }

    @Override
    public void visit(StringTypeVertex stringTypeVertex) {
        typeTreeAssertion.actualAmountOfStringTypeTerminalVertices(increment(typeTreeAssertion.actualAmountOfStringTypeTerminalVertices()));
    }
}
