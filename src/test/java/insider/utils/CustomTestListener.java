package insider.utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class CustomTestListener extends BaseTests implements ITestListener {
    public CustomTestListener() {
    }

    public void onTestFailure(ITestResult result) {
        ScreenshotHelper screenshotHelper = new ScreenshotHelper(this.driver);
        screenshotHelper.takeScreenshot(result.getName() + "_failure");
        System.err.println("Test failed: " + result.getName());
    }

    public void onTestSuccess(ITestResult result) {
        System.out.println("Test passed: " + result.getName());
    }

    public void onTestSkipped(ITestResult result) {
        System.out.println("Test skipped: " + result.getName());
    }

    public void onTestStart(ITestResult result) {
        System.out.println("Test started: " + result.getName());
    }

    public void onFinish(ITestContext context) {
        System.out.println("All tests completed.");
    }
}
