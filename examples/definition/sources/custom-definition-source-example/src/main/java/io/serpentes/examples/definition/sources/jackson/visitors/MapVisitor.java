package io.serpentes.examples.definition.sources.jackson.visitors;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitable;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonMapFormatVisitor;

class MapVisitor extends JsonMapFormatVisitor.Base implements JsonMapFormatVisitor {
    @Override
    public void keyFormat(JsonFormatVisitable handler, JavaType keyType) throws JsonMappingException {
        System.out.println("MapVisitor (keyFormat): " + String.join(", ", (handler != null) ? handler.toString() : "", keyType.toString()));
    }

    @Override
    public void valueFormat(JsonFormatVisitable handler, JavaType valueType) throws JsonMappingException {
        System.out.println("MapVisitor (valueFormat): " + String.join(", ", (handler != null) ? handler.toString() : "", valueType.toString()));
    }
}
