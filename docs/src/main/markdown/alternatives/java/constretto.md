# Serpentes vs. Constretto
Below you can find a feature comparison of Serpentes and Constretto.
Remarks have been added where I deemed it necessary.

If you catch any inaccuracies, missing features, grammatical problems etc. please let us know by creating an issue.

## Versions
| Serpentes         | Constretto    |
| :---------------: | :-----------: |
| 0.0.1-PROTOTYPE   | [3.0.0-BETA1](https://github.com/constretto/constretto-core/tree/constretto-3.0.0-beta-1)   |

## Feature comparison
| Feature                   | Serpentes         | Constretto    |
| :------------------------ | :---------------: | :-----------: |
| **InputSources**          |                   |               |
| Source-code               | &#9989;           | &#9989;       | 
| Environment-variables     | &#9989;           | &#10060;      | 
| FileSystem                | &#9989;           | &#9989;       |
| Custom input-sources*     | &#9989;           | &#9989;       |
| Configurable precedence*  | &#9989;           | &#10060;      |
| **InputParsers**          |                   |               |
| json                      | &#9989;           | &#9989;       |
| yaml                      | &#10060;          | &#10060;      |
| xml                       | &#10060;          | &#9989;       |
| ini                       | &#10060;          | &#9989;       |
| properties                | &#10060;          | &#9989;       |
| properties (Encrypted)    | &#10060;          | &#9989;       |
| Custom input-parsers      | &#9989;           | &#10060;      |
| Configurable precedence   | &#9989;           | &#10060;      |
| **Library configuration** |                   |               |
| Source-code (Annotations) | &#9989;           | &#10060;      |
| Source-code (Builders)    | &#10060;          | &#9989;       |
| Files                     | &#10060;          | &#10060;      |
| Environment               | &#10060;          | &#9989;       |
| **Miscellaneous**         |                   |               |
| Value injection*          | &#10060;          | &#9989;       |
| InputSource detection     | &#10060;          | &#10060;      |

## Remarks
### Custom input-sources

#### Serpentes
Serpentes enables you to create a custom InputSource to retrieve input from a particular source.

_Advantages_
1. Can be reused.
   1. InputSources in Serpentes refer to a source for input. For example the file-system, environment-variables, a http-call etc.
      They do not require direct knowledge of configuration-keys because this information has been encapsulated in a type-definition you provide. 
      This encapsulation ensures that InputSources can be reused across projects.
1. 

_Disadvantages_
1. Increased learning-curve.
   1. The creation of a custom InputSource requires knowledge of the internal structure of Serpentes. 
      You will need annotations, interfaces, a type-definition and, a configuration-store provided by Serpentes for your implementation.
      As a consequence you will need to learn what each of these do, increasing the time you need before getting started.

#### Constretto
Constretto enables you to create a custom InputSource that can retrieve a value for a specific key [ [source](https://github.com/constretto/constretto-core/tree/constretto-3.0.0-beta-1#java-objects-used-as-configuration-sources) ].

_Advantages_
1. Ease of use.
   1. Constretto's approach is arguably easier to implement. As it only requires annotating a class with the `@ConfigurationSource(basePath = "path", tag = "tag")` annotation. 
   After which Constretto detects for which configuration-keys it can retrieve values based on the names of the methods in the class.
1. Highly customizable.
   1. Constretto only requires that the return-type of the methods in an annotated class return the correct type. 
      As such there are no limits as to how you structure the rest of the implementation.

_Disadvantages_
1. Resistant to change.
   1. Since Constretto requires you to provide a base-path in the `@ConfigurationSource` annotation your code will contain strings that reference keys. Strings that will need refactoring if you decide to change your configuration structure.
1. May not be reused.
   1. Due to the usage of hardcoded base-paths the InputSource can only be reused if another library has matching keys.

#### Details
The difference between the two approaches comes down to retrieving a value from an exact key vs. attempting to retrieve input from a specific type of source. 
Due to this difference the two approaches are hard to compare. 

Serpentes does not require you to provide hardcoded key-names you wish to use for retrieval whereas Constretto does require the key-names to be hardcoded into your source-code.
Consequently, each custom input-source is specific to the application which is intended to use it.
Whereas a custom input-source in Serpentes should aim to (but doesn't have to) be reusable across applications.

### Configurable precedence
#### Constretto 
It is unclear whether Constretto has a specific precedence for InputSources. 
Constretto does offer the ability to add multiple value-sources (files) but makes no mention of their precedence when retrieving values [ [source](https://github.com/constretto/constretto-core/tree/constretto-3.0.0-beta-1#using-java-api) ].

#### Serpentes
Serpentes

### Value injection
#### Serpentes
Serpentes offers this ability by means of configuration factories.
Which allows specialized libraries to inject values if desired.

#### Constretto
Constretto offers you the ability to inject configuration values directly into a plain old object using the `@Configuration()` and `@Configure()` annotations [ [source](https://github.com/constretto/constretto-core/tree/constretto-3.0.0-beta-1#configuration-injection---annotation-based) ] .

#### Details
Serpentes enables you to inject specific configuration-values into the properties of an object using specialized libraries.
After Serpentes has completed the consolidation-phase (during which values from all InputSources have been added to a configuration store), values from that store can be read and manipulated by third-party libraries when injecting.

Third-party libraries can be utilized to achieve the desired effect. 
The reasoning behind this is that Serpentes intents to wrap itself around input-sources after which you can manipulate the consolidated input as you see fit.
Even-though Serpentes offers several reference implementations the manner by which the consolidated input itself is manipulated is entirely up to you.

