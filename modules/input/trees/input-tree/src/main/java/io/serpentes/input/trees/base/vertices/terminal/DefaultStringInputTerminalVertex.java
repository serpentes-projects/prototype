package io.serpentes.input.trees.base.vertices.terminal;

import io.serpentes.api.input.trees.vertices.InputVertexVisitor;
import io.serpentes.api.input.trees.vertices.terminal.InputTerminalVertexVisitor;
import io.serpentes.api.input.trees.vertices.terminal.StringInputTerminalVertex;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class DefaultStringInputTerminalVertex implements StringInputTerminalVertex {
    private String value;

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public void accept(InputVertexVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void accept(InputTerminalVertexVisitor visitor) {
        visitor.visit(this);
    }
}
