package com.gmteam;

import com.gmteam.base.BaseTest;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;

public class AirBnb extends BaseTest {


    @Test(enabled = true, description = "Scenario 1 - Login With Email Address")
    public void TestScenario_01() {
        try {
            sleep(50000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test(enabled = true, description = "Scenario 2 - Add to Wishlist")
    public void TestScenario_02() {

    }

    @Test(enabled = true, description = "Scenario 3 - Remove from Wishlist")
    public void TestScenario_03() {

    }
}
