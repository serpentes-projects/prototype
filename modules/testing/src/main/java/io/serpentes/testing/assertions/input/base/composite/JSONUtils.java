package io.serpentes.testing.assertions.input.base.composite;

import java.util.Collection;
import java.util.stream.Collectors;

public class JSONUtils {
    public static String wrapInSquareBrackets(String toWrap) {
        return "[" +
                String.join(",", toWrap) +
                "]";
    }

    public static String wrapInSquareBrackets(Collection<String> toWrap) {
        return wrapInSquareBrackets(String.join(",", toWrap));
    }

    public static String wrapInBrackets(String toWrap) {
        return "{" +
                String.join(",", toWrap) +
                "}";
    }

    public static String wrapInBrackets(Collection<String> toWrap) {
        return wrapInBrackets(String.join(",", toWrap));
    }

    public static Collection<String> toUnmodifiableStringCollection(Collection<?> collection) {
        return collection.stream().map(String::valueOf).collect(Collectors.toUnmodifiableList());
    }

    public static Collection<String> wrapInQuotationMarks(Collection<String> collection) {
        return collection.stream().map(JSONUtils::wrapInQuotationMarks).collect(Collectors.toUnmodifiableList());
    }

    public static String wrapInQuotationMarks(String string) {
        return "\"" + string + "\"";
    }

    public static String unWrapQuotationMarks(String string) {
        return string.replace("\"", "");
    }
}
