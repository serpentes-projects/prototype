package io.serpentes.api.definition.trees.vertices.branch;


import io.serpentes.api.definition.trees.vertices.TypeVertex;
import io.serpentes.api.definition.trees.edges.TypeEdge;

import java.util.List;

public interface BranchTypeVertex<C extends TypeEdge> extends TypeVertex {
     List<C> getEdges();

     void addEdge(C edge);

     void accept(BranchTypeVertexVisitor visitor);
}
