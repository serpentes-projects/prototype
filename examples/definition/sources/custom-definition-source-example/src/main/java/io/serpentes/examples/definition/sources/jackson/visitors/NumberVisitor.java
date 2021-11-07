package io.serpentes.examples.definition.sources.jackson.visitors;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonNumberFormatVisitor;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonValueFormat;

import java.util.Set;

class NumberVisitor implements JsonNumberFormatVisitor {
    @Override
    public void numberType(JsonParser.NumberType type) {
        System.out.println("NumberVisitor (numberType): " + type);
    }

    @Override
    public void format(JsonValueFormat format) {
        System.out.println("NumberVisitor (format): " + format);
    }

    @Override
    public void enumTypes(Set<String> enums) {
        System.out.println("NumberVisitor (enums): " + enums);
    }
}
