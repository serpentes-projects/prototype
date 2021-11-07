package io.serpentes.api.definition.trees.vertices.terminal;


import io.serpentes.api.definition.trees.vertices.TypeVertex;

public interface TerminalTypeVertex extends TypeVertex {
    String getValue();

    void setValue(String value);

    void accept(TerminalTypeVertexVisitor visitor);
}
