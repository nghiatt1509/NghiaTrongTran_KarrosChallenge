package com.amazoneaws.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {

    private String pageUrl = "http://ktvn-test.s3-website.us-east-1.amazonaws.com/";

    @FindBy(xpath = "//input[@id='formHorizontalEmail']")
    private WebElement txtEmail;

    @FindBy(xpath = "//input[@id='formHorizontalPassword']")
    private WebElement txtPassword;

    @FindBy(xpath = "//a[contains(@class, 'login__btn')]")
    private WebElement btnLogin;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /***
     * Navigate to Login Page
     */
    public void openLoginPage() {
        openUrl(pageUrl);
    }

    /***
     * Enter username, password then click Login button
     * @param username
     * @param password
     * @return
     */
    public ParentPortalPage login(String username, String password) {
        txtEmail.sendKeys(username);
        txtPassword.sendKeys(password);
        btnLogin.click();
        return new ParentPortalPage(driver);
    }
}
