package io.serpentes.api.input.trees.vertices.terminal;


import io.serpentes.api.input.trees.vertices.InputVertex;

public interface InputTerminalVertex extends InputVertex {
    String getValue();

    void setValue(String value);

    void accept(InputTerminalVertexVisitor visitor);
}
