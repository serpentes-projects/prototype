package io.serpentes.definition.trees.base;

import io.serpentes.api.definition.trees.TypeTree;
import io.serpentes.api.definition.trees.TypeTreeVisitor;
import io.serpentes.api.definition.trees.edges.KeyValueTypeEdge;
import io.serpentes.api.definition.trees.edges.TypeEdgeVisitor;
import io.serpentes.api.definition.trees.vertices.UnknownTypeVertex;
import io.serpentes.api.definition.trees.vertices.UnknownTypeVertexVisitor;
import io.serpentes.api.definition.trees.vertices.branch.BranchTypeVertexVisitor;
import io.serpentes.api.definition.trees.vertices.branch.CollectionTypeVertex;
import io.serpentes.api.definition.trees.vertices.branch.MapTypeVertex;
import io.serpentes.api.definition.trees.vertices.terminal.*;
import io.serpentes.api.definition.trees.vertices.terminal.*;
import io.serpentes.definition.trees.base.vertices.DefaultUnknownTypeVertex;
import lombok.EqualsAndHashCode;

//TODO: All of this is duplication, remove when out of prototyping-phase.
@EqualsAndHashCode
public class DefaultTypeTree implements TypeTree {
    private final MapTypeVertex mapTypeVertex;
    private final KeyValueTypeEdge keyValueTypeEdge;
    private final CollectionTypeVertex collectionTypeVertex;
    private final NullTypeVertex nullTypeVertex;
    private final BooleanTypeVertex booleanTypeVertex;
    private final IntegerTypeVertex integerTypeVertex;
    private final FloatingPointTypeVertex floatingPointTypeVertex;
    private final StringTypeVertex stringTypeVertex;
    private final UnknownTypeVertex unknownTypeVertex;

    public DefaultTypeTree() {
        mapTypeVertex = null;
        keyValueTypeEdge = null;
        collectionTypeVertex = null;
        nullTypeVertex = null;
        booleanTypeVertex = null;
        integerTypeVertex = null;
        floatingPointTypeVertex = null;
        stringTypeVertex = null;
        unknownTypeVertex = new DefaultUnknownTypeVertex();
    }

    public DefaultTypeTree(MapTypeVertex rootVertex) {
        mapTypeVertex = rootVertex;
        keyValueTypeEdge = null;
        collectionTypeVertex = null;
        nullTypeVertex = null;
        booleanTypeVertex = null;
        integerTypeVertex = null;
        floatingPointTypeVertex = null;
        stringTypeVertex = null;
        unknownTypeVertex = null;
    }

    public DefaultTypeTree(KeyValueTypeEdge rootVertex) {
        mapTypeVertex = null;
        keyValueTypeEdge = rootVertex;
        collectionTypeVertex = null;
        nullTypeVertex = null;
        booleanTypeVertex = null;
        integerTypeVertex = null;
        floatingPointTypeVertex = null;
        stringTypeVertex = null;
        unknownTypeVertex = null;
    }


    public DefaultTypeTree(CollectionTypeVertex rootVertex) {
        mapTypeVertex = null;
        keyValueTypeEdge = null;
        collectionTypeVertex = rootVertex;
        nullTypeVertex = null;
        booleanTypeVertex = null;
        integerTypeVertex = null;
        floatingPointTypeVertex = null;
        stringTypeVertex = null;
        unknownTypeVertex = null;
    }

    public DefaultTypeTree(NullTypeVertex rootVertex) {
        mapTypeVertex = null;
        keyValueTypeEdge = null;
        collectionTypeVertex = null;
        nullTypeVertex = rootVertex;
        booleanTypeVertex = null;
        integerTypeVertex = null;
        floatingPointTypeVertex = null;
        stringTypeVertex = null;
        unknownTypeVertex = null;
    }

    public DefaultTypeTree(BooleanTypeVertex rootVertex) {
        mapTypeVertex = null;
        keyValueTypeEdge = null;
        collectionTypeVertex = null;
        nullTypeVertex = null;
        booleanTypeVertex = rootVertex;
        integerTypeVertex = null;
        floatingPointTypeVertex = null;
        stringTypeVertex = null;
        unknownTypeVertex = null;
    }

