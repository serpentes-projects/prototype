package io.serpentes.api.input.trees.vertices.branch;


import io.serpentes.api.input.trees.edges.InputEdge;
import io.serpentes.api.input.trees.vertices.InputVertex;

import java.util.List;

public interface InputBranchVertex<E extends InputEdge> extends InputVertex {
     List<E> getEdges();

     void addEdge(E vertex);

     void accept(InputBranchVertexVisitor visitor);
}
