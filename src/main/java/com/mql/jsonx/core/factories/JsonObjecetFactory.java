package com.mql.jsonx.core.factories;

import com.mql.jsonx.core.JsonObject;
import com.mql.jsonx.core.JsonPair;
import com.mql.jsonx.core.JsonTokenizer;
import com.mql.jsonx.core.impl.JsonObjectImpl;
import com.mql.jsonx.utils.IOUtils;

import java.io.InputStream;

/**
 * @author Mehdi Maick
 */
public class JsonObjecetFactory {

    public static JsonObject fromString(String json) {
        return fromInputStream(IOUtils.getInputStreamFromString(json));
    }

    public static JsonObject fromInputStream(InputStream is) {
        JsonTokenizer tokenizer = new JsonTokenizer(is);
        JsonObject ret = new JsonObjectImpl();
        try {
            while (true) {
                JsonPair pair = tokenizer.readJsonPair();
                ret.putJsonPair(pair);
            }
        } catch (Exception e) {
            return ret;
        }
    }
}
