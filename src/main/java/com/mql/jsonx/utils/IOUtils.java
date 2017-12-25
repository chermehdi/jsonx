package com.mql.jsonx.utils;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Mehdi Maick
 */
public class IOUtils {

    /**
     * returns the content of a file from an input stream
     */
    public static String getFileContent(InputStream is) {
        StringBuffer sb = new StringBuffer();
        byte[] array = new byte[1024];
        int pt = 0;
        while (true) {
            try {
                pt = is.read(array);
                if (pt == -1) break;
                sb.append(new String(array, 0, pt));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
