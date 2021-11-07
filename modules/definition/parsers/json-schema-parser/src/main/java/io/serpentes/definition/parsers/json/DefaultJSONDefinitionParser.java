package io.serpentes.definition.parsers.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.serpentes.api.annotations.definition.parsers.DefinitionParser;
import io.serpentes.api.annotations.definition.schema.SchemaVersion;
import io.serpentes.api.annotations.parsers.ContentType;
import io.serpentes.api.definition.parsers.DefinitionParsingException;
import io.serpentes.api.definition.trees.TypeTree;
import io.serpentes.definition.trees.base.DefaultTypeTree;
import io.serpentes.definition.trees.base.edges.DefaultKeyValueTypeEdge;
import io.serpentes.definition.trees.base.vertices.branch.DefaultCollectionTypeVertex;
import io.serpentes.definition.trees.base.vertices.branch.DefaultMapTypeVertex;
import io.serpentes.definition.trees.base.vertices.terminal.*;

import java.io.CharArrayReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.Map;

@DefinitionParser
@ContentType(value = "application/json", aliases = {"json"})
@SchemaVersion(value = "http://json-schema.org/draft-04/schema#", aliases = {"draft-04"}) //TODO: Use this.
public class DefaultJSONDefinitionParser implements io.serpentes.api.definition.parsers.DefinitionParser {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public TypeTree parse(String definitionContent) throws DefinitionParsingException {
        try (final Reader reader = new CharArrayReader(definitionContent.toCharArray())) {
            final var jsonNode = objectMapper.readTree(reader);
            if (!jsonNode.isMissingNode()) {
                return parseSchema(jsonNode);
            }
            return new DefaultTypeTree();
        } catch (JsonProcessingException e) {
            throw new DefinitionParsingException(e);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new DefaultTypeTree();
    }

    private TypeTree parseSchema(final JsonNode jsonNode) throws DefinitionParsingException {
        return this.parseType(jsonNode, jsonNode.get(JsonSchemaKeyNames.TYPE).asText());
    }

    private TypeTree parseType(final JsonNode node, final String type) throws DefinitionParsingException {
        switch (type) {
            case JsonSchemaTypes.NULL:
                final var defaultNullTypeVertex = new DefaultNullTypeVertex();
                defaultNullTypeVertex.setValue(node.asText());
                return new DefaultTypeTree(defaultNullTypeVertex);
            case JsonSchemaTypes.BOOLEAN:
                final var defaultBooleanTypeVertex = new DefaultBooleanTypeVertex();
                defaultBooleanTypeVertex.setValue(node.asText());
                return new DefaultTypeTree(defaultBooleanTypeVertex);
            case JsonSchemaTypes.INTEGER:
                final var defaultIntegerTypeVertex = new DefaultIntegerTypeVertex();
                defaultIntegerTypeVertex.setValue(node.asText());
                return new DefaultTypeTree(defaultIntegerTypeVertex);
            case JsonSchemaTypes.FLOATING_POINT:
                final var defaultFloatingPointTypeVertex = new DefaultFloatingPointTypeVertex();
                defaultFloatingPointTypeVertex.setValue(node.asText());
                return new DefaultTypeTree(defaultFloatingPointTypeVertex);
            case JsonSchemaTypes.STRING:
                final var defaultStringTypeVertex = new DefaultStringTypeVertex();
                defaultStringTypeVertex.setValue(node.asText());
                return new DefaultTypeTree(defaultStringTypeVertex);
            case JsonSchemaTypes.MAP:
                return parseMap(node);
            case JsonSchemaTypes.COLLECTION:
                return parseCollection(node);
            default:
                return new DefaultTypeTree();
        }
    }

    private TypeTree parseMap(final JsonNode jsonNode) throws DefinitionParsingException {
        final var mapTypeVertex = new DefaultMapTypeVertex();
        final var properties = jsonNode.get(JsonSchemaKeyNames.PROPERTIES);
        if (properties != null && properties.size() > 0) {
            final Iterator<Map.Entry<String, JsonNode>> elements = properties.fields();
            while (elements.hasNext()) {
                final Map.Entry<String, JsonNode> next = elements.next();
                final var node = next.getValue();
                //TODO: Check that type is not missing.
                final var type = node.get(JsonSchemaKeyNames.TYPE);
                if (type != null) {
                    final var typeTree = parseType(node, type.asText());
                    final var keyValueTypeEdge = new DefaultKeyValueTypeEdge(next.getKey(), typeTree);
                    mapTypeVertex.addEdge(keyValueTypeEdge);
                }
            }
        }
        return new DefaultTypeTree(mapTypeVertex);
    }

    private TypeTree parseCollection(JsonNode jsonNode) throws DefinitionParsingException {
        final var collectionTypeVertex = new DefaultCollectionTypeVertex();
        final var items = jsonNode.get(JsonSchemaKeyNames.ITEMS);
        if (items != null && items.size() > 0) {
            final var type = items.get(JsonSchemaKeyNames.TYPE);
            if (type != null) {
                final TypeTree typeTree = parseType(items, type.asText());
                collectionTypeVertex.addAllowedVertexType(typeTree);
            }
        }
        return new DefaultTypeTree(collectionTypeVertex);
    }
}
