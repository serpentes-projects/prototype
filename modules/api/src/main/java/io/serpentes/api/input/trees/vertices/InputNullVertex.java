package io.serpentes.api.input.trees.vertices;

import io.serpentes.api.input.trees.vertices.branch.InputBranchVertexVisitor;
import io.serpentes.api.input.trees.vertices.terminal.InputTerminalVertexVisitor;

public interface InputNullVertex extends InputVertex {
    void accept(InputBranchVertexVisitor visitor);

    void accept(InputTerminalVertexVisitor visitor);
}
