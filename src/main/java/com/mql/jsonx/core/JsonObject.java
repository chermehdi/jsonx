package com.mql.jsonx.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Mehdi Maick
 */
public class JsonObject {

    private Map<String, JsonObject> object;

    public String toString() {
        return "{}";
    }

    public JsonObject() {
        object = new HashMap<>();
    }

    public Set<String> keys() {
        return object.keySet();
    }
}
