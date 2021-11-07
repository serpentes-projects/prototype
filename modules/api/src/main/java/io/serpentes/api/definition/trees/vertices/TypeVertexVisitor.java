package io.serpentes.api.definition.trees.vertices;

import io.serpentes.api.definition.trees.vertices.branch.BranchTypeVertexVisitor;
import io.serpentes.api.definition.trees.vertices.terminal.TerminalTypeVertexVisitor;

public interface TypeVertexVisitor extends BranchTypeVertexVisitor, TerminalTypeVertexVisitor {
}
