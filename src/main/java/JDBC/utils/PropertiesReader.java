package JDBC.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {

    private Properties properties = new Properties();

    public PropertiesReader() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("ddt.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find ddt.properties");
                return;
            }
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}

