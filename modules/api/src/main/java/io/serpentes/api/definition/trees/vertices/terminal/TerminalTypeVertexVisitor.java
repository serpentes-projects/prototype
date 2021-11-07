package io.serpentes.api.definition.trees.vertices.terminal;


import io.serpentes.api.definition.trees.vertices.UnknownTypeVertexVisitor;

public interface TerminalTypeVertexVisitor extends UnknownTypeVertexVisitor {
    void visit(NullTypeVertex nullTypeVertex);

    void visit(BooleanTypeVertex booleanTypeVertex);

    void visit(IntegerTypeVertex integerTypeVertex);

    void visit(FloatingPointTypeVertex floatingPointTypeVertex);

    void visit(StringTypeVertex stringTypeVertex);
}
