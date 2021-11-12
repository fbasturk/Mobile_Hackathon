package com.gmteam.base;

import com.gmteam.listener.Listener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.URL;
import java.util.Properties;

@Listeners({Listener.class})
public class BaseTest {
    private static final Logger baseTestLogger = LogManager.getLogger(BaseTest.class);


    @Parameters({"platformName", "deviceName", "udid", "app"})
    @BeforeClass(alwaysRun = true)
    public void beforeClass(String platformName, String deviceName, String udid, String app) {
        baseTestLogger.info("BeforeClass başlatıldı. Cihaz Ayarları yapılıyor.");

        try {
            Properties properties = new Properties();

            properties.setProperty("platformName", platformName);
            properties.setProperty("deviceName", deviceName);
            properties.setProperty("udid", udid);
            properties.setProperty("app", app);

            URL url = new URL("http://127.0.0.1:4723/wd/hub");

            DriverThread.setDriver(PlatformType.deviceConfig(url, properties));
            baseTestLogger.info("Cihaz ayarlandı. Driver oluşturuldu.");
        } catch (Exception e) {
            baseTestLogger.error(e);
            Assert.fail("BeforeClass hata ile karşılaştı! ErrorMessage: " + e.getMessage());
        }
    }

    @BeforeMethod
    public void beforeMethod() {
        baseTestLogger.info("BeforeMethod başlatıldı. Uygulama başlatılıyor.");
        try {
            DriverThread.getDriver().launchApp();

        } catch (Exception e) {
            baseTestLogger.error(e);
            Assert.fail("BeforeMethod hata ile karşılaştı! ErrorMessage: " + e.getMessage());
        }
    }

    @AfterMethod
    public void afterMethod() {
        baseTestLogger.info("AfterMethod başlatıldı. Uygulama kapatılıyor.");
        try {
            DriverThread.getDriver().closeApp();
        } catch (Exception e) {
            baseTestLogger.error(e);
            Assert.fail("AfterMethod hata ile karşılaştı! ErrorMessage: " + e.getMessage());
        }
    }

    @AfterClass
    public void afterClass() {
        baseTestLogger.info("AfterClass başlatıldı. Driver kapatılıyor ve driver bilgisi temizleniyor.");
        try {
            DriverThread.getDriver().quit();
            DriverThread.setDriver(null);

        } catch (Exception e) {
            baseTestLogger.error(e);
            Assert.fail("AfterClass hata ile karşılaştı! ErrorMessage: " + e.getMessage());
        }
    }


}