package io.serpentes.examples.definition.sources.jackson.visitors;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsonFormatVisitors.*;
import com.fasterxml.jackson.databind.ser.SerializerFactory;
import io.serpentes.api.definition.trees.TypeTree;
import io.serpentes.definition.trees.base.DefaultTypeTree;
import io.serpentes.definition.trees.base.edges.DefaultKeyValueTypeEdge;
import io.serpentes.definition.trees.base.vertices.branch.DefaultCollectionTypeVertex;
import io.serpentes.definition.trees.base.vertices.branch.DefaultMapTypeVertex;
import io.serpentes.definition.trees.base.vertices.terminal.*;

import java.util.Stack;

public class TypeVisitor extends JsonFormatVisitorWrapper.Base implements JsonFormatVisitorWrapper {

    private final SerializerFactory serializerFactory;
    private final Stack<TypeTree> stack;

    public TypeVisitor(final SerializerProvider provider, final SerializerFactory serializerFactory) {
        super(provider);
        this.serializerFactory = serializerFactory;
        this.stack = new Stack<>();
    }

    public TypeTree getTree(){
        return stack.pop();
    }

    @Override
    public JsonObjectFormatVisitor expectObjectFormat(JavaType javaType) throws JsonMappingException {
        System.out.println("TypeVisitor (Object): " + javaType);
        final var objectVisitor = new ObjectVisitor(getProvider());
        final var objectSerializer = serializerFactory.createSerializer(getProvider(), javaType);

        final var properties = objectSerializer.properties();
        final var defaultMapTypeVertex = new DefaultMapTypeVertex();
        final var defaultTypeTree = new DefaultTypeTree(defaultMapTypeVertex);
        while (properties.hasNext()) {
            final var property = properties.next();
            final var propertyType = property.getType();
            final var propertySerializer = serializerFactory.createSerializer(getProvider(), propertyType);
            propertySerializer.acceptJsonFormatVisitor(this, propertyType);
            final var propertyTypeTree = stack.pop();
            defaultMapTypeVertex.addEdge(new DefaultKeyValueTypeEdge(property.getName(), propertyTypeTree));
        }
        stack.push(defaultTypeTree);
        System.out.println("TypeVisitor (Object): =====Done=====");
        return objectVisitor;
    }

    @Override
    public JsonArrayFormatVisitor expectArrayFormat(JavaType type) throws JsonMappingException {
        System.out.println("TypeVisitor (Array): " + type);
        final var defaultCollectionTypeVertex = new DefaultCollectionTypeVertex();
        final var defaultTypeTree = new DefaultTypeTree(defaultCollectionTypeVertex);

        final var itemSerializer = serializerFactory.createSerializer(getProvider(), type.getContentType());
        itemSerializer.acceptJsonFormatVisitor(this, type.getContentType());
        final var itemTypeTree = stack.pop();

        defaultCollectionTypeVertex.addAllowedVertexType(itemTypeTree);
        stack.push(defaultTypeTree);
        System.out.println("TypeVisitor (Array): =====Done=====");
        return new ArrayVisitor();
    }
    
    @Override
    public JsonStringFormatVisitor expectStringFormat(JavaType type) throws JsonMappingException {
        System.out.println("TypeVisitor (String): " + type);
        final var typeVertex = new DefaultStringTypeVertex();
        typeVertex.setValue("string");
        stack.push(new DefaultTypeTree(typeVertex));
        return new StringVisitor();
    }

    @Override
    public JsonNumberFormatVisitor expectNumberFormat(JavaType type) throws JsonMappingException {
        System.out.println("TypeVisitor (Number): " + type);
        final var typeVertex = new DefaultFloatingPointTypeVertex();
        typeVertex.setValue("float");
        stack.push(new DefaultTypeTree(typeVertex));
        return new NumberVisitor();
    }

    @Override
    public JsonIntegerFormatVisitor expectIntegerFormat(JavaType type) throws JsonMappingException {
        System.out.println("TypeVisitor (Integer): " + type);
        final var typeVertex = new DefaultIntegerTypeVertex();
        typeVertex.setValue("integer");
        stack.push(new DefaultTypeTree(typeVertex));
        return new IntegerVisitor();
    }

    @Override
    public JsonBooleanFormatVisitor expectBooleanFormat(JavaType type) throws JsonMappingException {
        System.out.println("TypeVisitor (Boolean): " + type);
        final var typeVertex = new DefaultBooleanTypeVertex();
        typeVertex.setValue("boolean");
        stack.push(new DefaultTypeTree(typeVertex));
        return new BooleanVisitor();
    }

    @Override
    public JsonNullFormatVisitor expectNullFormat(JavaType type) throws JsonMappingException {
        System.out.println("TypeVisitor (Null): " + type);
        return null;
    }

    @Override
    public JsonAnyFormatVisitor expectAnyFormat(JavaType type) throws JsonMappingException {
        System.out.println("TypeVisitor (Any): " + type);
        // NOTE: Technically an "any" object would have all types: ["boolean", "integer", "number", etc. ].
        // However, current assertions define a null-object as type "Object", which Jackson correctly defines as "Any".
        // For the purposes of illustrating how a custom definition source might be built we can allow this deviation.
        final var typeVertex = new DefaultNullTypeVertex();
        typeVertex.setValue("null");
        stack.push(new DefaultTypeTree(typeVertex));
        return new AnyVisitor();
    }

    @Override
    public JsonMapFormatVisitor expectMapFormat(JavaType type) throws JsonMappingException {
        System.out.println("TypeVisitor (Map): " + type);

        //NOTE: Technically we would have to add the value-type as well.
        // However, for illustration purposes this is fine.
        final var typeVertex = new DefaultMapTypeVertex();
        final var typeTree = new DefaultTypeTree(typeVertex);
        stack.push(typeTree);

        return new MapVisitor();
    }
}
