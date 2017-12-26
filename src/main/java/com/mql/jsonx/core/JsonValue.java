package com.mql.jsonx.core;

/**
 * represents a Json value (array, object, number or string)
 *
 * @author Mehdi Maick
 */
public interface JsonValue {

    public enum Type {
        ARRAY,

        OBJECT,

        STRING,

        NUMBER,

        TRUE,

        FALSE,

        NULL
    }

    Type getType();

    String toString();

    boolean equals(Object o);

    int hashCode();

    default JsonObject asJsonObject() {
        return JsonObject.class.cast(this);
    }

    default JsonArray asJsonArray() {
        return JsonArray.class.cast(this);
    }

    default JsonString asJsonString() {
        return JsonString.class.cast(this);
    }

    default JsonNumber asJsonNumber() {
        return JsonNumber.class.cast(this);
    }
}
