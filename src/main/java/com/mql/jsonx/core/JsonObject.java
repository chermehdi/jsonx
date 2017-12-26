package com.mql.jsonx.core;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * the base object of the library, a Json Object is a java representation of the Javascript Object Notation (json)
 * it abstracts the major functionality you expect of a basic json object
 *
 * @author Mehdi Maick
 */
public interface JsonObject extends Map<String, JsonValue>, JsonStructure {

    JsonArray getJsonArray(String key);

    JsonString getJsonString(String key);

    JsonObject getJsonObject(String key);

    JsonNumber getJsonNumber(String key);

    String getString(String key);

    String getString(String key, String defaultValue);

    boolean isNull(String key);

    Boolean getBoolean(String key);

    boolean putJson(String key, JsonValue value);

    boolean putString(String key, String value);
}