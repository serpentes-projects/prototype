package io.serpentes.api.input.trees.vertices;

public interface InputVertex {
    void accept(InputVertexVisitor visitor);
}
