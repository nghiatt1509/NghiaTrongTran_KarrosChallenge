package com.amazoneaws.pages;

import com.amazoneaws.utils.KeywordHelper;
import com.amazoneaws.utils.WaitHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ParentPortalPage extends BasePage {

    @FindBy(xpath = "//button[contains(@class, 'btn-filter')]")
    private WebElement btnFilter;

    @FindBy(xpath = "//*[@class='main-module_tableview']")
    private WebElement tblStudentRequestAccess;

    private WaitHelper waitHelper = new WaitHelper(driver);
    private KeywordHelper keywordHelper = new KeywordHelper(driver);

    public ParentPortalPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /***
     * Click Filter button to open the Filter Popup
     * @return
     */
    public FilterPage openFilterPopup() {
        waitHelper.waitForVisibilityOf(btnFilter, 5);
        btnFilter.click();
        return new FilterPage(driver);
    }

    /***
     * Verify if List of request is filter with Inactive status
     * @return
     */
    public boolean verifyFilterStudentAccessRequestwithInative() {
        boolean isCorrect = false;

        List<String> statusListWithoutInactive = new ArrayList<String>();
        statusListWithoutInactive.add("approved");
        statusListWithoutInactive.add("denied");
        statusListWithoutInactive.add("pending");
        statusListWithoutInactive.add("rejected");

        List<String> filteredStudentAccessRequest = keywordHelper.getTableCellByHeader(tblStudentRequestAccess, "Request Status");

        isCorrect = Collections.disjoint(filteredStudentAccessRequest, statusListWithoutInactive);

        return isCorrect;
    }

    /***
     * Click column header to make a sort
     * @param columnHeader
     * @return
     */
    public ParentPortalPage sortStudentAccessRequestByColumn(String columnHeader){
        waitHelper.waitForVisibilityOf(tblStudentRequestAccess, 5);
        keywordHelper.sortByColumnHeader(tblStudentRequestAccess, columnHeader);
        return this;
    }

    /***
     * Verify sort status
     * @param columnHeader
     * @param sortType
     * @return
     */
    public boolean verifySortingStudentAccessRequestByColumn(String columnHeader, String sortType){
        boolean isSorted = false;
        waitHelper.waitForVisibilityOf(tblStudentRequestAccess);
        isSorted = keywordHelper.isColumnSorted(tblStudentRequestAccess, columnHeader, sortType);
        return isSorted;
    }
}
