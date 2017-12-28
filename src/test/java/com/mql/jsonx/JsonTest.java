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

    InputStream emptyStream = null, objectStream = null, objectWithInteger = null;

    @Before
    public void setUp() throws Exception {
        String root = Paths.get("").toAbsolutePath().toString();
        root += "/src/test/resources";
        emptyStream = new FileInputStream(new File(root + "/empty.json"));
        objectStream = new FileInputStream(new File(root + "/full.json"));
        objectWithInteger = new FileInputStream(new File(root + "/object.json"));
    }

    @Test
    public void readFrom() throws Exception {
        JsonObject object = Json.readFrom(emptyStream);
        assertNotNull(object);
        assertThat(object.toString(), is("{}"));
        assertThat(object.keySet(), is(empty()));
    }

    @Test
    public void readObjectFromFile() throws Exception {
        JsonObject object = Json.readFrom(objectStream);
        assertNotNull(object);
        assertThat(object.toString(), is("{\n" +
                "  \"name\" : \"hello\",\n" +
                "  \"age\" : \"18\"\n" +
                "}")); // TODO the to String method does not guaranty key order
        Set<String> set = new HashSet<>(Arrays.asList("name", "age"));
        assertThat(object.keySet(), is(set));
    }
    
    @Test
    public void readObjectFromFileContainingIntegerValue() throws Exception {
    	JsonObject object = Json.readFrom(objectWithInteger);
    	assertNotNull(object);
    	assertThat(object.toString(), is("{\n" +
                "  \"name\" : \"hisham\",\n" +
                "  \"age\" : 18\n" +
                "}"));
    	Set<String> set = new HashSet<>(Arrays.asList("name", "age"));
    	assertThat(object.keySet(), is(set));
    }

}