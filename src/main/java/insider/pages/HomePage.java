package insider.pages;

import insider.helpers.BasePage;
import insider.utils.PropertiesFileReader;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {
    private PropertiesFileReader propertiesFileReader;
    protected WebDriver driver;

    public HomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.propertiesFileReader = new PropertiesFileReader("links.properties");
    }

    //ELEMENTS
    @FindBy(css = ".account")
    WebElement loginBtn;
    @FindBy(id = "wt-cli-accept-all-btn")
    WebElement acceptCookiesBtn;
    @FindBy(id = "cookie-law-info-bar")
    WebElement cookieOverlay;
    @FindBy(linkText = "Careers")
    WebElement careersBtn;
    @FindBy(xpath = "//*[@class='ins-element-content ins-selectable-element']")
    WebElement closePopupBtn;
    @FindBy(linkText = "Find your dream job")
    WebElement findYourDreamJobBtn;


    public void navigateToHomePage() {
        String homePageUrl = this.propertiesFileReader.getUrl("insider.home.page");
        this.driver.get(homePageUrl);
        if (this.loginBtn.isDisplayed() && this.driver.getCurrentUrl().equals(homePageUrl)) {
            System.out.println("Successfully navigated to the Home Page.");
        } else {
            System.out.println("Failed to navigate to the Home Page.");
        }

    }

    public void acceptCookies() {
        this.waitUntilElementVisible(this.acceptCookiesBtn);
        if (this.acceptCookiesBtn.isDisplayed()) {
            this.acceptCookiesBtn.click();
        }

        this.waitForElementToDisappears(this.cookieOverlay);
        this.closePopUpIfVisible();
        this.waitForPageToLoad();
    }

    public void closePopUpIfVisible() {
        try {
            this.waitUntilElementVisible(this.closePopupBtn);
            if (this.closePopupBtn.isDisplayed()) {
                this.closePopupBtn.click();
            }
        } catch (NoSuchElementException var2) {
            System.out.println("Pop-up is not found or visible");
        } catch (TimeoutException var3) {
            System.out.println("Pop-up is still not available");
        }

        this.waitForPageToLoad();
    }

    public CareersPage navigateCareersPage(String menuItem) throws InterruptedException {
        this.navigateInNavBar(menuItem);
        this.selectCareersFromDropDown();
        //System.out.println("Careers page is not opened.");
        Thread.sleep(3000);
        return new CareersPage(this.driver);
    }

    public void navigateInNavBar(String menuName) {
        this.waitForPageToLoad();
        WebElement menuItem = this.driver.findElement(By.xpath("//nav//a[contains(text(),'" + menuName + "')]"));
        this.waitElementToBeClickable(menuItem);
        menuItem.click();
    }

    public void selectCareersFromDropDown() {
        waitElementToBeClickable(careersBtn);
        careersBtn.click();
        waitUntilElementVisible(findYourDreamJobBtn);
    }
}
