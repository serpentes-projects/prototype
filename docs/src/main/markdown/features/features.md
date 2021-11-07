# Features

Serpentes offers a rich feature-set as well as enough flexibility for extension.
Often times features are undocumented, unclear or not kept up to date.
To avoid this pitfall Serpentes' features are listed as part of the documentation to ensure traceability, clarity and most importantly insight into capabilities.

To achieve this the feature-set offered by is divided into several subsets. 
The main subsets we consider are the [core](./core/core.md) and [bootstrapping](./bootstrap/bootstrap.md) features.
A short summary of both feature-sets is provided below.

## Core

The single most important feature of Serpentes is:

"The ability to retrieve input from a desired location and return it in a usable format."

To achieve this Serpentes defines 3 phases in its life-cycle:

1. [Definition](./core/definition/definition.md)
2. [Retrieval](./core/retrieval/retrieval.md)
3. [Consolidation](./core/consolidation/consolidation.md)

Firstly, in the _definition_ phase Serpentes determines _what_ input to retrieve. 
Secondly, in the _retrieval_ phase Serpentes determines _where_ and _how_ to retrieve this input.
Finally, in the _consolidation_ phase Serpentes selects _which_ input to return and _as which_ type of object.

Each phase comes with a feature-set to ensure that its goal is met.

## Implementations

Ensuring the [requirement for extensibility](../requirements/requirements.md#ensure-extensibility) of Serpentes has lead to a design where Serpentes can easily be extended by implementing its API.
Serpentes provides several [implementations](./implementations/implementations.md) of the API and their associated feature-set.

## Bootstrapping

One of the main [requirements](../requirements/requirements.md) of Serpentes is "[ensuring ease of use](../requirements/requirements.md#ensure-ease-of-use)" and "[user control](../requirements/requirements.md#ensure-user-control-over-the-configuration-that-is-produced)".
To this end Serpentes features a [bootstrapping](./bootstrap/bootstrap.md) mechanism.
This mechanism is intended to provide default implementations of concepts used by Serpentes and, to bundle these implementations.

These bundles are made available as a package and all you need to do is define them as a dependency.
Once you've retrieved a bootstrapped bundle you can start using Serpentes without having to think about anything except which configuration type you'd like to build.
As a result this mechanism "ensures ease of use" by providing you with a ready-to-go bundle with default implementations.

As for ensuring that you can control Serpentes. This mechanism allows you to create your own bootstrapped bundle.
This is useful when you're not interested in certain types of input and, you'd like to reduce the size of the package.