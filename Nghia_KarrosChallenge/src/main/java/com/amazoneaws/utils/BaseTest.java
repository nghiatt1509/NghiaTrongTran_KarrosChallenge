package com.amazoneaws.utils;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {

    protected WebDriver driver;

    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true)
    public void setUp(@Optional("chrome") String browser, ITestContext ctx) {

        // Print out the execution log
        String testName = ctx.getCurrentXmlTest().getName();
        System.out.println(testName);

        // Init WebDriver
        if(!browser.equals("none")) {
            DriverFactory driverFactory = new DriverFactory(browser);
            driver = driverFactory.createDriver();
            driver.manage().window().maximize();
        }

        System.out.println("-----   Start Test   -----");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        System.out.println("-----   End Test   -----");
        if(driver != null) {
            driver.quit();
        }
    }
}
