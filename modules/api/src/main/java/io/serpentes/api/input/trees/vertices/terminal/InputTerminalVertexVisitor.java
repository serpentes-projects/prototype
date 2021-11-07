package io.serpentes.api.input.trees.vertices.terminal;

import io.serpentes.api.input.trees.vertices.NullInputVertexVisitor;

public interface InputTerminalVertexVisitor extends NullInputVertexVisitor {
    void visit(NullInputTerminalVertex nullInputTerminalVertex);

    void visit(BooleanInputTerminalVertex booleanInputTerminalVertex);

    void visit(IntegerInputTerminalVertex integerInputTerminalVertex);

    void visit(FloatingPointInputTerminalVertex floatingPointInputTerminalVertex);

    void visit(StringInputTerminalVertex stringInputTerminalVertex);

}
