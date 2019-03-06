package com.company.Convert;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Props extends Properties {
    public Props() {
    }

    public static Properties propertiesReadFile(String file) {
        try {
            Properties prop = new Properties();
            FileInputStream fis = new FileInputStream(file);
            prop.load(fis);
            fis.close();
            return prop;
        } catch (IOException var3) {
            System.out.println(var3.getMessage());
            return null;
        }
    }

    public static Properties propertiesReadResource(String file) {
        try {
            Properties prop = new Properties();
            InputStream fis = Props.class.getResourceAsStream(file);
            prop.load(fis);
            fis.close();
            return prop;
        } catch (Exception var3) {
            return null;
        }
    }

    public static void propertiesSaveFile(Properties prop, String file, String comment) {
        try {
            FileOutputStream fos = null;
            prop.store(fos = new FileOutputStream(file), comment);
            fos.close();
        } catch (IOException var4) {
            System.out.println(var4.getMessage());
        }

    }

    public static int getProp(Properties prop, String key) {
        return Integer.parseInt(prop.getProperty(key));
    }
}

