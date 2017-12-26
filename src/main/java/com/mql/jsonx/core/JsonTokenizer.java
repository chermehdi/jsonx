package com.mql.jsonx.core;

import java.io.InputStream;

/**
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

    private boolean isEmptyChar(int c) {
        char current = (char) c;
        return current == ' ' || current == '\r' || current == '\n';
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

    public JsonObject readJsonObject() {
        return null;
    }

    public static void main(String[] args) {
        JsonTokenizer in = new JsonTokenizer(System.in);
        for (int i = 0; i < 10; i++) {
            System.out.println((char) in.readCleanToken());
        }
    }
}
