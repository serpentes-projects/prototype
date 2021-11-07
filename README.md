# Serpentes

## IMPORTANT

The framework is in its prototypical phase. This means:

- For the documentation:
  - No API reference.
  - No User Guides.
  - Unfinished feature documentation.
  - Alternative comparison is unfinished.
- For the code-base:
  - Bugs.
    - As focus was placed on architecture, understandability and primary functionality.
  - Incomplete functionality.
    - For instance, the command-line parameters input-source only handles a single parameter.
  - Duplication
    - To save time, some sections of code have been duplicated instead of refactored into a common and reusable component.
- For the examples:
  - Incomplete.
  - Not representative of all capabilities.
  - Maybe hard to grasp intent of an example.

## Introduction

As developers, we know that the applications we create often need some sort of external configuration to operate as desired.
Depending on the language used and its restrictions, the methods to obtain this configuration are often straight-forward and repetitive.
Due to this straight-forwardness many open-source libraries provide developers with the functionality to obtain configuration.

Whilst their exact approaches are not always equivalent they do boil down to the same general approach. 
There exist sources of configuration, configuration is retrieved from these sources and the result is encapsulated in a library specific configuration object.
Through getter-functions defined by this configuration object you can then retrieve the data you need.

This approach often implies the following issues:

1. Decreased maintainability.
   - The means of accessing a value in the configuration object requires a library-user to provide a key (often a string) to a getter-function. 
     These keys (strings) are hardcoded in your code-base. Due to this you may resort to introducing mechanisms for re-use that might otherwise be unnecessary. 
1. Decreased quality.
   - If a configuration object is mutable, you may use setters on the configuration object to set it to any desired type. 
     This may result in runtime-exceptions.
1. Decreased understandability.
   - As stated before, accessing a value requires a key to be passed to a getter. Clearly accessing a value does not have to occur in the same class or module.
     This leads to the scattering of keys across a users' code-base. Unless you create a centralized definition of keys, this will lead to a decrease in understandability. 
1. Decreased user-control.
   - You have no control over the type of configuration object produced, as it is often a class provided by the library.

Serpentes attempts to mitigate these issues by giving back control to you whilst ensuring ease-of-use. 
This is achieved by allowing you to extend core functionality, injecting the new functionality into the library and,
by providing a fully bootstrapped version of Serpentes which requires no additional configuration.

The following sections provide some more detail about Serpentes. 
First, the requirements for Serpentes are detailed, based on the issues highlighted in this section.
Second, the approach taken to guarantee Serpentes meets these requirements is explained.
Third, some major concepts of Serpentes are explained.
Finally, a quickstart guide and references to other relevant documentation are provided in the remaining sections. 

## Requirements

### Functional

- No need for hardcoded configuration keys.
- No need for a configuration class provided by the library/framework.
- No need for typed getters/setters.
    - E.g.:
        - `configuration.getString("some.key")`
        - `configuration.getInt("another.key")`
        - `configuration.setString("some.key")`
        - `configuration.setInt("another.key")`

### Non-functional

- Ensure maintainability of configuration.
- Ensure quality of configuration.
- Ensure understandability of configuration.
- Ensure user-control over the configuration that is produced.
- Ensure extensibility.
- Ensure a smooth user-experience.

## Approach

The way Serpentes attempts to achieve the above-mentioned requirements is as follows.
Serpentes differentiates between input, input-parsers which parse that input, input-trees which hold the parsed input and, input-sources which retrieve the
input from a particular source such as; the file-system, environment-variables and command-line parameters.

Input-sources use type-definitions to ensure that they only retrieve the desired input and, configuration-keys no longer have to be hard-coded. 
To process type-definitions Serpentes utilizes similar concepts as it does for inputs (sources, trees, etc.) save a few implementation differences. 

After retrieval, Serpentes processes the input and adds it to a configuration object.
To do this Serpentes introduces the concept of a configuration-factory which produces a desired configuration object. 
A configuration-factory is not restricted in the type of object it returns.
This allows you to select the type of configuration object that best suits their needs.

Serpentes implements these concepts to be very extensible.
You can assemble any combination of modules implementing these concepts or create custom implementations of each concept. 
This hands you the reigns by allowing you to restrict or expand the manner by which Serpentes constructs a configuration object.

## Concepts

Given the above approach the concepts mentioned are elaborated upon below.

### Definition
 
A definition or, type-definition is a data-format containing the expected structure of data. 
They are widely used in the form of schemas and as a result most developers are familiar with them and their purpose.

Serpentes uses type-definitions as a source regarding the configuration object it is attempting to construct. 
Definitions define the type of the configuration object, its properties, their types and, whether they are required or optional.

Making use of configurable type-definitions is where Serpentes differs from other configuration-libraries.

### Input

Input within Serpentes is raw data in any format from which a configuration object can be constructed.
Where other libraries/frameworks use "configuration-values" Serpentes deliberately uses "input" instead.
Configuration-values are the "end-product" so to speak, in the sense that they are the values stored in a configuration-object.
Whereas "input" is conceived as an intermediary for values which may or may not, 
end up in a configuration-object depending on for instance, precedence settings.

### Sources

At its core Serpentes uses "sources" to retrieve either type-definitions or input from a specific location. These locations range
from, but aren't limited to, the file-system, environment variables, and command-line parameters. 
You can easily define a new source to extend this list of sources.

### Parsers

Each source, of either type-definition or input, can parse the data it obtains by using parsers.
Respectively creating a type-tree, and an input-tree as an intermediate format from a retrieved type-definition or input.

### Trees

Type-trees are used by input-sources to know which input to retrieve, which keys exist and what the type of
each value should be. Input-trees store the retrieved input and are traversed by configuration-factories. 
Configuration-factories produce the eventual configuration.

### Configuration factories

Configuration factories use the input-trees that input-sources produce to create configuration. This configuration can be anything. 
For instance, a configuration-factory can traverse an input-tree and create a string, a HashMap, or a plain old object from it.

### Summary

1. Serpentes uses definition-sources to retrieve type-definitions from a given location.
1. Definition-sources **_can_** use definition-parsers to convert type-definitions into type-trees.
1. Serpentes then uses input-sources to retrieve input from a given location.
1. Input-sources **_must_** use type-trees to retrieve relevant input from a location.
1. Input-sources **_can_** but do not have to use input-parsers to convert input into input-trees.
1. Input-sources **_must_** store input-trees in an input-store using a type-vertex as a key for retrieval.
1. Serpentes uses configuration-factories to build configuration instances.
1. Configuration-factories **_should_** retrieve the previously built input-trees and convert them to a configuration instance.

## Quick-start

This quick-start assumes you've installed:
- Git
- JDK 11
- Maven
- Gradle

Run:
```
git clone https://github.com/serpentes-projects/prototype.git
gradle -p modules clean publishToMavenLocal
```

When gradle finishes this task, you can run any of the examples from [the examples directory](./examples) to explore Serpentes.

## Documentation

You can find the non-extensive documentation in [the documentation directory](./docs/src/main/markdown/docs.md).

## API-reference

T.b.a.

## Examples

Check out the [the examples directory](./examples) for examples regarding intended usage of Serpentes.

## Roadmap

For information on upcoming milestones check [the roadmap](./docs/src/main/markdown/features/roadmap.md).

## Alternatives

Serpentes may not serve your specific needs. To help you make an informed decision take a look at an overview
of [possible alternatives](./docs/src/main/markdown/alternatives/alternatives.md). 
This overview also contains comparisons between the functionality offered by Serpentes and the alternative. 
Each comparison lists the compared versions, specific features and explains, where necessary, 
core differences in their implementation.