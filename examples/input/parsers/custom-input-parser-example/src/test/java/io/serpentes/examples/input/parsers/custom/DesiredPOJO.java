package io.serpentes.examples.input.parsers.custom;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;
import java.util.Map;

public class DesiredPOJO {
    @JsonProperty("null")
    public Object aNull;
    @JsonProperty("boolean")
    public Boolean aBoolean;
    public Integer integer;
    @JsonProperty("float")
    public Double aFloat;
    public String string;
    public Collection<Object> collection;
    public Map<String, Object> map;
}
