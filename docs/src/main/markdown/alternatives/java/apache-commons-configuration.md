### Serpentes vs. Apache Commons Configuration
Below you can find a feature comparison of Serpentes and Apache Commons Configuration.
Remarks have been added where I deemed it necessary.

If you catch any inaccuracies, missing features, grammatical problems etc. please let us know by creating an issue.

#### Versions
| Serpentes         | Apache Commons Configuration      |
| :---------------: | :-------------------------------: |
| 0.0.1-PROTOTYPE   | [2.7](https://github.com/apache/commons-configuration/tree/rel/commons-configuration-2.7) |

## Feature comparison
| Feature                   | Serpentes         | Apache Commons Configuration      |
| :------------------------ | :---------------: | :-------------------------------: |
| **InputSources**          |                   |                                   |
| Source-code*              | &#9989;           | &#9989;   [[1]][1]                |
| Environment-variables     | &#9989;           | &#10060;  [[2]][2]                |
| FileSystem                | &#9989;           | &#9989;   [[3]][3]                |
| Commandline parameters    | &#9989;           | &#10060;                          |
| JNDI*                     | &#10060;          | &#9989;   [[4]][4]                |
| JDBC*                     | &#10060;          | &#9989;   [[5]][5]                |
| System Properties*        | &#10060;          | &#9989;   [[6]][6]                |
| Custom input-sources*     | &#9989;           | &#9989;                           | TODO: Check this.
| Configurable precedence*  | &#9989;           | &#9989;   [[7]][7]                |
| **InputParsers**          |                   |                                   |
| json                      | &#9989;           | &#9989;   [[8]][8]                |
| yaml                      | &#10060;          | &#9989;   [[9]][9]                |
| xml                       | &#10060;          | &#9989;   [[10]][10]              |
| ini                       | &#10060;          | &#9989;   [[11]][11]              |
| properties                | &#10060;          | &#9989;   [[12]][12]              |
| properties (Encrypted)    | &#10060;          | &#9989;   [[13]][13]              |
| Custom input-parsers      | &#9989;           | &#10060;                          | TODO: Check this.
| Configurable precedence*  | &#9989;           | &#10060;  [[7]][7]                |
| **Library configuration** |                   |                                   |
| Source-code (CDI)         | &#9989;           | &#10060;                          |
| Source-code (Builders)    | &#10060;          | &#9989;   [[14]][14]              | 
| Files                     | &#10060;          | &#10060;                          | 
| Environment               | &#10060;          | &#10060;                          |
| **Miscellaneous**         |                   |                                   |
| Value injection           | &#10060;          | &#10060;                          |
| InputSource detection     | &#10060;          | &#10060;                          |
| Variable interpolation*   | &#10060;          | &#9989;       | https://commons.apache.org/proper/commons-configuration/userguide/howto_basicfeatures.html#Basic_features_and_AbstractConfiguration 
| Customizing interpolation*| &#10060;          | &#9989;       |
| Using Expressions*        | &#10060;          | &#9989;       |
| Data type conversions*    | &#10060;          | &#9989;       |
| Customizing conversions*  | &#10060;          | &#9989;       |
| Encoded Properties*       | &#10060;          | &#9989;       |
| Source reloading*         | &#10060;          | &#9989;       | https://commons.apache.org/proper/commons-configuration/userguide/howto_reloading.html
| Composite Configuration*  | &#9989;           | &#9989;       | https://commons.apache.org/proper/commons-configuration/userguide/howto_compositeconfiguration.html#Composite_Configuration_Details
| Combination Strategies*   | &#10060;          | &#9989;       | https://commons.apache.org/proper/commons-configuration/userguide/howto_combinedconfiguration.html#Combined_Configuration
| Creating Beans*           | &#10060;          | &#9989;       | https://commons.apache.org/proper/commons-configuration/userguide/howto_beans.html#Declaring_and_Creating_Beans

### Remarks
#### InputSources 
##### Source-code
###### Serpentes
Serpentes does not offer a standard mechanism to, retrieve or set the value of configuration objects from your source-code.
The reason for this is that I want to avoid hard-coded key-paths as much as possible.
These key-paths are subject to change the moment you deprecate a certain configuration and will require you to adjust your code.

Instead, Serpentes allows you to make the decision for yourself. It does so through 2 concepts input-sources and configuration-factories.
The former enable you to create a custom input-source which will obey input-source precedence and allow you to inject whatever values you like.
However, it is restricted as it only allows you to set values before a configuration object is instantiated and, as indicated before, depending on the input-source precedence it is not guaranteed to be the value used by a configuration object.

The latter enables you to create your own configuration-factory through which you could instantiate configuration objects of your own design.
Since that design is completely up to you, it could be constructed in a manner that allows you to set configuration values from your source-code.

Alternatively you could use the Jackson configuration-factory which allows you to provide a POJO of from which Jackson will create a new instance, based on the input retrieved by input-sources you've enabled.
The POJO is fully under your control which means that you can make it mutable, immutable or, a combination where some values are mutable and others immutable. 
Furthermore, there are no hard-coded key-paths in this approach, which means that if you refactor the configuration object (POJO in this case) an IDE can perform that refactoring for you.
Not to mention that together with the use of type-definitions using the Jackson configuration-factory gives you type-safety, as conversion will be handled by Jackson and the type is guaranteed to remain the same at run-time.

In short, Serpentes does not offer a mechanism to set configuration values from source-code "out-of-the-box", it is however extremely easy to create a mechanism that allows you to do this.
This is exactly what Serpentes aims to do, provide you with the freedom to make your own choices with only a few restrictions.

_Advantages_

1. Flexibility
   - Due to the use of input-sources and configuration-factories Serpentes is more flexible in the manner it handles configuration objects. 
     In turn this gives users more flexibility in their decision-making process regarding input retrieval from source-code.

_Disadvantages_

1. Steeper learning curve.
   - There is no standard mechanism so, a user will either need to create their own, or familiarize themselves with a mechanism provided by an implementation provide by Serpentes.

###### Apache Commons Configuration
The Apache Commons Configuration library allows a user to retrieve/set configuration values from source-code through a well-known mechanism, key-paths [[1]][1].
This mechanism works by providing a set-function of the configuration object with a string (often hard-coded), which is parsed according to a specific format, usually of the form "parentKey.childKey" and, a value to set for that path.
Subsequent calls to a get-function using the same key-path as a parameter will retrieve the updated value.

This approach is well-known, easy to understand but not without its downsides. For instance, when you wish to change a key-path you need to make sure it is changed everywhere in your source-code. 
This can be done by using constants, and then changing those, however, you will receive no compiler warnings if an unknown path is used, only at run-time will you receive a warning.

Furthermore, most of the configuration-libraries provide setters and getters for each data-type, setInt, getInt, setString, getString, etc.
This means that type-safety is not guaranteed as a value's type may change at run-time. 
While you may get some help from the IDE and compiler when changing a key-path, type-safety is a different story.

_Advantages_
1. Little to no learning curve.
   - This approach is well-known and needs little explanation.
1. Dynamic typing.
   - It may be desirable in some implementations to change the type of configuration value at run-time.

_Disadvantages_
1. No type-safety.
   - Type-safety is not guaranteed and, a user does not have the option to force it.
1. Resistant to change.
   - Hard-coded key-paths and, typed getters/setters make the setting of configuration values from source-code resistant to change.

#### JNDI
###### Serpentes
Serpentes current does not offer an input-source capable of accessing a JNDI-service. 
However, constructing a custom input-source that retrieves objects from a JNDI-service is possible.

_Advantages_
1. Flexibility.
   - The ability to easily construct your own input-source means that Serpentes does not restrict you in the manner in which you wish to access the JNDI-service.

_Disadvantages_
1. Higher learning-curve.
   - No input-source is available as such, you will need to create one. 
     This will require you to familiarize yourself not only with the JNDI API but also with the Serpentes API. 

###### Apache Commons Configuration
The Apache Commons Configuration library allows you to access a JNDI-service through the JNDIConfiguration [[4]][4].
The configuration enables a user to retrieve objects based on a key-path, this has several down-sides but to avoid re-iteration them here, please see the remarks concerning the "Source-code input-source".

_Advantages_
1. Consistency
   - The JNDIConfiguration is consistent with the rest of the configuration objects of the library. 
     Making it easy to understand with little extra knowledge requirements.

_Disadvantages_
1. Please see the remarks on key-paths in the "Source-code input-source" section.

#### JDBC
###### Serpentes
Serpentes current does not offer an input-source capable of accessing a JDBC-database.
However, constructing a custom input-source that retrieves data from a JDBC-database is possible.

_Advantages_
1. Flexibility.
    - The ability to easily construct your own input-source means that Serpentes does not restrict you in the manner in which you wish to access the JDBC-database.

_Disadvantages_
1. Higher learning-curve.
    - No input-source is available. As such, you will need to create one.
      This will require you to familiarize yourself not only with the JDBC API but also with the Serpentes API.

###### Apache Commons Configuration
The Apache Commons Configuration library allows you to access a JDBC-database through the DatabaseConfiguration [[5]][5].
The configuration enables a user to retrieve data based on a key-path, this has several down-sides but to avoid re-iteration them here, please see the remarks concerning the "Source-code input-source".
To connect, database information must be added to the configuration as you would for a regular JDBC-connection.

_Advantages_
1. Consistency
    - The DatabaseConfiguration is consistent with the rest of the configuration objects of the library.
      Making it easy to understand with little extra knowledge requirements.

_Disadvantages_
1. Please see the remarks on key-paths in the "Source-code input-source" section.

#### System Properties
###### Serpentes
Serpentes current does not offer an input-source capable of accessing system properties.
However, constructing a custom input-source that retrieves input from system properties is possible.

_Advantages_
1. Flexibility.
    - The ability to easily construct your own input-source means that Serpentes does not restrict you in the manner in which you wish to manipulate system properties before adding them to an input-tree.

_Disadvantages_
1. Higher learning-curve.
    - No input-source is available. As such, you will need to create one.
      This will require you to familiarize yourself not only with the System Properties API but also with the Serpentes API.

###### Apache Commons Configuration
The Apache Commons Configuration library allows you to access system properties through the SystemConfiguration [[6]][6].
The configuration enables a user to retrieve data based on a key-path, this has several down-sides but to avoid re-iteration them here, please see the remarks concerning the "Source-code input-source".
To connect, database information must be added to the configuration as you would for a regular JDBC-connection.

_Advantages_
1. Consistency
    - The DatabaseConfiguration is consistent with the rest of the configuration objects of the library.
      Making it easy to understand with little extra knowledge requirements.

_Disadvantages_
1. Please see the remarks on key-paths in the "Source-code input-source" section.


## Configurable precedence
###### Serpentes
Serpentes allows a user to define precedence for input-sources. 
The precedence determines the order in which input will be retrieved from input-sources handing control over to the user such that they may determine importance of each input-source.
It should be noted that the boostrap module for Serpentes sets a default precedence. However, this precedence can be overwritten if desired.

_Advantages_
1. Easy to understand.
   - Due to the explicit definition of the precedence between input-sources it is very easy to understand which input-source takes precedence over another.
1. Flexibility.
   - A user can easily change the precedence between input-sources to suit their needs.

_Disadvantages_
1. CDI-based
   - The prototype only provides a CDI-mechanism to inject configuration into the library. This mechanism does not account for desired restrictions. 
     In the sense that, if another library uses Serpentes, configuration meant for that library will be discovered and injected by your instance of Serpentes.
     A solution to that would be to require a project-wide custom annotation which would restrict configuration to your project only.

###### Apache Commons Configuration
The Apache Commons Configuration library offers users the ability to define NodeCombiners when combining Configurations in a CombinedConfiguration [[7]][7].
The CombinedConfiguration applies a given NodeCombiner to the Configurations a user has added in the order they were added.
In short a CombinedConfiguration enables a user to define precedence by adding Configurations in a specific order.
A NodeCombiner is then used to override, merge or unite the Configurations.

_Advantages_
1. Easy to understand.
   - Using the order in which the user adds Configurations to determine precedence is easy to understand as most programmers are familiar with the concept.
1. User control.
   - Using individual Configurations allows the user to have a finer grained level of control over precedence.

_Disadvantages_
1. Complexity.
  - When the number of configurations increases, the kind of precedence implemented by Apache Commons can get complex rather quickly.
    This is, in part, due to the finer-grained level of control a user has. 

#### Library configuration
##### Library configuration


[1]:  https://commons.apache.org/proper/commons-configuration/userguide/overview.html#Using_Configuration
[2]:  https://commons.apache.org/proper/commons-configuration/apidocs/org/apache/commons/configuration2/EnvironmentConfiguration.html
[3]:  https://commons.apache.org/proper/commons-configuration/apidocs/org/apache/commons/configuration2/FileBasedConfiguration.html
[4]:  https://commons.apache.org/proper/commons-configuration/apidocs/org/apache/commons/configuration2/JNDIConfiguration.html
[5]:  https://commons.apache.org/proper/commons-configuration/apidocs/org/apache/commons/configuration2/DatabaseConfiguration.html
[6]:  https://commons.apache.org/proper/commons-configuration/apidocs/org/apache/commons/configuration2/SystemConfiguration.html
[7]:  https://commons.apache.org/proper/commons-configuration/userguide/howto_combinedconfiguration.html#Combined_Configuration
[8]:  https://commons.apache.org/proper/commons-configuration/apidocs/org/apache/commons/configuration2/JSONConfiguration.html
[9]:  https://commons.apache.org/proper/commons-configuration/apidocs/org/apache/commons/configuration2/YAMLConfiguration.html
[10]: https://commons.apache.org/proper/commons-configuration/userguide/howto_xml.html
[11]: https://commons.apache.org/proper/commons-configuration/apidocs/org/apache/commons/configuration2/INIConfiguration.html
[12]: https://commons.apache.org/proper/commons-configuration/userguide/howto_properties.html
[13]: https://commons.apache.org/proper/commons-configuration/userguide/howto_basicfeatures.html#Encoded_Properties
[14]: https://commons.apache.org/proper/commons-configuration/userguide/howto_builders.html