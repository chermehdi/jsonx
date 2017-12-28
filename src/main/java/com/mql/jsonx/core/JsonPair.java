package com.mql.jsonx.core;

/**
 * @author Mehdi Maick
 */
public class JsonPair {

    private String key;
    private JsonValue value;

    public JsonPair(String key, JsonValue value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JsonPair jsonPair = (JsonPair) o;

        if (key != null ? !key.equals(jsonPair.key) : jsonPair.key != null) return false;
        return value != null ? value.equals(jsonPair.value) : jsonPair.value == null;
    }

    @Override
    public int hashCode() {
        int result = key != null ? key.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public JsonValue getValue() {
        return value;
    }

    public void setValue(JsonValue value) {
        this.value = value;
    }

    public String toString() {
        return this.key + ":" + this.value;
    }
}
