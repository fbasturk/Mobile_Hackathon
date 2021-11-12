package com.gmteam.base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;


import java.io.File;
import java.net.URL;
import java.util.Properties;

public class PlatformType {

    enum PlatformName {
        ANDROID, IOS;
    }


    public static AppiumDriver<MobileElement> deviceConfig(URL url, Properties properties) {
        AppiumDriver<MobileElement> driver = null;
        PlatformName platformName = PlatformName.valueOf(properties.getProperty("platformName").toUpperCase().replaceAll("İ", "I"));

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, properties.getProperty("platformName"));
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, properties.getProperty("deviceName"));
        capabilities.setCapability(MobileCapabilityType.UDID, properties.getProperty("udid")); // adb devices
        capabilities.setCapability(MobileCapabilityType.NO_RESET, true);
        capabilities.setCapability(MobileCapabilityType.FULL_RESET, false);

        if (platformName.equals(PlatformName.ANDROID)) {
            capabilities.setCapability("automationName", "UiAutomator2");
            capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE,properties.getProperty("appPackage"));
            capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,properties.getProperty("appActivity"));
            driver = new AndroidDriver<>(url, capabilities);
        } else if (platformName.equals(PlatformName.IOS)) {
            capabilities.setCapability("automationName", "XCUITest");
            driver = new IOSDriver<>(url, capabilities);
        }

        return driver;
    }
}
