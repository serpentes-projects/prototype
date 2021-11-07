package io.serpentes.boot.se.input.sources.command_line.parameters;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.serpentes.boot.se.SerpentesSE;
import io.serpentes.configuration.factories.jackson.JacksonConfigurationFactory;
import io.serpentes.api.Serpentes;
import io.serpentes.input.sources.command_line.parameters.providers.cdi_extension.CommandLineParametersCDIExtension;
import io.serpentes.input.sources.environment_variables.EnvironmentVariablesInputSource;
import io.serpentes.input.sources.file_system.implementation.FileSystemInputSource;
import io.serpentes.testing.FileUtils;
import io.serpentes.testing.cdi.extentions.base.BeanIgnoringExtension;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class ClassWithCommandLineParameters {
    static final String outputClassName = System.getProperty("output.class.name");
    static final String outputTypeName = System.getProperty("output.type.name");
    static final String outputFilePath = System.getProperty("output.file");

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        final Serpentes serpentes = new SerpentesSE();
        Map<Type, Set<Annotation>> beansToIgnore = new HashMap<>();
        beansToIgnore.put(FileSystemInputSource.class, new HashSet<>());
        beansToIgnore.put(EnvironmentVariablesInputSource.class, new HashSet<>());
        serpentes.addCDIExtension(new BeanIgnoringExtension(beansToIgnore));
        // TODO: Ensure that users don't have to add this CDIExtension themselves.
        // Should be handled by the SEBootstrap module.
        serpentes.addCDIExtension(new CommandLineParametersCDIExtension(args));
        serpentes.load();

        output(serpentes);
    }

    private static void output(final Serpentes serpentes) throws ClassNotFoundException, IOException {
        final JacksonConfigurationFactory jacksonConfigurationFactory = serpentes.createConfigurationFactory(JacksonConfigurationFactory.class);
        final ObjectMapper objectMapper = new ObjectMapper();
        jacksonConfigurationFactory.setObjectMapper(objectMapper);

        Object outputObject;

        if (outputClassName != null && outputTypeName == null) {
            final Class<?> outputClass = ClassWithCommandLineParameters.class.getClassLoader().loadClass(outputClassName);
            outputObject = jacksonConfigurationFactory.create(outputClass);
        } else {
            final TypeReference<Class<?>> typeReference = new TypeReference<>() {
                @Override
                public Type getType() {
                    return new Type() {
                        @Override
                        public String getTypeName() {
                            return outputTypeName;
                        }
                    };
                }
            };
            System.err.println(outputTypeName);
            System.err.println(typeReference.getType());
            outputObject = jacksonConfigurationFactory.create(typeReference);
        }
        final File tempOutputFile = new File(outputFilePath);
        objectMapper.writeValue(tempOutputFile, outputObject);
        FileUtils.printFileContents("=== temp file contents ===", tempOutputFile, System.err);
    }

}
