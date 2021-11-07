package io.serpentes.examples.definition.sources.jackson;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;
import java.util.Map;

public class UserProvidedTypeDefinition {
    @JsonProperty("boolean")
    public Boolean aBoolean;
    public Integer integer;
    @JsonProperty("float")
    public Double floatingPoint;
    public String string;
    public Collection<Object> collection;
    public Map<String, String> map;
}
