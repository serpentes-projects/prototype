package io.serpentes.api.precedence;

import java.util.Set;

public interface Precedence {
    <P extends Set<E>, E> P getPrecedence();
}
