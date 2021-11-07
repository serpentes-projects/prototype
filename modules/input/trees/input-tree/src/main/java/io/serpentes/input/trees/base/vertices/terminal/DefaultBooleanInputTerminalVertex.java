package io.serpentes.input.trees.base.vertices.terminal;

import io.serpentes.api.input.trees.vertices.InputVertexVisitor;
import io.serpentes.api.input.trees.vertices.terminal.BooleanInputTerminalVertex;
import io.serpentes.api.input.trees.vertices.terminal.InputTerminalVertexVisitor;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class DefaultBooleanInputTerminalVertex implements BooleanInputTerminalVertex {
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
