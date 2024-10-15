package insider.helpers;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void waitForElementToDisappears(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.invisibilityOf(element));
        } catch (TimeoutException var3) {
            TimeoutException e = var3;
            System.out.println("Element is still visible after waiting: " + e.getMessage());
        }

    }

    public void waitForPopupToBeVisible(WebElement popupElement) {
        WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(popupElement));
    }

    public void waitElementToBeClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitUntilElementVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitForPageToLoad() {
        WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(10L));
        wait.until((driver) -> {
            return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
        });
    }

    public Boolean isElementPresent(By elementBy) {
        return !this.driver.findElements(elementBy).isEmpty();
    }

    public void waitUntilAllElementsAreClickable(List<WebElement> elements) {
        WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(10L));
        Iterator var3 = elements.iterator();

        while(var3.hasNext()) {
            WebElement element = (WebElement)var3.next();

            try {
                wait.until(ExpectedConditions.elementToBeClickable(element));
            } catch (TimeoutException var6) {
                System.out.println("Timeout waiting for element to be clickable: " + element.toString());
            } catch (WebDriverException var7) {
                WebDriverException e = var7;
                System.out.println("WebDriver error while waiting for element: " + element.toString());
                e.printStackTrace();
            }
        }

    }

    public void clickElementWithAction(WebElement element) {
        Actions actions = new Actions(this.driver);
        actions.moveToElement(element).click().build().perform();
    }

    public void clickElementUsingJavaScript(WebElement element) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor)this.driver;
        jsExecutor.executeScript("arguments[0].click();", new Object[]{element});
    }

    public void waitUntilAllElementsAreVisible(List<WebElement> elements) {
        WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(20L));
        wait.until((driver) -> {
            Iterator var2 = elements.iterator();

            WebElement element;
            do {
                if (!var2.hasNext()) {
                    return true;
                }

                element = (WebElement)var2.next();
            } while(element.isDisplayed());

            return false;
        });
    }

    public void switchToNewTab() {
        String currentWindowHandle = this.driver.getWindowHandle();
        Set<String> allWindowHandles = this.driver.getWindowHandles();
        Iterator var3 = allWindowHandles.iterator();

        while(var3.hasNext()) {
            String windowHandle = (String)var3.next();
            if (!windowHandle.equals(currentWindowHandle)) {
                this.driver.switchTo().window(windowHandle);
                break;
            }
        }

    }
}