    public DefaultTypeTree(IntegerTypeVertex rootVertex) {
        mapTypeVertex = null;
        keyValueTypeEdge = null;
        collectionTypeVertex = null;
        nullTypeVertex = null;
        booleanTypeVertex = null;
        integerTypeVertex = rootVertex;
        floatingPointTypeVertex = null;
        stringTypeVertex = null;
        unknownTypeVertex = null;
    }

    public DefaultTypeTree(FloatingPointTypeVertex rootVertex) {
        mapTypeVertex = null;
        keyValueTypeEdge = null;
        collectionTypeVertex = null;
        nullTypeVertex = null;
        booleanTypeVertex = null;
        integerTypeVertex = null;
        floatingPointTypeVertex = rootVertex;
        stringTypeVertex = null;
        unknownTypeVertex = null;
    }

    public DefaultTypeTree(StringTypeVertex rootVertex) {
        mapTypeVertex = null;
        keyValueTypeEdge = null;
        collectionTypeVertex = null;
        nullTypeVertex = null;
        booleanTypeVertex = null;
        integerTypeVertex = null;
        floatingPointTypeVertex = null;
        stringTypeVertex = rootVertex;
        unknownTypeVertex = null;
    }


    @Override
    public void accept(TypeTreeVisitor visitor) {
        visitIfPresent(visitor, mapTypeVertex);
        visitIfPresent(visitor, keyValueTypeEdge);
        visitIfPresent(visitor, collectionTypeVertex);
        visitIfPresent(visitor, nullTypeVertex);
        visitIfPresent(visitor, booleanTypeVertex);
        visitIfPresent(visitor, integerTypeVertex);
        visitIfPresent(visitor, floatingPointTypeVertex);
        visitIfPresent(visitor, stringTypeVertex);
        visitIfPresent(visitor, unknownTypeVertex);
    }

    public void accept(BranchTypeVertexVisitor visitor) {
        visitIfPresent(visitor, mapTypeVertex);
        visitIfPresent(visitor, collectionTypeVertex);
        visitIfPresent(visitor, unknownTypeVertex);
    }

    public void accept(TerminalTypeVertexVisitor visitor) {
        visitIfPresent(visitor, nullTypeVertex);
        visitIfPresent(visitor, booleanTypeVertex);
        visitIfPresent(visitor, integerTypeVertex);
        visitIfPresent(visitor, floatingPointTypeVertex);
        visitIfPresent(visitor, stringTypeVertex);
        visitIfPresent(visitor, unknownTypeVertex);
    }

    private void visitIfPresent(BranchTypeVertexVisitor visitor, MapTypeVertex rootVertex) {
        if (rootVertex != null) {
            visitor.visit(rootVertex);
        }
    }

    private void visitIfPresent(TypeEdgeVisitor visitor, KeyValueTypeEdge edge) {
        if (edge != null) {
            visitor.visit(edge);
        }
    }

    private void visitIfPresent(BranchTypeVertexVisitor visitor, CollectionTypeVertex rootVertex) {
        if (rootVertex != null) {
            visitor.visit(rootVertex);
        }
    }

    private void visitIfPresent(TerminalTypeVertexVisitor visitor, NullTypeVertex rootVertex) {
        if (rootVertex != null) {
            visitor.visit(rootVertex);
        }
    }

    private void visitIfPresent(TerminalTypeVertexVisitor visitor, BooleanTypeVertex rootVertex) {
        if (rootVertex != null) {
            visitor.visit(rootVertex);
        }
    }

    private void visitIfPresent(TerminalTypeVertexVisitor visitor, IntegerTypeVertex rootVertex) {
        if (rootVertex != null) {
            visitor.visit(rootVertex);
        }
    }

    private void visitIfPresent(TerminalTypeVertexVisitor visitor, FloatingPointTypeVertex rootVertex) {
        if (rootVertex != null) {
            visitor.visit(rootVertex);
        }
    }

    private void visitIfPresent(TerminalTypeVertexVisitor visitor, StringTypeVertex rootVertex) {
        if (rootVertex != null) {
            visitor.visit(rootVertex);
        }
    }

    private void visitIfPresent(UnknownTypeVertexVisitor visitor, UnknownTypeVertex rootVertex) {
        if (rootVertex != null) {
            visitor.visit(rootVertex);
        }
    }
}
