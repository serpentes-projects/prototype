package io.serpentes.api.input.trees;


import io.serpentes.api.input.trees.vertices.InputVertex;

public interface InputTree extends InputVertex {
    void accept(InputTreeVisitor visitor);
}
