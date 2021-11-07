package io.serpentes.api.precedence;

import java.util.LinkedHashSet;

public interface NameBasedPrecedence extends Precedence {
    @Override
    @SuppressWarnings("unchecked")
    LinkedHashSet<String> getPrecedence();
}
