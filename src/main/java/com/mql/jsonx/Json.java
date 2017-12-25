package com.mql.jsonx;

import com.mql.jsonx.core.JsonObject;
import com.mql.jsonx.core.factories.JsonObjecetFactory;
import com.mql.jsonx.utils.IOUtils;

import java.io.InputStream;

/**
 * @author Mehdi Maick
 */
public final class Json {

    private Json() {
        throw new UnsupportedOperationException("cannot instantiate the Json");
    }

    /**
     * reads a JsonObject from a given input stream
     *
     * @param is target input stream
     * @return the read JsonObject
     */
    public static JsonObject readFrom(InputStream is) {
        String fileContent = IOUtils.getFileContent(is);
        return JsonObjecetFactory.fromString(fileContent);
    }
}
