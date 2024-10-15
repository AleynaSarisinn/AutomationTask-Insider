# Insider Test Automation Task

## Table of Contents
- [Overview](#overview)
- [Automated UI/Load/API Tasks](#Automated UI/Load/API Tasks)
- [Test Automation UI Testing Part](#Test Automation UI Testing Part)
- [Test automation Load Testing Part](#Test automation Load Testing Part)
- [Test automation API Testing Part](#Test automation API Testing Part)

## Overview
This project involved automating tests for UI, load, and API functionalities across different platforms. 
UI testing focused on navigating the Insider website, verifying job listings, and validating specific role details, while load testing was conducted using JMeter on the n11 search module. 
Additionally, comprehensive API testing was carried out for CRUD operations on Swagger Petstore, covering both positive and negative scenarios.

## Test Automation UI Testing Part

### Requirements:

1 - Programming Language and Framework:
        The test case should be written using any programming language and framework.
        Preferred options: Python or Java with Selenium.
        Note: BDD frameworks (such as Cucumber, Quantum, Codeception, etc.) are not allowed.

2- Failure Handling:
        If the test case fails at any step, a screenshot should be automatically taken to capture the failure.

3- Browser Compatibility:
        The test case should be capable of running on both Chrome and Firefox browsers.
        The browser selection should be parameterized, allowing easy switching between browsers without modifying the code.

4- Page Object Model (POM) Compliance:
        The test code must adhere to the principles of the Page Object Model (POM), ensuring that the test structure is modular and maintainable.

### Task Instructions:

1 - Verify Insider Home Page:

- Navigate to [Insider's home page](https://useinsider.com/)  and ensure that the home page is successfully opened.

2 - Navigate to Careers Page:
- In the navigation bar, select the "Company" menu, then choose "Careers". 
- Verify that the Careers page is opened, and check the visibility of the Locations, Teams, and Life at Insider sections.

3 - Filter QA Jobs in Istanbul:
-Go to the  [Quality Assurance page ](https://useinsider.com/careers/quality-assurance/)
- Click on the "See all QA jobs" button.
- Apply filters for Location: "Istanbul, Turkey" and Department: "Quality Assurance".
- Verify that a list of jobs appears.

4 - Validate Job Listings:
For all listed jobs, confirm that:
- The Position field contains "Quality Assurance".
- The Department field contains "Quality Assurance". 
- The Location field contains "Istanbul, Turkey".

5 - Verify Job Details:

-Click on the "View Role" button for any job listing.
-Ensure that the role details are displayed correctly.


### Explanation of Performed UI Testing Part

In this test scenario, navigated from the homepage to the careers page, verifying the correct loading of various blocks. 
Then, moved to the QA page and confirm that it opens properly. 
Afterward, we navigate to the job listings page, apply location and position filters, and verify that the job listings display correctly based on the criteria.
Proceed to apply for a specific job and ensure that the job application page contains the correct URL and displays the correct job position. 
Throughout the process, SoftAssert is used to validate each step, and results are reported.
- Additionally, the implementation allows the tests to be run on both Chrome and Firefox through TestNG. 
- Any remaining tests capture screenshots, which are saved in the path: `AutomationTaskInsider/src/test/java/resources/screenshots.` 
- All test reports are generated in the test-output and target/surefire-reports directory, with a detailed summary available in the `emailable-report.html` file. 
For further details, you can refer to this screenshot: https://prnt.sc/J7mRvy4cxPF1, https://prnt.sc/iF-f1a1h3-nn

## Test automation - Load Testing Part

### Requirements:
- Prepare Load Test for Search Module:
- Navigate to [N11's home page](https://www.n11.com/).
- Identify the search module in the website header and ensure it is functioning correctly.
- Develop load testing scenarios in JMeter to simulate multiple users interacting with the search module simultaneously.


### Explanation of Performed Load Testing Part : JMeter Test Plan and Test Cases for n11 Application

**For this part,** 
- Total of **10 test cases** have been created as part of the testing process. Additionally,`Load Test Files.zip` file has been prepared and can be found in the project folder.
- Inside this file, there is a test plan file named `n11TestPlan.jmx`, which was created using JMeter. 
- This test plan defines the structure and configuration of the load test, including details such as the target application, test scenarios, number of users, and other parameters to simulate various load conditions. 
- The `.jmx file` can be loaded into **JMeter** to execute the load test and analyze the performance results for the n11 application, testing its behavior under different levels of traffic. 
Note: These test cases are designed to validate various functional and performance aspects of the application, ensuring that key features work as expected and that the system behaves correctly under load.
  Relevant tests and details  can be found in the following screenshot: https://prnt.sc/kH0uGf2nZ7VR.

## Test automation - API Testing Part

### Requirements:
- Using “pet” endpoints from [Swagger Petstore](https://petstore.swagger.io/)  write CRUD operations API tests with positive and negative scenarios.


### Explanation of Performed API Testing Part :Comprehensive Testing of CRUD Operations for Swagger Petstore API

A total of **11 test cases** were created to test the CRUD operations of the Petstore API available at[Swagger Petstore](https://petstore.swagger.io/). 

These test cases cover both positive and negative scenarios for various endpoints.
* **The tested endpoints include:**
* CreatePet (`GET v2/pet`),
* CreatePetWithId (`GET v2/pet/{petId}`),
* deletePet (`DELETE v2/pet/{petId}`),
* readPet (`GET v2/pet/{petId}`),
* readPetFindByStatus (`GET v2/pet/findByStatus?status={status}`),
* and updatePet (`PUT v2/pet`). 

In positive scenarios, the expected **HTTP status code is 200,** indicating successful operations.
Negative cases were tested by intentionally sending malformed requests using the sendWrongRequest method to validate how the system handles invalid inputs, expecting **HTTP status codes 400 or 404**.
Relevant tests and details  can be found in the following screenshot: https://prnt.sc/wcmFStuQ-Tyt.
