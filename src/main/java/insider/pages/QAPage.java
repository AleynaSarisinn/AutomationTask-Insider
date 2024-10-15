package insider.pages;

import insider.helpers.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class QAPage extends BasePage {
    WebDriver driver;

    public QAPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //ELEMENTS
    @FindBy(id = "page-head")
    WebElement pageHeadSection;

    @FindBy(id = "find-job-widget")
    WebElement findYourDreamJobSection;

    @FindBy(css = "a[href*='department']")
    WebElement seeQaJobsBtn;

    //METHODS
    public void verifyQAPageOpened() {
        this.waitUntilElementVisible(this.pageHeadSection);
        this.waitUntilElementVisible(this.findYourDreamJobSection);
    }

    public JobListingPage goToJobListingPage() {
        this.waitElementToBeClickable(this.seeQaJobsBtn);
        this.seeQaJobsBtn.click();
        JobListingPage jobListingPage = new JobListingPage(this.driver);
        return jobListingPage;
    }
}
