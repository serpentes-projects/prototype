package io.serpentes.examples.definition.sources.jackson.visitors;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitable;

class ArrayVisitor extends JsonArrayFormatVisitor.Base implements JsonArrayFormatVisitor {
    @Override
    public void itemsFormat(JsonFormatVisitable handler, JavaType elementType) throws JsonMappingException {
        System.out.println("ArrayVisitor (itemsFormat): " + String.join(", ", handler.toString(), elementType.toString()));
    }

    @Override
    public void itemsFormat(JsonFormatTypes format) throws JsonMappingException {
        System.out.println("ArrayVisitor (itemsFormat): " + format);
    }
}
