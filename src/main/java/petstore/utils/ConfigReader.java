package petstore.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties = new Properties();

    public ConfigReader() {
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    static {
        try {
            FileInputStream input = new FileInputStream("src/test/java/petstore/config.properties");

            try {
                properties.load(input);
            } catch (Throwable var4) {
                try {
                    input.close();
                } catch (Throwable var3) {
                    var4.addSuppressed(var3);
                }

                throw var4;
            }

            input.close();
        } catch (IOException var5) {
            IOException e = var5;
            e.printStackTrace();
        }

    }
}
