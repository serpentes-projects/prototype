# Alternatives
Even-though it would be very much appreciated if you use Serpentes it was built to help you.
As such, if Serpentes is lacking a feature you need and there are alternatives that do offer that feature, you should know.
That way you can choose the library that is most suited to your needs.

To help you make a decision the list below offers possible alternatives and compares the features each library offers with Serpentes.

## Java

### Approach
I compiled this list of comparisons as follows:
- I compare specific versions only.
  - I list which versions I compare in each comparison.
- I compare based on documented features. 
  - I do not list undocumented features.
- I add remarks to a comparison if a feature has a significant difference in either implementation or usage.

### Possible alternatives
A non-exhaustive list of alternatives to Serpentes:
- Apache Commons Configuration [ [repository](https://github.com/apache/commons-configuration) ]
- Constretto [ [repository](https://github.com/constretto/constretto-core) ]
- Owner [ [repository](https://github.com/lviggiano/owner) ]
- Cfg4j [ [repository](https://github.com/cfg4j/cfg4j) ]
- Lightbend [ [repository](https://github.com/lightbend/config) ]
- DotEnv [ [repository](https://github.com/shyiko/dotenv) ]
- MicroConfig [ [repository](https://github.com/microconfig/microconfig) ]
- Configure8 [ [repository](https://github.com/daviddenton/configur8) ]
- Apache DeltaSpike [ [docs](https://deltaspike.apache.org/documentation/configuration.html), [repository](https://github.com/apache/deltaspike/tree/deltaspike-1.9.5) ]

## Terms
### InputSources
Where does the library retrieve configuration values from?

### InputParsers
Which data-formats can the library parse?

### Library configuration
How can the library itself be configured?

### Miscellaneous
#### Value injection
The ability of a library to inject configuration values into a plain old object.

#### InputSource detection
The ability of the library to automatically detect InputSources without a user's direction.
For most libraries this means automatic detection of a file based on known characteristics of the project-environment.