package io.serpentes.definition.trees.base.vertices;

import io.serpentes.api.definition.trees.TypeTreeVisitor;
import io.serpentes.api.definition.trees.vertices.UnknownTypeVertex;
import io.serpentes.api.definition.trees.vertices.branch.BranchTypeVertexVisitor;
import io.serpentes.api.definition.trees.vertices.terminal.TerminalTypeVertexVisitor;

public class DefaultUnknownTypeVertex implements UnknownTypeVertex {
    @Override
    public void accept(TypeTreeVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void accept(BranchTypeVertexVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void accept(TerminalTypeVertexVisitor visitor) {
        visitor.visit(this);
    }
}
