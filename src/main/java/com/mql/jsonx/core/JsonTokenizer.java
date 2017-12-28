package com.mql.jsonx.core;

import com.mql.jsonx.core.impl.JsonStringImpl;

import java.io.InputStream;

/**
 * read and tokenize json streams
 *
 * @author Mehdi Maick
 */
public class JsonTokenizer {

    private static final int DEFAULT_BUFFER_SIZE = 1 << 10;

    private InputStream is;

    private byte[] buff;

    private int readPtr, size;

    private boolean eof = false;

    public JsonTokenizer(InputStream is) {
        this(is, DEFAULT_BUFFER_SIZE);
    }

    public JsonTokenizer(InputStream is, int bufferSize) {
        this.is = is;
        this.buff = new byte[bufferSize];
        this.readPtr = 0;
        this.size = 0;
    }


    public int read() {
        fillBuffer();
        if (!eof) {
            return (char) buff[readPtr++];
        } else {
            throw new RuntimeException("End Of File reached !");
        }
    }

    public int readCleanToken() {
        int c;
        while (true) {
            c = read();
            if (c < 0) throw new RuntimeException("End Of File reached !");
            if (!isEmptyChar(c)) return c;
        }
    }

    public int peakCleanToken() {
        fillBuffer();
        if (!eof) {
            int c;
            int cur = readPtr;
            while (true) {
                if (cur >= size) fillBuffer();
                c = buff[cur];
                if (c < 0) throw new RuntimeException("End Of File reached !");
                if (isEmptyChar(c)) ++cur;
                else return (char) c;
            }
        } else {
            throw new RuntimeException("End Of File reached !");
        }
    }

    /**
     * returns true if the passed character is an empty character
     *
     * @param c the target variable
     */
    private boolean isEmptyChar(int c) {
        return c == ' ' || c == '\r' || c == '\n' || c == '\t';
    }

    private void fillBuffer() {
        if (readPtr < size) return;
        try {
            size = is.read(buff);
            if (size < 0) {
                this.eof = true;
            }
            readPtr = 0;
        } catch (Exception e) {
            this.eof = true;
        }
    }

    // TODO
    public JsonObject readJsonObject() {
        return null;
    }

    public JsonPair readJsonPair() {
        String key = readKey();
        JsonValue value = readJsonValue();
        return new JsonPair(key, value);
    }

    public JsonString readJsonString() {
        int c;
        StringBuffer sb = new StringBuffer();
        boolean isOpenStr = true;
        while (true) {
            if (isOpenStr) {
                c = read();
                if (c == '"') {
                    if (peakCleanToken() != ',' && peakCleanToken() != '}') formatException();
                    return new JsonStringImpl(sb.toString());
                } else {
                    sb.append((char) c);
                }
            } else {
                c = readCleanToken();
                if (c == '"') {
                    isOpenStr = true;
                }
            }
        }
    }

    // TODO: must read any JSON value
    private JsonValue readJsonValue() {
        int c = peakCleanToken();
        if (c == ':') {
            c = readCleanToken();
            c = readCleanToken();
        }
        switch (c) {
            case '"':
                return readJsonString();
            default:
                return null;
        }
    }

    private String readKey() {
        int c;
        int peek = peakCleanToken();
        if (peek == ',') {
            read(); // consume the comma
        }
        if (peek == '}') {
            formatException();
        }
        StringBuffer sb = new StringBuffer();
        boolean isOpenBrace = false, isOpenStr = false;
        while (true) {
            if (isOpenStr) {
                c = read();
                if (c == '"') {
                    c = peakCleanToken();
                    if (c != ':') formatException();
                    return sb.toString();
                }
                sb.append((char) c);
            } else {
                c = read();
                if (c == '"') isOpenStr = true;
            }
        }
    }


    private void formatException() {
        throw new RuntimeException("The Object is not correctly formatted");
    }

    public String readKeyValuePair() {
        StringBuffer sb = new StringBuffer();
        int c;
        boolean open = false;
        c = readCleanToken();
        while (true) {
            if (c == '{' && open) throw new RuntimeException("not supported");
            if (c == '{' && !open) {
                open = true;
                c = readCleanToken();
                continue;
            }
            if (c == ',' || c == '}') break;
            sb.append((char) c);
            c = read();
        }
        return sb.toString();
    }
}
