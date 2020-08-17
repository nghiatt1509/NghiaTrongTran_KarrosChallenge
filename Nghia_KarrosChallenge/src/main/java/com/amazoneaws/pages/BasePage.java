package com.amazoneaws.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BasePage {

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    /***
     * Navigate to the expected Url
     * @param url
     */
    protected void openUrl(String url) {
        driver.get(url);
    }
}
