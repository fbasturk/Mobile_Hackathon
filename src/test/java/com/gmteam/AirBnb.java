package com.gmteam;

import com.gmteam.base.BaseTest;
import com.gmteam.pages.LoginPage;
import com.gmteam.pages.MainPage;
import org.testng.annotations.Test;


public class AirBnb extends BaseTest {


    @Test(enabled = true, description = "Scenario 1 - Login With Email Address")
    public void TestScenario_01() throws Exception {
        LoginPage loginPage = new LoginPage();
        MainPage mainPage = new MainPage();

        loginPage.checkOpenPage();

        loginPage.clickContinueEmail();
        loginPage.setEmail();
        loginPage.clickContinue();

        loginPage.setPassword();
        loginPage.clickContinue();

        mainPage.checkLogin();
    }

    @Test(enabled = true, description = "Scenario 2 - Add to Wishlist")
    public void TestScenario_02() throws Exception {
        MainPage mainPage = new MainPage();

        mainPage.clickExplore();
        mainPage.clickWhereAreYou();
        mainPage.setWhereAre();
        mainPage.clickAntalya();
        mainPage.clickPlaceToStay();
        mainPage.swapCalendar();

    }

    @Test(enabled = true, description = "Scenario 3 - Remove from Wishlist")
    public void TestScenario_03() {

    }
}
