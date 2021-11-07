### Serpentes vs. 
Below you can find a feature comparison of Serpentes and Cfg4j.
Remarks have been added where I deemed it necessary.

If you catch any inaccuracies, missing features, grammatical problems etc. please let us know by creating an issue.

#### Versions
| Serpentes         | Cfg4j         |
| :---------------: | :-----------: |
| 0.0.1-PROTOTYPE   | [4.4.1](https://github.com/cfg4j/cfg4j/tree/v.4.4.1)         |

## Feature comparison
| Feature                   | Serpentes         | Cfg4j         |
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

