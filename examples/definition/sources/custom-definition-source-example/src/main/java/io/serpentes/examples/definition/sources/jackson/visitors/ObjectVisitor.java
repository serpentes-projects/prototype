package io.serpentes.examples.definition.sources.jackson.visitors;

import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitable;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;

class ObjectVisitor extends JsonObjectFormatVisitor.Base implements JsonObjectFormatVisitor {

    public ObjectVisitor(SerializerProvider serializerProvider) {
        super(serializerProvider);
    }

    @Override
    public void property(BeanProperty writer) throws JsonMappingException {
        System.out.println("ObjectVisitor: " + writer);
    }

    @Override
    public void property(String name, JsonFormatVisitable handler, JavaType propertyTypeHint) throws JsonMappingException {
        System.out.println("ObjectVisitor: " + String.join(", ", name, handler.toString(), propertyTypeHint.toString()));
    }

    @Override
    public void optionalProperty(BeanProperty writer) throws JsonMappingException {
        System.out.println("ObjectVisitor (optional): " + writer);
    }

    @Override
    public void optionalProperty(String name, JsonFormatVisitable handler, JavaType propertyTypeHint) throws JsonMappingException {
        System.out.println("ObjectVisitor (optional): " + String.join(", ", name, handler.toString(), propertyTypeHint.toString()));
    }
}
