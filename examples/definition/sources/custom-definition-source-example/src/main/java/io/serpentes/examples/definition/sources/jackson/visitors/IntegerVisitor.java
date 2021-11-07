package io.serpentes.examples.definition.sources.jackson.visitors;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonIntegerFormatVisitor;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonValueFormat;

import java.util.Set;

class IntegerVisitor implements JsonIntegerFormatVisitor {

    @Override
    public void numberType(JsonParser.NumberType type) {
        System.out.println("IntegerVisitor (numberType): " + type);
    }

    @Override
    public void format(JsonValueFormat format) {
        System.out.println("IntegerVisitor (format): " + format);
    }

    @Override
    public void enumTypes(Set<String> enums) {
        System.out.println("IntegerVisitor (enums): " + enums);
    }
}
