package com.amazoneaws.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

    private ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
    private String browser;

    public DriverFactory(String browser) {
        this.browser = browser;
    }

    public WebDriver createDriver() {

        // Create driver
        System.out.println("-----    Init Test   -----");

        switch (browser) {
            case "firefox":
                System.out.println("----- Create driver: " + browser);
                System.setProperty("webdriver.gecko.driver", "src/main/resources/drivers/geckodriver.exe");
                driver.set(new FirefoxDriver());
                break;
            case "chrome":
            default:
                System.out.println("----- Create driver: " + browser);
                System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
                driver.set(new ChromeDriver());
                break;
        }

        return driver.get();
    }
}
