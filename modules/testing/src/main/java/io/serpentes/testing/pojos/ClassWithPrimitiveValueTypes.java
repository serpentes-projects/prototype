package io.serpentes.testing.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class ClassWithPrimitiveValueTypes {
    @JsonProperty("boolean")
    private Boolean aBoolean;
    private Integer integer;
    @JsonProperty("null")
    private Object aNull;
    @JsonProperty("float")
    private Double floatingPoint;
    private String string;
}
