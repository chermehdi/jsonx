package com.mql.jsonx.core.factories;

import com.mql.jsonx.core.JsonObject;

/**
 * @author Mehdi Maick
 */
public class JsonObjecetFactory {

    public static JsonObject fromString(String json){
        json = json.trim();
        if (json.isEmpty()) return  null;
        else if (json.equals("{}")){
            return new JsonObject();
        }
        return null;
    }
}
