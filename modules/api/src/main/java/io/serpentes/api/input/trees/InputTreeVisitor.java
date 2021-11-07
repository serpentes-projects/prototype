package io.serpentes.api.input.trees;

import io.serpentes.api.input.trees.edges.InputEdgeVisitor;
import io.serpentes.api.input.trees.vertices.InputVertexVisitor;

public interface InputTreeVisitor extends InputVertexVisitor, InputEdgeVisitor {
}
