package io.serpentes.input.sources.command_line.parameters.providers.cdi_extension;

import jakarta.enterprise.event.Observes;
import jakarta.enterprise.inject.spi.AfterBeanDiscovery;
import jakarta.enterprise.inject.spi.Bean;
import jakarta.enterprise.inject.spi.BeanManager;
import jakarta.enterprise.inject.spi.Extension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandLineParametersCDIExtension implements Extension {
    private ArrayList<String> parameters = new ArrayList<>();

    //CDI Requires default constructor.
    public CommandLineParametersCDIExtension() {
    }

    public CommandLineParametersCDIExtension(String[] parameters) {
        final List<String> strings = Arrays.asList(parameters);
        this.parameters = new ArrayList<>();
        this.parameters.addAll(strings);
    }

    void afterBeanDiscovery(@Observes AfterBeanDiscovery abd, BeanManager bm) {
        Class<ArrayList<String>> clazz = ClassUtils.castClass(ArrayList.class);
        var at = bm.createAnnotatedType(clazz);
        final var injectionTarget = bm.createInjectionTarget(at);
        Bean<ArrayList<String>> commandLineArgumentsBean = new CommandLineParametersBean(this.parameters, injectionTarget);
        abd.addBean(commandLineArgumentsBean);
    }
}
