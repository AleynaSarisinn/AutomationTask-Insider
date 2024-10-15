package insider.pages;

import insider.helpers.BasePage;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class JobListingPage extends BasePage {
    WebDriver driver;

    public JobListingPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //ELEMENTS

    @FindBy(id = "career-position-filter")
    WebElement jobFilterSection;

    @FindBy(id = "select2-filter-by-department-results")
    WebElement departmentFilterResults;

    @FindBy(id = "select2-filter-by-department-container")
    WebElement filterDepartmentDropdown;

    @FindBy(css = "span ul li")
    List<WebElement> dropdownOptions;

    @FindBy(css = ".position-list-item")
    List<WebElement> jobListItems;

    @FindBy(id = "select2-filter-by-location-container")
    WebElement filterLocationDropdown;

    @FindBy(id = "select2-filter-by-location-results")
    WebElement locationFilterDropdownResults;

    By viewRoleBtnBy = By.cssSelector("a[href*='jobs.lever']");


    //METHODS
    public void waitForJLPageLoad() {
        this.waitUntilElementVisible(this.jobFilterSection);
        this.waitUntilElementVisible(this.filterDepartmentDropdown);
    }

    public void chooseLocation(String desiredLocation) throws InterruptedException {
        Thread.sleep(5000L);
        this.waitUntilElementVisible(this.filterLocationDropdown);
        Thread.sleep(5000L);
        this.clickElementWithAction(this.filterLocationDropdown);
        Thread.sleep(5000L);
        this.waitUntilElementVisible(this.locationFilterDropdownResults);
        this.waitUntilAllElementsAreVisible(this.dropdownOptions);
        this.waitUntilAllElementsAreClickable(this.dropdownOptions);
        WebElement selectedLocation = (WebElement) this.dropdownOptions.stream().filter((option) -> {
            return option.getText().equals(desiredLocation);
        }).findFirst().orElseThrow(() -> {
            return new NoSuchElementException("Location not found: " + desiredLocation);
        });
        this.waitUntilElementVisible(selectedLocation);
        selectedLocation.click();
    }

    public void chooseDepartment(String departmentName) {
        this.waitElementToBeClickable(this.filterDepartmentDropdown);
        this.clickElementWithAction(this.filterDepartmentDropdown);
        this.waitUntilElementVisible(this.departmentFilterResults);
        this.waitUntilAllElementsAreClickable(this.dropdownOptions);
        WebElement departmentOption = (WebElement) this.dropdownOptions.stream().filter((option) -> {
            return option.getText().equals(departmentName);
        }).findFirst().orElseThrow(() -> {
            return new NoSuchElementException("No department found: " + departmentName);
        });
        this.waitElementToBeClickable(departmentOption);
        departmentOption.click();
    }

    public void filterJobs(String locationSelection, String departmentSelection) throws InterruptedException {
        this.chooseLocation(locationSelection);
        this.chooseDepartment(departmentSelection);
    }

    public boolean checkLocationSelection(String expectedLocation) {
        this.waitUntilElementVisible(this.filterLocationDropdown);
        String[] dropdownText = this.filterLocationDropdown.getText().split("\n");
        if (dropdownText.length > 1) {
            String actualLocation = dropdownText[1];
            return expectedLocation.equals(actualLocation);
        } else {
            return false;
        }
    }

    public boolean checkJobListPresence() {
        if (this.jobListItems != null && !this.jobListItems.isEmpty()) {
            this.waitUntilAllElementsAreVisible(this.jobListItems);
            System.out.println("Job listings are present. Number of jobs displayed: " + this.jobListItems.size());
            return true;
        } else {
            System.out.println("No job listings are present.");
            return false;
        }
    }

    public boolean verifyJobListingsByCriteria(String locationSelection, String departmentSelection, String titleSelection) {
        this.waitUntilAllElementsAreVisible(this.jobListItems);
        List<String> jobListingsPositions = this.jobListItems.stream().map((item) -> {
            return item.findElement(By.cssSelector(".position-title")).getText();
        }).toList();
        boolean areAllTitlesMatching = jobListingsPositions.stream().allMatch((title) -> {
            return title.contains(titleSelection);
        });
        List<String> jobListingsLocations = this.jobListItems.stream().map((item) -> {
            return item.findElement(By.cssSelector(".position-location")).getText();
        }).toList();
        boolean areAllLocationsMatching = jobListingsLocations.stream().allMatch((location) -> {
            return location.equals(locationSelection);
        });
        List<String> jobListingsDepartments = this.jobListItems.stream().map((item) -> {
            return item.findElement(By.cssSelector(".position-department")).getText();
        }).toList();
        boolean areAllDepartmentsMatching = jobListingsDepartments.stream().allMatch((department) -> {
            return department.equals(departmentSelection);
        });
        return areAllTitlesMatching && areAllLocationsMatching && areAllDepartmentsMatching;
    }

    public JobApplicationPage applyForJob(String jobRole) {
        this.waitUntilAllElementsAreVisible(this.jobListItems);
        WebElement jobToApply = (WebElement) this.jobListItems.stream().filter((job) -> {
            return job.findElement(By.cssSelector(".position-title")).getText().contains(jobRole);
        }).findFirst().orElseThrow(() -> {
            return new NoSuchElementException("Job role not found: " + jobRole);
        });
        this.clickElementUsingJavaScript(jobToApply.findElement(this.viewRoleBtnBy));
        super.switchToNewTab();
        return new JobApplicationPage(this.driver);
    }
}
