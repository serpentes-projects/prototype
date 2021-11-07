package io.serpentes.examples.input.sources.environment_variables.naming.camel_case;

public class CamelCaseUtils {
    private CamelCaseUtils(){}

    public static String camelize(String name){
        return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }
}
