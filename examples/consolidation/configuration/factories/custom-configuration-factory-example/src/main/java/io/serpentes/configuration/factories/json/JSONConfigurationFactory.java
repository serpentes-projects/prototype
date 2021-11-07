package io.serpentes.configuration.factories.json;

import io.serpentes.api.configuration.factories.ConfigurationFactory;
import io.serpentes.api.definition.sources.DefinitionSources;
import io.serpentes.api.definition.trees.TypeTreeVisitor;
import io.serpentes.api.definition.trees.edges.CollectionItemTypeEdge;
import io.serpentes.api.definition.trees.edges.KeyValueTypeEdge;
import io.serpentes.api.definition.trees.vertices.TypeVertex;
import io.serpentes.api.definition.trees.vertices.UnknownTypeVertex;
import io.serpentes.api.definition.trees.vertices.branch.BranchTypeVertex;
import io.serpentes.api.definition.trees.vertices.branch.CollectionTypeVertex;
import io.serpentes.api.definition.trees.vertices.branch.MapTypeVertex;
import io.serpentes.api.definition.trees.vertices.terminal.*;
import io.serpentes.input.stores.base.InputStore;
import jakarta.inject.Inject;

import java.util.Stack;

public class JSONConfigurationFactory implements ConfigurationFactory, TypeTreeVisitor {
    private final DefinitionSources definitionSources;
    private final InputStore inputStore;

    private final Stack<StringBuilder> stack = new Stack<>();
    private final JSONInputTreeConverter jsonInputTreeConverter = new JSONInputTreeConverter();

    @Inject
    public JSONConfigurationFactory(final DefinitionSources definitionSources, final InputStore inputStore ){
        this.definitionSources = definitionSources;
        this.inputStore = inputStore;
    }

    public String create() {
        stack.push(new StringBuilder());
        definitionSources.getTypeTree().accept(this);
        return stack.pop().toString();
    }

    @Override
    public void visit(UnknownTypeVertex unknownTypeVertex) {
        final var firstNonNullValue = inputStore.get(unknownTypeVertex).findFirstNonNullValue();
        stack.peek().append(jsonInputTreeConverter.convert(firstNonNullValue));
    }

    @Override
    public void visit(MapTypeVertex mapTypeVertex) {
        final var parent = stack.peek();
        final var map = visitChildren(mapTypeVertex);
        JSONUtils.wrapInBrackets(map);
        parent.append(map);
    }

    @Override
    public void visit(KeyValueTypeEdge keyValueTypeEdge) {
        final var parent = stack.peek();
        final var keyValue = new StringBuilder();
        keyValue.append(JSONUtils.wrapInQuotationMarks(keyValueTypeEdge.getKey()));
        keyValue.append(JSONUtils.COLON);
        stack.push(keyValue);
        keyValueTypeEdge.getValue().accept(this);
        stack.pop();
        parent.append(keyValue);
    }

    @Override
    public void visit(CollectionTypeVertex collectionTypeVertex) {
        final var firstNonNullValue = inputStore.get(collectionTypeVertex).findFirstNonNullValue();
        stack.peek().append(jsonInputTreeConverter.convert(firstNonNullValue));
    }

    @Override
    public void visit(CollectionItemTypeEdge collectionItemTypeEdge) {
        //TODO: Implement. Good enough for now.
    }

    private StringBuilder visitChildren(BranchTypeVertex<?> branchTypeVertex) {
        final var parent = new StringBuilder();
        stack.push(parent);
        for (int i = 0; i < branchTypeVertex.getEdges().size(); i++) {
            final var typeEdge = branchTypeVertex.getEdges().get(i);
            typeEdge.accept(this);
            if (i < branchTypeVertex.getEdges().size() - 1) {
                parent.append(JSONUtils.COMMA);
            }
        }
        return stack.pop();
    }

    @Override
    public void visit(NullTypeVertex nullTypeVertex) {
        appendTerminalVertex(nullTypeVertex);
    }

    @Override
    public void visit(BooleanTypeVertex booleanTypeVertex) {
        appendTerminalVertex(booleanTypeVertex);
    }

    @Override
    public void visit(IntegerTypeVertex integerTypeVertex) {
        appendTerminalVertex(integerTypeVertex);
    }

    @Override
    public void visit(FloatingPointTypeVertex floatingPointTypeVertex) {
        appendTerminalVertex(floatingPointTypeVertex);
    }

    @Override
    public void visit(StringTypeVertex stringTypeVertex) {
        appendTerminalVertex(stringTypeVertex);
    }

    private void appendTerminalVertex(TypeVertex typeVertex) {
        final var firstNonNullValue = inputStore.get(typeVertex).findFirstNonNullValue();
        stack.peek().append(jsonInputTreeConverter.convert(firstNonNullValue));
    }
}
