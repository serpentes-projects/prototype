package io.serpentes.examples.input.sources.custom;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NPMPackage {
  public String name;
  public String version;
  public String license;
  @JsonProperty("private")
  public Boolean privatePackage;
  public String main;
  public Map<String, String> engines;
  public Boolean sideEffects;
  public Map<String, String> scripts;
  public Map<String, String> devDependencies;
}
