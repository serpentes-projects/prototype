package io.serpentes.examples.definition.sources.jackson.visitors;

import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonBooleanFormatVisitor;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonValueFormat;

import java.util.Set;

public class BooleanVisitor implements JsonBooleanFormatVisitor {
    @Override
    public void format(JsonValueFormat format) {
        System.out.println("BooleanVisitor (format): " + format);
    }

    @Override
    public void enumTypes(Set<String> enums) {
        System.out.println("BooleanVisitor (enums): " + enums);
    }
}
