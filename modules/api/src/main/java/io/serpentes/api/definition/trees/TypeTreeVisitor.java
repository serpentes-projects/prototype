package io.serpentes.api.definition.trees;


import io.serpentes.api.definition.trees.vertices.TypeVertexVisitor;
import io.serpentes.api.definition.trees.edges.TypeEdgeVisitor;

public interface TypeTreeVisitor extends TypeVertexVisitor, TypeEdgeVisitor {
}
