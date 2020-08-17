package com.amazoneaws.pages;

import com.amazoneaws.utils.KeywordHelper;
import com.amazoneaws.utils.WaitHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FilterPage extends BasePage {

    @FindBy(xpath = "//select[@id='formControlsSelect']")
    private WebElement ddlRequestStatus;

    @FindBy(xpath = "//input[@id='formHorizontalEmail']")
    private WebElement txtEmail;

    @FindBy(xpath = "//input[@id='formHorizontalStudentID']")
    private WebElement txtStudentID;

    @FindBy(xpath = "//input[@id='formHorizontalStudentFN']")
    private WebElement txtStudentFirstName;

    @FindBy(xpath = "//input[@id='formHorizontalStudentLN']")
    private WebElement txtStudentLastName;

    private String btnDynamicButtonXpath = "//button[translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz') = '%s']";
    private WaitHelper waitHelper = new WaitHelper(driver);

    public FilterPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /***
     * Enter data into fields on Filter popup
     * @param requestStatus
     * @param email
     * @param studentID
     * @param studentFirstName
     * @param studentLastName
     * @return
     */
    public FilterPage enterFilterFields(String requestStatus, String email, String studentID, String studentFirstName, String studentLastName) {

        waitHelper.waitForVisibilityOf(ddlRequestStatus);

        KeywordHelper keywordHelper = new KeywordHelper(driver);
        keywordHelper.SelectDropdownListByText(ddlRequestStatus, requestStatus);

        txtEmail.sendKeys(email);
        txtStudentID.sendKeys(studentID);
        txtStudentFirstName.sendKeys(studentFirstName);
        txtStudentLastName.sendKeys(studentLastName);

        return this;
    }

    /***
     * Click Cancel or Apply Filters button
     * @param buttonText
     * @return
     */
    public ParentPortalPage clickSelectedButton(String buttonText) {
        String xpathTemp = btnDynamicButtonXpath;
        xpathTemp = String.format(xpathTemp, buttonText.toLowerCase().trim());
        WebElement btnButton = driver.findElement(new By.ByXPath(xpathTemp));

        waitHelper.waitForVisibilityOf(btnButton, 5);
        btnButton.click();

        return new ParentPortalPage(driver);
    }
}
