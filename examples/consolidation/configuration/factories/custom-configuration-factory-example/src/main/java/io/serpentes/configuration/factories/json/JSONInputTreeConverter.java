package io.serpentes.configuration.factories.json;

import io.serpentes.api.input.trees.InputTree;
import io.serpentes.api.input.trees.InputTreeVisitor;
import io.serpentes.api.input.trees.edges.CollectionItemInputEdge;
import io.serpentes.api.input.trees.edges.InputEdge;
import io.serpentes.api.input.trees.edges.KeyValueInputEdge;
import io.serpentes.api.input.trees.vertices.InputNullVertex;
import io.serpentes.api.input.trees.vertices.branch.CollectionInputVertex;
import io.serpentes.api.input.trees.vertices.branch.InputBranchVertex;
import io.serpentes.api.input.trees.vertices.branch.MapInputVertex;
import io.serpentes.api.input.trees.vertices.terminal.*;

import java.util.Iterator;
import java.util.Stack;

public class JSONInputTreeConverter implements InputTreeVisitor {
    private final Stack<StringBuilder> stringBuilderStack = new Stack<>();

    public String convert(InputTree inputTree) {
        clear();
        stringBuilderStack.push(new StringBuilder());
        inputTree.accept(this);
        return stringBuilderStack.pop().toString();
    }

    public void clear() {
        stringBuilderStack.clear();
    }

    @Override
    public void visit(MapInputVertex mapInputVertex) {
        final var parent = stringBuilderStack.peek();
        final var map = visitCompositeChildren(mapInputVertex);
        JSONUtils.wrapInBrackets(map);
        stringBuilderStack.pop();
        parent.append(map);
    }

    @Override
    public void visit(KeyValueInputEdge keyValueInputEdge) {
        final var parent = stringBuilderStack.peek();
        final var keyValue = new StringBuilder();
        stringBuilderStack.push(keyValue);
        keyValue.append(JSONUtils.wrapInQuotationMarks(keyValueInputEdge.getLabel()));
        keyValue.append(JSONUtils.COLON);
        keyValueInputEdge.getValue().accept(this);
        stringBuilderStack.pop();
        parent.append(keyValue);
    }

    @Override
    public void visit(CollectionInputVertex collectionInputVertex) {
        final var parent = stringBuilderStack.peek();
        final var collection = visitCompositeChildren(collectionInputVertex);
        JSONUtils.wrapInSquareBrackets(collection);
        stringBuilderStack.pop();
        parent.append(collection);
    }

    @Override
    public void visit(CollectionItemInputEdge collectionItemInputEdge) {
        collectionItemInputEdge.getItem().accept(this);
    }

    private <T extends InputEdge> StringBuilder visitCompositeChildren(InputBranchVertex<T> inputBranchVertex) {
        final var composite = new StringBuilder();
        stringBuilderStack.push(composite);
        final Iterator<T> iterator = inputBranchVertex.getEdges().iterator();
        while(iterator.hasNext()){
            final var  next = iterator.next();
            next.accept(this);
            if(iterator.hasNext()){
                composite.append(JSONUtils.COMMA);
            }
        }
        return composite;
    }

    @Override
    public void visit(InputNullVertex inputNullVertex) {
        stringBuilderStack.peek();
    }

    @Override
    public void visit(NullInputTerminalVertex nullInputTerminalVertex) {
        stringBuilderStack.peek().append(nullInputTerminalVertex.getValue());
    }

    @Override
    public void visit(BooleanInputTerminalVertex booleanInputTerminalVertex) {
        stringBuilderStack.peek().append(booleanInputTerminalVertex.getValue());
    }

    @Override
    public void visit(IntegerInputTerminalVertex integerInputTerminalVertex) {
        stringBuilderStack.peek().append(integerInputTerminalVertex.getValue());
    }

    @Override
    public void visit(FloatingPointInputTerminalVertex floatingPointInputTerminalVertex) {
        stringBuilderStack.peek().append(floatingPointInputTerminalVertex.getValue());
    }

    @Override
    public void visit(StringInputTerminalVertex stringInputTerminalVertex) {
        stringBuilderStack.peek().append(JSONUtils.wrapInQuotationMarks(stringInputTerminalVertex.getValue()));
    }
}
