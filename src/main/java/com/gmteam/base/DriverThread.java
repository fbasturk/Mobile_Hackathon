package com.gmteam.base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class DriverThread {
    private static final ThreadLocal<AppiumDriver<MobileElement>> driverThreadLocal = new ThreadLocal<>();

    public static AppiumDriver<MobileElement> getDriver() {
        return driverThreadLocal.get();
    }

    public static void setDriver(AppiumDriver<MobileElement> driver) {
        DriverThread.driverThreadLocal.set(driver);
    }
}
