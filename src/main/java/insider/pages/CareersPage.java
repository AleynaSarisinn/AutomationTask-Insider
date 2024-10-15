package insider.pages;

import insider.helpers.BasePage;
import insider.utils.PropertiesFileReader;
import java.util.stream.Stream;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CareersPage extends BasePage {
    private PropertiesFileReader propertiesFileReader;
    WebDriver driver;


    public CareersPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.propertiesFileReader = new PropertiesFileReader("links.properties");
    }


    //ELEMENTS
    @FindBy(id = "career-find-our-calling")
    WebElement teamsSection;
    By teamsSectionBy = By.id("career-find-our-calling");
    @FindBy(id = "career-our-location")
    WebElement locationsSection;
    By locationsSectionBy = By.id("career-our-location");
    @FindBy(css = "section[data-id='a8e7b90']")
    WebElement lifeAtInsiderSection;
    By lifeAtInsiderSectionBy = By.cssSelector("section[data-id='a8e7b90']");

    //METHODS
    public void waitForCareerPageLoad() throws InterruptedException {
        Stream.of(this.teamsSection,this.locationsSection, this.lifeAtInsiderSection).forEach(this::waitUntilElementVisible);
    }

    public void verifyTeamsLocationsAndLifeBlocksPresent() throws InterruptedException {
        this.verifyTeamsPresent();
        this.verifyLocationsPresent();
        this.verifyLifeAtInsiderPresent();
    }

    public void verifyTeamsPresent() throws InterruptedException {
        if (!this.isElementPresent(this.teamsSectionBy)) {
            throw new RuntimeException("Teams section is not present");
        }
    }

    public void verifyLocationsPresent() throws InterruptedException {
        if (!this.isElementPresent(this.locationsSectionBy)) {
            throw new RuntimeException("Locations section is not present");
        }
    }

    public void verifyLifeAtInsiderPresent() throws InterruptedException {

        if (!this.isElementPresent(this.lifeAtInsiderSectionBy)) {
            throw new RuntimeException("Life at Insider section is not present");
        }
    }

    public QAPage goToQaPage() throws InterruptedException {
        this.driver.get(this.propertiesFileReader.getUrl("qa.page"));
        return new QAPage(this.driver);
    }
}
