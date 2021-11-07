package io.serpentes.api.definition.trees.vertices;

import io.serpentes.api.definition.trees.vertices.branch.BranchTypeVertexVisitor;
import io.serpentes.api.definition.trees.vertices.terminal.TerminalTypeVertexVisitor;

public interface UnknownTypeVertex extends TypeVertex {

     void accept(BranchTypeVertexVisitor visitor);

     void accept(TerminalTypeVertexVisitor visitor);
}
