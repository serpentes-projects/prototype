package io.serpentes.examples.definition.sources.jackson.visitors;

import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonStringFormatVisitor;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonValueFormat;

import java.util.Set;

class StringVisitor implements JsonStringFormatVisitor {
    @Override
    public void format(JsonValueFormat format) {
        System.out.println("StringVisitor (format): " + format);
    }

    @Override
    public void enumTypes(Set<String> enums) {
        System.out.println("StringVisitor (enums): " + enums);
    }
}
