package com.gmteam.base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class DriverThread {
    private static final ThreadLocal<AppiumDriver<MobileElement>> driverThreadLocal = new ThreadLocal<>();

    public static synchronized AppiumDriver<MobileElement> getDriver() {
        return driverThreadLocal.get();
    }

    public static synchronized void setDriver(AppiumDriver<MobileElement> driver) {
        DriverThread.driverThreadLocal.set(driver);
    }
}
