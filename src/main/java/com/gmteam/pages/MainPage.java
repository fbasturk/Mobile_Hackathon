package com.gmteam.pages;

import com.gmteam.base.BaseStepMethod;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;

import static java.lang.Thread.sleep;

public class MainPage extends BaseStepMethod {
    private static final Logger mainPageLogger = LogManager.getLogger(MainPage.class);

    private static final By PROFILE_BTN = By.id("com.airbnb.android:id/2131430787");
    private static final By PROFILE_LBL = By.id("com.airbnb.android:id/2131432435");
    private static final By EXPLORE_BTN = By.id("com.airbnb.android:id/2131430791");
    private static final By WHERE_ARE_BTN = By.id("com.airbnb.android:id/2131429019");
    private static final By WHERE_ARE_LBL = By.id("com.airbnb.android:id/2131430752");
    private static final By CALENDAR_TITLE_LBL = By.id("com.airbnb.android:id/2131430765\n");
    private static final By PLACE_TO_STAY_BTN = By.id("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout[2]/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout[1]/android.view.ViewGroup/android.widget.LinearLayout[2]/android.widget.FrameLayout/android.widget.FrameLayout[1]/android.widget.LinearLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[2]/android.view.ViewGroup");
    private static final By ANTALYA_LBL = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout[2]/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout[1]/android.view.ViewGroup/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout[1]/android.widget.FrameLayout/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[1]");


    @Step("Check login ")
    public void checkLogin() throws Exception {
        mainPageLogger.info("TAP Continue with Email");
        sleep(2500);
        clickElement(PROFILE_BTN);
        if (isVisibleElement(PROFILE_LBL, 7)) {
            String title = getTextElement(PROFILE_LBL);
            if (!title.equals("Automation"))
                Assert.fail("Couldn't login! LoginName: " + title);
        } else
            Assert.fail("Couldn't login!");
    }

    @Step("TAP Explore")
    public void clickExplore() throws Exception {
        mainPageLogger.info("TAP Continue with Email");
        sleep(2500);
        clickElement(EXPLORE_BTN);

    }

    @Step("TAP  Where are you going")
    public void clickWhereAreYou() {
        mainPageLogger.info("TAP Continue with Email");

        clickElement(WHERE_ARE_BTN);

    }

    @Step("SET Where are you going? -> Antalya")
    public void setWhereAre() throws Exception {
        mainPageLogger.info("TAP Continue with Email");
        sleep(2500);
        clickElement(WHERE_ARE_LBL);
        setTextElement(WHERE_ARE_LBL, "Antalya");
    }

    @Step("TAP Search for Antalya, Turkey")
    public void clickAntalya() throws Exception {
        mainPageLogger.info("TAP Continue with Email");
        sleep(2500);
        clickElement(ANTALYA_LBL);

    }

    @Step("TAP Find a place to stay")
    public void clickPlaceToStay() throws Exception {
        mainPageLogger.info("TAP Continue with Email");
        sleep(2500);
        clickElement(PLACE_TO_STAY_BTN);

    }

    @Step("TAP 24 (November 2021) on Calender")
    public void swapCalendar() throws Exception {
        mainPageLogger.info("TAP Continue with Email");
        sleep(2500);
        testSeekBarToEnd(PLACE_TO_STAY_BTN, "up", 200);

    }


}
