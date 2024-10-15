package insider.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesFileReader {
    private Properties properties = new Properties();

    public PropertiesFileReader(String filePath) {
        try {
            InputStream input = this.getClass().getClassLoader().getResourceAsStream(filePath);

            try {
                if (input != null) {
                    this.properties.load(input);
                } else {
                    System.err.println("Properties dosyası bulunamadı: " + filePath);
                }
            } catch (Throwable var6) {
                if (input != null) {
                    try {
                        input.close();
                    } catch (Throwable var5) {
                        var6.addSuppressed(var5);
                    }
                }

                throw var6;
            }

            if (input != null) {
                input.close();
            }
        } catch (IOException var7) {
            IOException e = var7;
            System.err.println("Properties dosyası okunurken hata oluştu: " + e.getMessage());
        }

    }

    public String getUrl(String key) {
        return this.properties.getProperty(key);
    }
}
