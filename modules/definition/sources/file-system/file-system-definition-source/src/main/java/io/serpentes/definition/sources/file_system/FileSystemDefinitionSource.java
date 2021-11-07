package io.serpentes.definition.sources.file_system;

import io.serpentes.api.definition.parsers.DefinitionParsers;
import io.serpentes.api.definition.sources.DefinitionSource;
import io.serpentes.api.definition.trees.TypeTree;
import io.serpentes.definition.sources.file_system.file_properties.DefinitionFilePropertiesRegistry;
import io.serpentes.definition.sources.file_system.file_paths.DefinitionDirectories;
import io.serpentes.definition.trees.base.DefaultTypeTree;
import jakarta.inject.Inject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

//TODO: FileSystemDefinitionSource is a duplicate of FileSystemInputSource. This duplication should be addressed.
public class FileSystemDefinitionSource implements DefinitionSource {
    private final DefinitionDirectories definitionDirectories;
    private final DefinitionFilePropertiesRegistry definitionFileProperties;
    private final DefinitionParsers definitionParsers;

    @Inject
    public FileSystemDefinitionSource(final DefinitionDirectories definitionDirectories, final DefinitionFilePropertiesRegistry definitionFileProperties, final DefinitionParsers definitionParsers) {
        this.definitionDirectories = definitionDirectories;
        this.definitionFileProperties = definitionFileProperties;
        this.definitionParsers = definitionParsers;
    }

    @Override
    public TypeTree createTypeTree() {
        //TODO: Currently this will result in only one path, adjust it so that configuration will be retrieved from multiple paths.
        final var path = Paths.get(definitionDirectories.getPath().toString(), definitionFileProperties.getName());
        try {
            return definitionParsers.parse(Files.readString(path));
        } catch (IOException e) {
            //TODO: Handle this.
        }
        //TODO:Throw an exception instead of returning this?
        return new DefaultTypeTree();
    }
}
