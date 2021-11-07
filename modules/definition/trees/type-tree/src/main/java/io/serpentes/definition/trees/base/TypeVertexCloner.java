package io.serpentes.definition.trees.base;

import io.serpentes.api.definition.trees.TypeTree;
import io.serpentes.api.definition.trees.TypeTreeVisitor;
import io.serpentes.api.definition.trees.edges.CollectionItemTypeEdge;
import io.serpentes.api.definition.trees.edges.KeyValueTypeEdge;
import io.serpentes.api.definition.trees.vertices.TypeVertex;
import io.serpentes.api.definition.trees.vertices.UnknownTypeVertex;
import io.serpentes.api.definition.trees.vertices.branch.CollectionTypeVertex;
import io.serpentes.api.definition.trees.vertices.branch.MapTypeVertex;
import io.serpentes.api.definition.trees.vertices.terminal.*;
import io.serpentes.definition.trees.base.edges.DefaultCollectionItemTypeEdge;
import io.serpentes.definition.trees.base.edges.DefaultKeyValueTypeEdge;
import io.serpentes.definition.trees.base.vertices.branch.DefaultCollectionTypeVertex;
import io.serpentes.definition.trees.base.vertices.branch.DefaultMapTypeVertex;
import io.serpentes.definition.trees.base.vertices.terminal.*;

import java.util.Stack;

public class TypeVertexCloner implements TypeTreeVisitor {
    private final Stack<TypeTree> cloneStack = new Stack<>();
    private final TypedRootTypeVertexRetriever typedRootVertexRetriever = new TypedRootTypeVertexRetriever();


    public TypeTree clone(TypeVertex typeVertex) {
        typeVertex.accept(this);
        return cloneStack.pop();
    }

    @Override
    public void visit(MapTypeVertex mapTypeVertex) {
        final var defaultTypeTree = new DefaultTypeTree(new DefaultMapTypeVertex());
        cloneStack.push(defaultTypeTree);
        refreshTypeVertexRetriever();
        mapTypeVertex.getEdges().forEach(child -> child.accept(this));
    }

    @Override
    public void visit(KeyValueTypeEdge keyValueTypeEdge) {
        final var parent = typedRootVertexRetriever.getMapTypeVertex();
        keyValueTypeEdge.getValue().accept(this);
        final var clone = new DefaultKeyValueTypeEdge(keyValueTypeEdge.getKey(), cloneStack.pop());
        parent.addEdge(clone);
        refreshTypeVertexRetriever();
    }

    @Override
    public void visit(CollectionTypeVertex collectionTypeVertex) {
        final var clone = new DefaultCollectionTypeVertex();
        final var defaultTypeTree = new DefaultTypeTree(clone);
        cloneStack.push(defaultTypeTree);
        refreshTypeVertexRetriever();
        collectionTypeVertex.getAllowedVertexTypes().forEach(child -> {
            child.accept(this);
            refreshTypeVertexRetriever();
            clone.addAllowedVertexType(typedRootVertexRetriever.getTypeVertex());
            cloneStack.pop();
        });
        collectionTypeVertex.getEdges().forEach(child -> child.accept(this));
    }

    @Override
    public void visit(CollectionItemTypeEdge collectionItemTypeEdge) {
        final var parent = typedRootVertexRetriever.getCollectionTypeVertex();
        collectionItemTypeEdge.getItem().accept(this);
        final var clone = new DefaultCollectionItemTypeEdge(collectionItemTypeEdge.getIndex(), cloneStack.pop());
        parent.addEdge(clone);
        refreshTypeVertexRetriever();
    }

    @Override
    public void visit(NullTypeVertex nullTypeVertex) {
        final var clone = new DefaultNullTypeVertex();
        copyValue(nullTypeVertex, clone);
        cloneStack.push(new DefaultTypeTree(clone));
    }

    private void copyValue(final TerminalTypeVertex source, final TerminalTypeVertex target) {
        target.setValue(source.getValue());
    }

    private void refreshTypeVertexRetriever() {
        typedRootVertexRetriever.refresh(cloneStack.peek());
    }

    @Override
    public void visit(BooleanTypeVertex booleanTypeVertex) {
        final var clone = new DefaultBooleanTypeVertex();
        copyValue(booleanTypeVertex, clone);
        cloneStack.push(new DefaultTypeTree(clone));
    }

    @Override
    public void visit(IntegerTypeVertex integerTypeVertex) {
        final var clone = new DefaultIntegerTypeVertex();
        copyValue(integerTypeVertex, clone);
        cloneStack.push(new DefaultTypeTree(clone));
    }

    @Override
    public void visit(FloatingPointTypeVertex floatingPointTypeVertex) {
        final var clone = new DefaultFloatingPointTypeVertex();
        copyValue(floatingPointTypeVertex, clone);
        cloneStack.push(new DefaultTypeTree(clone));
    }

    @Override
    public void visit(StringTypeVertex stringTypeVertex) {
        final var clone = new DefaultStringTypeVertex();
        copyValue(stringTypeVertex, clone);
        cloneStack.push(new DefaultTypeTree(clone));
    }

    @Override
    public void visit(UnknownTypeVertex unknownTypeVertex) {

    }
}
