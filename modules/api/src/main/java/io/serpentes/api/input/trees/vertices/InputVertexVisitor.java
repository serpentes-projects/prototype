package io.serpentes.api.input.trees.vertices;

import io.serpentes.api.input.trees.vertices.branch.InputBranchVertexVisitor;
import io.serpentes.api.input.trees.vertices.terminal.InputTerminalVertexVisitor;

public interface InputVertexVisitor extends InputBranchVertexVisitor, InputTerminalVertexVisitor {
}
