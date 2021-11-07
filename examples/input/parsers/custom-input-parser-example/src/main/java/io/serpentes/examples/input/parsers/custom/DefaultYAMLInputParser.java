package io.serpentes.examples.input.parsers.custom;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import io.serpentes.api.annotations.input.parsers.InputParser;
import io.serpentes.api.annotations.parsers.ContentType;
import io.serpentes.api.input.parsers.InputParsingException;
import io.serpentes.input.trees.base.DefaultInputTree;
import io.serpentes.input.trees.base.edges.DefaultCollectionItemInputEdge;
import io.serpentes.input.trees.base.edges.DefaultKeyValueInputEdge;
import io.serpentes.input.trees.base.vertices.branch.DefaultCollectionInputVertex;
import io.serpentes.input.trees.base.vertices.branch.DefaultMapInputVertex;
import io.serpentes.input.trees.base.vertices.terminal.*;

import java.util.Iterator;
import java.util.Map;

@InputParser
@ContentType(value = "application/yaml", aliases = {"yaml"})
public class DefaultYAMLInputParser implements io.serpentes.api.input.parsers.InputParser {
    private final ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());

    @Override
    public DefaultInputTree parse(String input) throws InputParsingException {
        try {
            final JsonNode rootNode = objectMapper.readTree(input);
            return buildTree(rootNode);
        } catch (JsonProcessingException e) {
            throw new InputParsingException(e);
        }
    }

    private DefaultInputTree buildTree(JsonNode jsonNode) throws InputParsingException {
        //Branch nodes
        if (jsonNode.isObject()) {
            return parseMap(jsonNode);
        } else if (jsonNode.isArray()) {
            return parseCollection(jsonNode);
        }
        //Terminal nodes
        else if (jsonNode.isNull()) {
            final var inputTerminalVertex = new DefaultNullInputTerminalVertex();
            inputTerminalVertex.setValue(jsonNode.asText());
            return new DefaultInputTree(inputTerminalVertex);
        } else if (jsonNode.isBoolean()) {
            final var inputTerminalVertex = new DefaultBooleanInputTerminalVertex();
            inputTerminalVertex.setValue(jsonNode.asText());
            return new DefaultInputTree(inputTerminalVertex);
        } else if (jsonNode.isIntegralNumber()) {
            final var inputTerminalVertex = new DefaultIntegerInputTerminalVertex();
            inputTerminalVertex.setValue(jsonNode.asText());
            return new DefaultInputTree(inputTerminalVertex);
        } else if (jsonNode.isFloatingPointNumber()) {
            final var inputTerminalVertex = new DefaultFloatingPointInputTerminalVertex();
            inputTerminalVertex.setValue(jsonNode.asText());
            return new DefaultInputTree(inputTerminalVertex);
        } else if (jsonNode.isTextual()) {
            final var inputTerminalVertex = new DefaultStringInputTerminalVertex();
            inputTerminalVertex.setValue(jsonNode.asText());
            return new DefaultInputTree(inputTerminalVertex);
        }
        else{
            return null; //TODO: This is actually an empty or mismatched input. Consider throwing a InputParsingException. For now return null.
        }
    }

    private DefaultInputTree parseMap(JsonNode jsonNode) throws InputParsingException {
        final var defaultMapInputVertex = new DefaultMapInputVertex();
        if (jsonNode.size() > 0) {
            final Iterator<Map.Entry<String, JsonNode>> elements = jsonNode.fields();
            while (elements.hasNext()) {
                final Map.Entry<String, JsonNode> next = elements.next();
                final var value = buildTree(next.getValue());
                final var keyValueInputEdge = new DefaultKeyValueInputEdge(next.getKey(), value);
                defaultMapInputVertex.addEdge(keyValueInputEdge);
            }
        }
        return new DefaultInputTree(defaultMapInputVertex);
    }

    private DefaultInputTree parseCollection(JsonNode jsonNode) throws InputParsingException {
        final var collectionInputVertex = new DefaultCollectionInputVertex();
        if (jsonNode.size() > 0) {
            final Iterator<JsonNode> elements = jsonNode.elements();
            int index = 0;
            while (elements.hasNext()) {
                final JsonNode next = elements.next();
                final var item = buildTree(next);
                final var collectionItemInputEdge = new DefaultCollectionItemInputEdge(index, item);
                collectionInputVertex.addEdge(collectionItemInputEdge);
                index++;
            }
        }
        return new DefaultInputTree(collectionInputVertex);
    }
}
