package com.activitystream.underware;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Version {
    public static String sdkVersion;

    static {
        try {
            Properties prop = new Properties();
            InputStream in = Version.class.getResourceAsStream("/info.properties");
            prop.load(in);
            in.close();
            String version = prop.getProperty("version");
            if (!version.contains("$")) Version.sdkVersion = "java-" + version;
            else Version.sdkVersion = null;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
