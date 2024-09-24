package Practo_pages;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Configuration {

    private Properties properties;

    public  Configuration() {
        try {
            FileInputStream fileInputStream = new FileInputStream("src/main/resources/Config/Configuration.properties");
            properties = new Properties();
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to get property values by key
    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}

