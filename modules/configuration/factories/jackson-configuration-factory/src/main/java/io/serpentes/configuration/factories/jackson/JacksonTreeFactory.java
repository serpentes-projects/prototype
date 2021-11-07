package io.serpentes.configuration.factories.jackson;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import io.serpentes.api.definition.sources.DefinitionSources;
import io.serpentes.api.definition.trees.TypeTreeVisitor;
import io.serpentes.api.definition.trees.edges.CollectionItemTypeEdge;
import io.serpentes.api.definition.trees.edges.KeyValueTypeEdge;
import io.serpentes.api.definition.trees.vertices.UnknownTypeVertex;
import io.serpentes.api.definition.trees.vertices.TypeVertex;
import io.serpentes.api.definition.trees.vertices.branch.CollectionTypeVertex;
import io.serpentes.api.definition.trees.vertices.branch.MapTypeVertex;
import io.serpentes.api.definition.trees.vertices.terminal.*;
import io.serpentes.api.definition.trees.vertices.terminal.*;
import io.serpentes.input.stores.base.InputStore;
import io.serpentes.input.trees.base.TypedTreeComponentRetriever;
import jakarta.inject.Inject;

import java.util.Stack;

public class JacksonTreeFactory implements TypeTreeVisitor {
    private final DefinitionSources definitionSources;
    private final InputStore inputStore;
    private final Stack<JsonNode> jsonNodeStack = new Stack<>();
    private final TypedTreeComponentRetriever typedTreeComponentRetriever = new TypedTreeComponentRetriever();
    private JsonNodeFactory jsonNodeFactory;

    @Inject
    public JacksonTreeFactory(final DefinitionSources definitionSources, final InputStore inputStore) {
        this.definitionSources = definitionSources;
        this.inputStore = inputStore;
    }

    public JsonNode create(final ObjectMapper objectMapper) {
        jsonNodeFactory = objectMapper.getNodeFactory();
        definitionSources.getTypeTree().accept(this);
        return jsonNodeStack.pop();
    }

    @Override
    public void visit(KeyValueTypeEdge keyValueTypeEdge) {
        keyValueTypeEdge.getValue().accept(this);
    }

    @Override
    public void visit(CollectionItemTypeEdge collectionItemTypeEdge) {
        collectionItemTypeEdge.getItem().accept(this);
    }

    @Override
    public void visit(UnknownTypeVertex unknownTypeVertex) {
        //NOTE: Normally hitting this would mean we encountered a problematic issue in the type definition, for now add a null node.
        jsonNodeStack.push(jsonNodeFactory.nullNode());
    }

    @Override
    public void visit(MapTypeVertex mapTypeVertex) {
        if (refresh(mapTypeVertex)) {
            final var map = jsonNodeFactory.objectNode();
            jsonNodeStack.push(map);
            for (KeyValueTypeEdge edge : mapTypeVertex.getEdges()) {
                edge.accept(this);
                map.set(edge.getKey(), jsonNodeStack.pop());
            }
        }
        else{
            jsonNodeStack.push(jsonNodeFactory.nullNode());
        }
    }


    private boolean refresh(final TypeVertex typeVertex) {
        final var inputSourceValues = inputStore.get(typeVertex);
        final var firstNonNullValue = inputSourceValues.findFirstNonNullValue();
        if (firstNonNullValue != null) {
            firstNonNullValue.accept(typedTreeComponentRetriever);
            return true;
        }
        return false;
    }

    @Override
    public void visit(CollectionTypeVertex collectionTypeVertex) {
        if (refresh(collectionTypeVertex)) {
            final var collection = jsonNodeFactory.arrayNode();
            jsonNodeStack.push(collection);
            for (CollectionItemTypeEdge edge : collectionTypeVertex.getEdges()) {
                edge.accept(this);
                collection.add(jsonNodeStack.pop());
            }
        }
        else{
            jsonNodeStack.push(jsonNodeFactory.nullNode());
        }
    }

    @Override
    public void visit(NullTypeVertex nullTypeVertex) {
        jsonNodeStack.push(jsonNodeFactory.nullNode());
    }

    @Override
    public void visit(BooleanTypeVertex booleanTypeVertex) {
        if (refresh(booleanTypeVertex)) {
            //FIXME: This will break on malformed boolean strings.
            jsonNodeStack.push(jsonNodeFactory.booleanNode(Boolean.parseBoolean(typedTreeComponentRetriever.getBooleanInputTerminalVertex().getValue())));
        }
        else{
            jsonNodeStack.push(jsonNodeFactory.nullNode());
        }
    }

    @Override
    public void visit(IntegerTypeVertex integerTypeVertex) {
        if (refresh(integerTypeVertex)) {
            //FIXME: This will be incorrect when the Integer types do not match. Use Jackson internals in later version.
            jsonNodeStack.push(jsonNodeFactory.numberNode(Integer.parseInt(typedTreeComponentRetriever.getIntegerInputTerminalVertex().getValue())));
        }
        else{
            jsonNodeStack.push(jsonNodeFactory.nullNode());
        }
    }

    @Override
    public void visit(FloatingPointTypeVertex floatingPointTypeVertex) {
        if (refresh(floatingPointTypeVertex)) {
            //FIXME: This will be incorrect when the FloatingPoint types do not match. Use Jackson internals in later version.
            jsonNodeStack.push(jsonNodeFactory.numberNode(Double.valueOf(typedTreeComponentRetriever.getFloatingPointInputTerminalVertex().getValue())));
        }
        else{
            jsonNodeStack.push(jsonNodeFactory.nullNode());
        }
    }

    @Override
    public void visit(StringTypeVertex stringTypeVertex) {
        if (refresh(stringTypeVertex)) {
            //FIXME: This will be incorrect when the FloatingPoint types do not match. Use Jackson internals in later version.
            jsonNodeStack.push(jsonNodeFactory.textNode(typedTreeComponentRetriever.getStringInputTerminalVertex().getValue()));
        }
        else{
            jsonNodeStack.push(jsonNodeFactory.nullNode());
        }
    }
}
