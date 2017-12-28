package com.mql.jsonx.core.impl;

import com.mql.jsonx.core.JsonValue;

/**
 * @author Mehdi Maick
 */
public class JsonValueImpl implements JsonValue {

    private Type type;

    public JsonValueImpl(Type type) {
        this.type = type;
    }

    @Override
    public Type getType() {
        return type;
    }
}
