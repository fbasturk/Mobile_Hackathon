package com.gmteam.pages;

import com.gmteam.base.BaseStepMethod;
import com.gmteam.base.BaseTest;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;

import static java.lang.Thread.sleep;

public class LoginPage extends BaseStepMethod {
    private static final Logger loginPageLogger = LogManager.getLogger(LoginPage.class);

    private static final By EMAIL_BTN = By.id("com.airbnb.android:id/2131428790");
    private static final By EMAIL_LBL = By.id("com.airbnb.android:id/2131428776");
    private static final By CONTINUE_BTN = By.id("com.airbnb.android:id/2131429273");
    private static final By PASSWORD_LBL = By.id("com.airbnb.android:id/2131428776");
    private static final By PAGE_TITLE_LBL = By.id("com.airbnb.android:id/2131432435");


    @Step("Check Open Login Page")
    public void checkOpenPage() {
        loginPageLogger.info("TAP Continue with Email");

        String title = getTextElement(PAGE_TITLE_LBL);

        if (!title.equals("Log in or sign up to Airbnb"))
            Assert.fail("LoginPage not open! Title: " + title);


    }

    @Step("TAP Continue with Email")
    public void clickContinueEmail() {
        loginPageLogger.info("TAP Continue with Email");

        clickElement(EMAIL_BTN);
    }

    @Step("TAP OK on Your Privacy (If Exists)")
    public void clickIfExistsPrivacy() {
        loginPageLogger.info("TAP OK on Your Privacy (If Exists)\n");

    }

    @Step("SET Email ->")
    public void setEmail() {
        String email = getProperty("username");
        loginPageLogger.info("SET Email -> " + email);

        clickElement(EMAIL_LBL);
        setTextElement(EMAIL_LBL, email);
    }

    @Step("TAP Continue")
    public void clickContinue() throws Exception{
        loginPageLogger.info("TAP Continue");

        clickElement(CONTINUE_BTN);
        sleep(2500);
    }

    @Step("SET Password ->")
    public void setPassword()  {
        String password = getProperty("password");
        loginPageLogger.info("SET Password -> " + password);

        clickElement(PASSWORD_LBL);
        setTextElement(PASSWORD_LBL, password);
    }

}
