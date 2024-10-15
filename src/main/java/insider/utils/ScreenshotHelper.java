package insider.utils;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class ScreenshotHelper {
    private WebDriver driver;

    public ScreenshotHelper(WebDriver driver) {
        this.driver = driver;
    }

    public void takeScreenshot(String testName) {
        if (this.driver != null && this.driver instanceof TakesScreenshot) {
            TakesScreenshot screenshot = (TakesScreenshot)this.driver;
            File srcFile = (File)screenshot.getScreenshotAs(OutputType.FILE);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
            String timestamp = LocalDateTime.now().format(formatter);
            String specificDirectory = "src/test/java/resources/screenshots/";
            String fileName = specificDirectory + testName + "_" + timestamp + ".png";

            try {
                FileHandler.copy(srcFile, new File(fileName));
            } catch (IOException var9) {
                IOException e = var9;
                e.printStackTrace();
            }
        } else {
            System.out.println("Driver is null or does not support screenshot taking.");
        }

    }
}
