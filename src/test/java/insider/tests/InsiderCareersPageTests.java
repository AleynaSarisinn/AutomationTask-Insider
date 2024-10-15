package insider.tests;

import insider.pages.CareersPage;
import insider.pages.JobApplicationPage;
import insider.pages.JobListingPage;
import insider.pages.QAPage;
import insider.utils.BaseTests;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class InsiderCareersPageTests extends BaseTests {
    public InsiderCareersPageTests() {
    }

    @Test
    public void testJobApplicationProcessForQualityAssurance() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        CareersPage careersPage = this.homePage.navigateCareersPage("Company");
        careersPage.waitForCareerPageLoad();
        careersPage.verifyTeamsLocationsAndLifeBlocksPresent();
        QAPage qaPage = careersPage.goToQaPage();
        qaPage.verifyQAPageOpened();
        JobListingPage jobListingPage = qaPage.goToJobListingPage();
        jobListingPage.waitForJLPageLoad();
        Thread.sleep(5000);
        jobListingPage.filterJobs("Istanbul, Turkey", "Quality Assurance");
        softAssert.assertTrue(jobListingPage.checkLocationSelection("Istanbul, Turkey"), "Selected location is incorrect");
        Thread.sleep(5000);
        Assert.assertTrue(jobListingPage.checkJobListPresence());
        softAssert.assertTrue(jobListingPage.verifyJobListingsByCriteria("Istanbul, Turkey", "Quality Assurance", "Quality Assurance"));
        JobApplicationPage jobApplicationPage = jobListingPage.applyForJob("Software Quality Assurance Engineer");
        jobApplicationPage.waitForAppPageLoad();
        softAssert.assertTrue(jobApplicationPage.isCurrentUrlContains("jobs.lever.co"), "Page is not contain correct url");
        jobApplicationPage.verifyAppPageIsOpenedCorrectly("Software Quality Assurance Engineer");
    }
}