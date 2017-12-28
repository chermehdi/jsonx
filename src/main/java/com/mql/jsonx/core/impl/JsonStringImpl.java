package com.mql.jsonx.core.impl;

import com.mql.jsonx.core.*;

/**
 * @author Mehdi Maick
 */
public class JsonStringImpl implements JsonString {

    private String value;

    public JsonStringImpl(String value) {
        this.value = value;
    }

    @Override
    public String getString() {
        return value;
    }

    @Override
    public Type getType() {
        return Type.STRING;
    }

    @Override
    public String toString() {
        return "\"" + value + "\"";
    }
}
