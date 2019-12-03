package net.sasconul.helpers;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;

import static java.lang.ClassLoader.getSystemResource;
import static java.lang.ClassLoader.getSystemResourceAsStream;

public class ResourceHelper {

    public static String resource(String resource) {
        try {
            return IOUtils.toString(getSystemResourceAsStream(resource), Charset.defaultCharset());
        } catch (IOException e) {
            throw new AssertionError("Resource not found: " + e.getMessage());
        }
    }

    public static String getResource2(String rsc) {
        String val = "";

        try {
            Class cls = Class.forName("net.sasconul.helpers.ResourceHelper");
            ClassLoader cLoader = cls.getClassLoader();

            // input stream
            InputStream i = cLoader.getSystemResourceAsStream(rsc);
            BufferedReader r = new BufferedReader(new InputStreamReader(i));

            // reads each line
            String l;
            while((l = r.readLine()) != null) {
                val = val + l;
            }
            i.close();
        } catch(Exception e) {
            System.out.println(e);
        }
        return val;
    }

    public static File resourceAsFile(String resource) {
        try {
            URL systemResource = getSystemResource(resource);
            return new File(systemResource.toURI());
        } catch (URISyntaxException e) {
            throw new AssertionError("URI syntax error:" + e.getMessage());
        }
    }
}
