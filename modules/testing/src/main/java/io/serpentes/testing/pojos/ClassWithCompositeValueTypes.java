package io.serpentes.testing.pojos;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Collection;
import java.util.Map;

@Data
@EqualsAndHashCode
public class ClassWithCompositeValueTypes {
    private Collection<Object> collection;
    private Map<String, Object> map;
}
