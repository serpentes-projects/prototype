package io.serpentes.api.definition.trees.vertices;


import io.serpentes.api.definition.trees.TypeTreeVisitor;

public interface TypeVertex {
    void accept(TypeTreeVisitor typeTreeVisitor);
}
