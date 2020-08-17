package com.amazoneaws;

import com.amazoneaws.pages.FilterPage;
import com.amazoneaws.pages.LoginPage;
import com.amazoneaws.pages.ParentPortalPage;
import com.amazoneaws.utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ParentPortalTest extends BaseTest {

    @Parameters({"username", "password"})
    @Test(priority = 1)
    public void filterStudentAccessRequestwithInative(String username, String password) {

        System.out.println("1. Open Login page");
        LoginPage loginPage =  new LoginPage(driver);
        loginPage.openLoginPage();

        System.out.println("2. Log into the Parent Portal");
        ParentPortalPage parentPortalPage = loginPage.login(username, password);

        System.out.println("3. Click Filter button");
        FilterPage filterPage = parentPortalPage.openFilterPopup();

        System.out.println("4. Select 'Inactive' Status");
        filterPage.enterFilterFields("Inactive", "", "", "", "");

        System.out.println("5. Click 'Apply Filters' button");
        filterPage.clickSelectedButton("Apply Filters");

        System.out.println("VP. Verify filter Student Access Request with INACTIVE");
        System.out.println("This case is failed because of Bug-02: Filter does not work");
        Assert.assertTrue(parentPortalPage.verifyFilterStudentAccessRequestwithInative());
    }

    @Parameters({"username", "password"})
    @Test(priority = 1)
    public void sortingofFirstNameColumn(String username, String password) {

        System.out.println("1. Open Login page");
        LoginPage loginPage =  new LoginPage(driver);
        loginPage.openLoginPage();

        System.out.println("2. Log into the Parent Portal");
        ParentPortalPage parentPortalPage = loginPage.login(username, password);

        System.out.println("3. Click First Name column header to make a sort");
        parentPortalPage.sortStudentAccessRequestByColumn("First Name");

        System.out.println("VP. Verify DESC sorting of First Name column");
        Assert.assertTrue(parentPortalPage.verifySortingStudentAccessRequestByColumn("First Name", "desc"));

        System.out.println("4. Click First Name column header to make a sort");
        parentPortalPage.sortStudentAccessRequestByColumn("First Name");

        System.out.println("VP. Verify ASC sorting of First Name column");
        Assert.assertTrue(parentPortalPage.verifySortingStudentAccessRequestByColumn("First Name", "asc"));
    }
}
