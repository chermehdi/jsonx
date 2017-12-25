package com.mql.jsonx;

import com.mql.jsonx.core.JsonObject;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Paths;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * @author Mehdi Maick
 */
public class JsonTest {

    InputStream is = null;

    @Before
    public void setUp() throws Exception {
        String root = Paths.get("").toAbsolutePath().toString();
        root += "/src/test/resources/empty.json";
        is = new FileInputStream(new File(root));
    }

    @Test
    public void readFrom() throws Exception {
        JsonObject object = Json.readFrom(is);
        assertNotNull(object);
        assertThat(object.toString(), is("{}"));
        assertThat(object.keys(), is(empty()));
    }

}