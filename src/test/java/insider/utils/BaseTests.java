package insider.utils;

import insider.pages.HomePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class BaseTests {
    protected WebDriver driver;
    protected HomePage homePage;
    protected ScreenshotHelper screenshotHelper;

    public BaseTests() {
    }

    @BeforeMethod(
            alwaysRun = true
    )
    @Parameters({"browser"})
    public void setup(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments(new String[]{"--disable-notifications"});
            this.driver = new ChromeDriver(options);
        } else {
            if (!browser.equalsIgnoreCase("firefox")) {
                throw new IllegalArgumentException("Browser is not supported: " + browser);
            }

            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            options.addPreference("dom.webnotifications.enabled", false);
            this.driver = new FirefoxDriver(options);
        }

        this.driver.manage().window().maximize();
        this.homePage = new HomePage(this.driver);
        this.homePage.navigateToHomePage();
        this.homePage.acceptCookies();
        this.screenshotHelper = new ScreenshotHelper(this.driver);
    }

    @AfterMethod(
            alwaysRun = true
    )
    public void tearDown(ITestResult result) {
        if (2 == result.getStatus()) {
            this.screenshotHelper.takeScreenshot(result.getName());
        }

        if (this.driver != null) {
            this.driver.quit();
        }

    }
}