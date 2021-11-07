package io.serpentes.api.input.parsers;


import io.serpentes.api.input.trees.InputTree;

public interface InputParser {
    InputTree parse(String input) throws InputParsingException;
}
