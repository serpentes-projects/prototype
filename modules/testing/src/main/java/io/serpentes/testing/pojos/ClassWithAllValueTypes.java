package io.serpentes.testing.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Collection;
import java.util.Map;

@Data
@EqualsAndHashCode
public class ClassWithAllValueTypes {
    @JsonProperty("boolean")
    private Boolean aBoolean;
    private Integer integer;
    @JsonProperty("null")
    private Object aNull;
    @JsonProperty("float")
    private Double floatingPoint;
    private String string;
    private Collection<Object> collection;
    private Map<String, Object> map;
}