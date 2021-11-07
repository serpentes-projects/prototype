package io.serpentes.configuration.factories.json;

import java.util.Collection;
import java.util.stream.Collectors;

public class JSONUtils {
    public static final String COMMA = ",";
    public static final String LEFT_SQUARE_BRACKET = "[";
    public static final String RIGHT_SQUARE_BRACKET = "]";
    public static final String LEFT_BRACKET = "{";
    public static final String RIGHT_BRACKET = "}";
    public static final String QUOTATION_MARK = "\"";
    public static final String COLON = ":";

    private JSONUtils(){}

    public static String wrapInSquareBrackets(final String toWrap) {
        return wrapIn(toWrap, LEFT_SQUARE_BRACKET, RIGHT_SQUARE_BRACKET);
    }

    public static StringBuilder wrapInSquareBrackets(final StringBuilder toWrap) {
        return wrapIn(toWrap, LEFT_SQUARE_BRACKET, RIGHT_SQUARE_BRACKET);
    }

    public static String wrapInSquareBrackets(final Collection<String> toWrap) {
        return wrapInSquareBrackets(String.join(COMMA, toWrap));
    }

    public static String wrapInBrackets(final String toWrap) {
        return wrapIn(toWrap, LEFT_BRACKET, RIGHT_BRACKET);

    }

    public static StringBuilder wrapInBrackets(final StringBuilder toWrap) {
        return wrapIn(toWrap, LEFT_BRACKET, RIGHT_BRACKET);
    }

    public static StringBuilder wrapIn(final StringBuilder toWrap, final String begin,final String end) {
        toWrap.insert(0, begin);
        toWrap.insert(toWrap.length(), end);
        return toWrap;
    }

    public static String wrapIn(final String toWrap,final String begin, final String end) {
        return begin + toWrap + end;
    }

    public static String wrapInBrackets(final Collection<String> toWrap) {
        return wrapInBrackets(String.join(COMMA, toWrap));
    }

    public static Collection<String> toUnmodifiableStringCollection(final Collection<?> collection) {
        return collection.stream().map(String::valueOf).collect(Collectors.toUnmodifiableList());
    }

    public static Collection<String> wrapInQuotationMarks(final Collection<String> collection) {
        return collection.stream().map(JSONUtils::wrapInQuotationMarks).collect(Collectors.toUnmodifiableList());
    }

    public static String wrapInQuotationMarks(final String string) {
        return QUOTATION_MARK + string + QUOTATION_MARK;
    }

}
