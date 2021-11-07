package io.serpentes.definition.trees.base.vertices.terminal;

import io.serpentes.api.definition.trees.TypeTreeVisitor;
import io.serpentes.api.definition.trees.vertices.terminal.StringTypeVertex;
import io.serpentes.api.definition.trees.vertices.terminal.TerminalTypeVertexVisitor;

public class DefaultStringTypeVertex implements StringTypeVertex {
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
    public void accept(TypeTreeVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void accept(TerminalTypeVertexVisitor visitor) {
        visitor.visit(this);
    }
}
