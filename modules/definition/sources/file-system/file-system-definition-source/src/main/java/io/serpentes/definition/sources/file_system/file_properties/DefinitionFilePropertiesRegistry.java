package io.serpentes.definition.sources.file_system.file_properties;


import io.serpentes.definition.sources.file_system.api.file_properties.DefinitionFileProperties;
import io.serpentes.definition.sources.file_system.annotations.file_properties.DefaultDefinitionFileProperties;
import io.serpentes.definition.sources.file_system.annotations.file_properties.SupportedDefinitionFileProperties;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DefinitionFilePropertiesRegistry {
    //TODO: Change to Instance<>.
    private final DefinitionFileProperties defaultDefinitionFileProperties;
    private final Instance<Set<DefinitionFileProperties>> supportedDefinitionFileProperties;
    //TODO: Introduce precedence.
    // This would imply adding support for multiple type-definitions which might not be a good idea.
    // Since it would introduce multiple sources of truth. Consider this and hash it out.

    @Inject
    public DefinitionFilePropertiesRegistry(@DefaultDefinitionFileProperties final DefinitionFileProperties defaultDefinitionFileProperties,
                                            @SupportedDefinitionFileProperties final Instance<Set<DefinitionFileProperties>> supportedDefinitionFileProperties) {
        this.defaultDefinitionFileProperties = defaultDefinitionFileProperties;
        this.supportedDefinitionFileProperties = supportedDefinitionFileProperties;
    }

    public String getName() {
        final List<DefinitionFileProperties> definitionFileProperties = new ArrayList<>();
        definitionFileProperties.add(defaultDefinitionFileProperties);
        for (final Set<DefinitionFileProperties> supportedDefinitionFileProperties : supportedDefinitionFileProperties) {
            definitionFileProperties.addAll(supportedDefinitionFileProperties);
        }

        return definitionFileProperties.get(definitionFileProperties.size() - 1).getName();
    }
}
