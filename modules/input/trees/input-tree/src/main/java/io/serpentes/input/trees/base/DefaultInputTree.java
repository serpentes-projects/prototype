package io.serpentes.input.trees.base;

import io.serpentes.api.input.trees.InputTree;
import io.serpentes.api.input.trees.InputTreeVisitor;
import io.serpentes.api.input.trees.vertices.InputVertex;
import io.serpentes.api.input.trees.vertices.InputNullVertex;
import io.serpentes.api.input.trees.vertices.InputVertexVisitor;
import io.serpentes.api.input.trees.vertices.NullInputVertexVisitor;
import io.serpentes.api.input.trees.vertices.branch.CollectionInputVertex;
import io.serpentes.api.input.trees.vertices.branch.InputBranchVertexVisitor;
import io.serpentes.api.input.trees.vertices.branch.MapInputVertex;
import io.serpentes.api.input.trees.vertices.terminal.*;
import io.serpentes.api.input.trees.vertices.terminal.*;
import io.serpentes.input.trees.base.vertices.DefaultInputNullVertex;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class DefaultInputTree implements InputTree {
    private final MapInputVertex mapInputVertex;
    private final CollectionInputVertex collectionInputVertex;
    private final NullInputTerminalVertex nullInputTerminalVertex;
    private final BooleanInputTerminalVertex booleanInputTerminalVertex;
    private final IntegerInputTerminalVertex integerInputTerminalVertex;
    private final FloatingPointInputTerminalVertex floatingPointInputTerminalVertex;
    private final StringInputTerminalVertex stringInputTerminalVertex;
    private final InputNullVertex inputNullRootVertex;

    public DefaultInputTree() {
        mapInputVertex = null;
        collectionInputVertex = null;
        nullInputTerminalVertex = null;
        booleanInputTerminalVertex = null;
        integerInputTerminalVertex = null;
        floatingPointInputTerminalVertex = null;
        stringInputTerminalVertex = null;
        inputNullRootVertex = new DefaultInputNullVertex();
    }

    public DefaultInputTree(MapInputVertex rootVertex) {
        mapInputVertex = rootVertex;
        collectionInputVertex = null;
        nullInputTerminalVertex = null;
        booleanInputTerminalVertex = null;
        integerInputTerminalVertex = null;
        floatingPointInputTerminalVertex = null;
        stringInputTerminalVertex = null;
        inputNullRootVertex = null;
    }

    public DefaultInputTree(CollectionInputVertex rootVertex) {
        mapInputVertex = null;
        collectionInputVertex = rootVertex;
        nullInputTerminalVertex = null;
        booleanInputTerminalVertex = null;
        integerInputTerminalVertex = null;
        floatingPointInputTerminalVertex = null;
        stringInputTerminalVertex = null;
        inputNullRootVertex = null;
    }

    public DefaultInputTree(NullInputTerminalVertex rootVertex) {
        mapInputVertex = null;
        collectionInputVertex = null;
        nullInputTerminalVertex = rootVertex;
        booleanInputTerminalVertex = null;
        integerInputTerminalVertex = null;
        floatingPointInputTerminalVertex = null;
        stringInputTerminalVertex = null;
        inputNullRootVertex = null;
    }

    public DefaultInputTree(BooleanInputTerminalVertex rootVertex) {
        mapInputVertex = null;
        collectionInputVertex = null;
        nullInputTerminalVertex = null;
        booleanInputTerminalVertex = rootVertex;
        integerInputTerminalVertex = null;
        floatingPointInputTerminalVertex = null;
        stringInputTerminalVertex = null;
        inputNullRootVertex = null;
    }

    public DefaultInputTree(IntegerInputTerminalVertex rootVertex) {
        mapInputVertex = null;
        collectionInputVertex = null;
        nullInputTerminalVertex = null;
        booleanInputTerminalVertex = null;
        integerInputTerminalVertex = rootVertex;
        floatingPointInputTerminalVertex = null;
        stringInputTerminalVertex = null;
        inputNullRootVertex = null;
    }

    public DefaultInputTree(FloatingPointInputTerminalVertex rootVertex) {
        mapInputVertex = null;
        collectionInputVertex = null;
        nullInputTerminalVertex = null;
        booleanInputTerminalVertex = null;
        integerInputTerminalVertex = null;
        floatingPointInputTerminalVertex = rootVertex;
        stringInputTerminalVertex = null;
        inputNullRootVertex = null;
    }

    public DefaultInputTree(StringInputTerminalVertex rootVertex) {
        mapInputVertex = null;
        collectionInputVertex = null;
        nullInputTerminalVertex = null;
        booleanInputTerminalVertex = null;
        integerInputTerminalVertex = null;
        floatingPointInputTerminalVertex = null;
        stringInputTerminalVertex = rootVertex;
        inputNullRootVertex = null;
    }


    @Override
    public void accept(InputTreeVisitor visitor) {
        visitIfPresent(visitor, mapInputVertex);
        visitIfPresent(visitor, collectionInputVertex);
        visitIfPresent(visitor, nullInputTerminalVertex);
        visitIfPresent(visitor, booleanInputTerminalVertex);
        visitIfPresent(visitor, integerInputTerminalVertex);
        visitIfPresent(visitor, floatingPointInputTerminalVertex);
        visitIfPresent(visitor, stringInputTerminalVertex);
        visitIfPresent(visitor, inputNullRootVertex);
    }
    
    @Override
    public void accept(InputVertexVisitor visitor) {
        visitIfPresent(visitor, mapInputVertex);
        visitIfPresent(visitor, collectionInputVertex);
        visitIfPresent(visitor, nullInputTerminalVertex);
        visitIfPresent(visitor, booleanInputTerminalVertex);
        visitIfPresent(visitor, integerInputTerminalVertex);
        visitIfPresent(visitor, floatingPointInputTerminalVertex);
        visitIfPresent(visitor, stringInputTerminalVertex);
        visitIfPresent(visitor, inputNullRootVertex);
    }

    public void accept(InputBranchVertexVisitor visitor) {
        visitIfPresent(visitor, mapInputVertex);
        visitIfPresent(visitor, collectionInputVertex);
        visitIfPresent(visitor, inputNullRootVertex);
    }

    public void accept(InputTerminalVertexVisitor visitor) {
        visitIfPresent(visitor, nullInputTerminalVertex);
        visitIfPresent(visitor, booleanInputTerminalVertex);
        visitIfPresent(visitor, integerInputTerminalVertex);
        visitIfPresent(visitor, floatingPointInputTerminalVertex);
        visitIfPresent(visitor, stringInputTerminalVertex);
        visitIfPresent(visitor, inputNullRootVertex);
    }

    private void visitIfPresent(InputBranchVertexVisitor visitor, MapInputVertex rootVertex) {
        if (rootVertexNotNull(rootVertex)) {
            visitor.visit(rootVertex);
        }
    }

    private void visitIfPresent(InputBranchVertexVisitor visitor, CollectionInputVertex rootVertex) {
        if (rootVertexNotNull(rootVertex)) {
            visitor.visit(rootVertex);
        }
    }

    private void visitIfPresent(InputTerminalVertexVisitor visitor, NullInputTerminalVertex rootVertex) {
        if (rootVertexNotNull(rootVertex)) {
            visitor.visit(rootVertex);
        }
    }

    private void visitIfPresent(InputTerminalVertexVisitor visitor, BooleanInputTerminalVertex rootVertex) {
        if (rootVertexNotNull(rootVertex)) {
            visitor.visit(rootVertex);
        }
    }

    private void visitIfPresent(InputTerminalVertexVisitor visitor, IntegerInputTerminalVertex rootVertex) {
        if (rootVertexNotNull(rootVertex)) {
            visitor.visit(rootVertex);
        }
    }

    private void visitIfPresent(InputTerminalVertexVisitor visitor, FloatingPointInputTerminalVertex rootVertex) {
        if (rootVertexNotNull(rootVertex)) {
            visitor.visit(rootVertex);
        }
    }

    private void visitIfPresent(InputTerminalVertexVisitor visitor, StringInputTerminalVertex rootVertex) {
        if (rootVertexNotNull(rootVertex)) {
            visitor.visit(rootVertex);
        }
    }

    private void visitIfPresent(NullInputVertexVisitor visitor, InputNullVertex rootVertex) {
        if (rootVertexNotNull(rootVertex)) {
            visitor.visit(rootVertex);
        }
    }
    
    private boolean rootVertexNotNull(final InputVertex rootVertex){
        return rootVertex != null;
    }
}
