package com.mql.jsonx;

import com.mql.jsonx.core.JsonObject;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * @author Mehdi Maick
 */
public class JsonTest {

    InputStream emptyStream = null, objectStream = null;

    @Before
    public void setUp() throws Exception {
        String root = Paths.get("").toAbsolutePath().toString();
        root += "/src/test/resources";
        emptyStream = new FileInputStream(new File(root + "/empty.json"));
        objectStream = new FileInputStream(new File(root + "/full.json"));
    }

    @Test
    public void readFrom() throws Exception {
        JsonObject object = Json.readFrom(emptyStream);
        assertNotNull(object);
        assertThat(object.toString(), is("{}"));
        assertThat(object.keys(), is(empty()));
    }

    @Test
    public void readObjectFromFile() throws Exception {
        JsonObject object = Json.readFrom(objectStream);
        assertNotNull(object);
        assertThat(object.toString(), is("{\n" +
                "  \"name\": \"hello\",\n" +
                "  \"age\": 18\n" +
                "}"));
        Set<String> set = new HashSet<>(Arrays.asList("name", "age"));
        assertThat(object.keys(), is(set));
    }

}