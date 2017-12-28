package com.mql.jsonx.core.impl;

import com.mql.jsonx.Json;
import com.mql.jsonx.core.*;

import java.io.ByteArrayInputStream;
import java.util.*;

import static com.mql.jsonx.Json.*;

/**
 * @author Mehdi Maick
 */
public class JsonObjectImpl implements JsonObject {

    private Map<String, JsonValue> internal;

    private int indentFactor = 2;
    private final String EMPTY_OBJECT_STRING = "{}";

    public JsonObjectImpl() {
        internal = new HashMap<>();
    }

    @Override
    public JsonArray getJsonArray(String key) {
        return null;
    }

    @Override
    public JsonString getJsonString(String key) {
        return null;
    }

    @Override
    public JsonObject getJsonObject(String key) {
        return null;
    }

    @Override
    public JsonNumber getJsonNumber(String key) {
        return null;
    }

    @Override
    public String getString(String key) {
        return null;
    }

    @Override
    public String getString(String key, String defaultValue) {
        return null;
    }

    @Override
    public boolean isNull(String key) {
        return false;
    }

    @Override
    public Boolean getBoolean(String key) {
        return null;
    }

    @Override
    public boolean putJson(String key, JsonValue value) {
        return false;
    }

    @Override
    public boolean putString(String key, String value) {
        boolean ret = false;
        if (internal.containsKey(key)) ret = true;
        internal.put(key, new JsonStringImpl(value));
        return ret;
    }

    @Override
    public boolean putJsonPair(JsonPair pair) {
        String key = pair.getKey();
        JsonValue value = pair.getValue();
        boolean ret = false;
        if (internal.containsKey(key)) ret = true;
        internal.put(key, value);
        return ret;
    }

    @Override
    public Set<String> keySet() {
        return internal.keySet();
    }

    @Override
    public String representation(int indent) {
        List<String> keys = new ArrayList<>(keySet());
        if (keys.size() == 0) return EMPTY_OBJECT_STRING;
        StringBuilder sb = new StringBuilder("{");
        for (int i = 0; i < keys.size(); i++) {
            if (i > 0)
                sb.append(',');
            sb.append('\n');
            indent(indent, sb);
            sb.append("\"").append(keys.get(i)).append("\"").append(" : ");
            if (internal.get(keys.get(i)) instanceof JsonObject) {
                sb.append(((JsonObject) internal.get(keys.get(i))).representation(indent + indentFactor));
            } else {
                sb.append(internal.get(keys.get(i)));
            }
        }
        indent(indent - indentFactor, sb);
        return sb.append("\n}").toString();
    }

    private void indent(int indent, StringBuilder sb) {
        for (int i = 0; i < indent; i++) {
            sb.append(' ');
        }
    }

    @Override
    public Type getType() {
        return null;
    }

    public String toString() {
        return representation(indentFactor);
    }

}
