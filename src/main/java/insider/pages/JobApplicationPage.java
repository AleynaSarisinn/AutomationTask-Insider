package insider.pages;

import insider.helpers.BasePage;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class JobApplicationPage extends BasePage {
    WebDriver driver;

    public JobApplicationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //ELEMENTS
    @FindBy(tagName = "h2")
    WebElement jobTitle;
    @FindBy(css = "div[data-qa='job-description']")
    WebElement jobDescription;
    @FindBy(css = "a[href*='/apply']")
    List<WebElement> applyButtons;


    //METHODS
    public void waitForAppPageLoad() {
        List<WebElement> elementsToWaitFor = Arrays.asList(this.jobTitle, this.jobDescription);
        Iterator var2 = elementsToWaitFor.iterator();

        while (var2.hasNext()) {
            WebElement element = (WebElement) var2.next();
            this.waitUntilElementVisible(element);
        }

        this.waitUntilAllElementsAreVisible(this.applyButtons);
    }

    public boolean isCurrentUrlContains(String expectedUrlPart) {
        String currentUrl = this.driver.getCurrentUrl();
        boolean urlContainsExpectedPart = currentUrl.contains(expectedUrlPart);
        if (!urlContainsExpectedPart) {
            System.out.println("Current URL does not contain the expected part: " + expectedUrlPart);
        }

        return urlContainsExpectedPart;
    }

    public boolean isApplyThisJobButtonVisible() {
        this.waitUntilAllElementsAreVisible(this.applyButtons);
        boolean areButtonsVisible = this.applyButtons.stream().allMatch(WebElement::isDisplayed);
        if (areButtonsVisible) {
            return true;
        } else {
            System.out.println("Apply This Job button is not visible.");
            return false;
        }
    }

    public boolean verifyJobTitle(String roleTitle) {
        return this.jobTitle.getText().equals(roleTitle);
    }

    public void verifyAppPageIsOpenedCorrectly(String roleTitle) {
        this.isApplyThisJobButtonVisible();
        this.verifyJobTitle(roleTitle);
    }
}
