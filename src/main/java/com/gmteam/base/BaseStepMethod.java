package com.gmteam.base;

import com.gmteam.utilities.XpathType;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;

public class BaseStepMethod {
    private static final Logger baseStepMethodLogger = LogManager.getLogger(BaseStepMethod.class);

    private final FluentWait<WebDriver> wait;

    public BaseStepMethod() {
        this.wait = (new WebDriverWait(DriverThread.getDriver(), 30L))
                .pollingEvery(Duration.ofMillis(500L))
                .withTimeout(Duration.ofSeconds(30L))
                .ignoring(NotFoundException.class)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(NoSuchElementException.class);

        String platformName = DriverThread.getDriver().getPlatformName();
        if (platformName != null && platformName.equalsIgnoreCase("android")) {
            AndroidDriver<MobileElement> driver = (AndroidDriver<MobileElement>) DriverThread.getDriver();
            if (driver.isDeviceLocked())
                driver.unlockDevice();
        }
    }

    protected boolean isVisibleElement(By by, int timeOutInSeconds) {
        FluentWait<WebDriver> wait = (new WebDriverWait(DriverThread.getDriver(), timeOutInSeconds))
                .pollingEvery(Duration.ofMillis(500L))
                .withTimeout(Duration.ofSeconds(timeOutInSeconds))
                .ignoring(NotFoundException.class)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(NoSuchElementException.class);

        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(by)).getText();
            return true;
        } catch (Exception var5) {
            return false;
        }
    }

    protected MobileElement waitVisibleByLocator(By locator) {
        MobileElement element = null;

        try {
            element = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {
            baseStepMethodLogger.error("Web element is not visible!");
        }
        return element;
    }

    protected List<MobileElement> waitVisibleAllElementByLocator(By locator) {
        List<MobileElement> elementList = new ArrayList<>();

        try {
            List<WebElement> webElementList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
            for (WebElement webElement : webElementList)
                elementList.add((MobileElement) webElement);
        } catch (Exception e) {
            baseStepMethodLogger.error("Web element is not visible!");
        }
        return elementList;
    }

    protected MobileElement waitClickableByOfElement(MobileElement mobileElement) {
        MobileElement element = null;

        try {
            element = (MobileElement) wait.until(ExpectedConditions.elementToBeClickable(mobileElement));
        } catch (Exception e) {
            baseStepMethodLogger.error("Web element is not clickable!");
        }
        return element;
    }

    protected MobileElement waitPresenceOfElementByLocator(By locator) {
        MobileElement element = null;

        try {
            element = (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (Exception e) {
            baseStepMethodLogger.error("Web element not found in document!");
        }
        return element;
    }

    protected void clickElement(By locator) {
        MobileElement element = this.waitVisibleByLocator(locator);
        waitClickableByOfElement(element).click();
    }


    protected String getTextElement(By locator) {
        return waitPresenceOfElementByLocator(locator).getText();
    }

    protected String getAttributeElement(By locator, XpathType attributeType) {
        return waitPresenceOfElementByLocator(locator).getAttribute(attributeType.getValue());
    }

    protected boolean isDisableElement(By locator) {
        String isDisable = getAttributeElement(locator, XpathType.CHECKED);
        return isDisable.equals("false");
    }

    protected void setTextElement(By locator, String text) {
        MobileElement element = waitPresenceOfElementByLocator(locator);
        element.clear();
        element.sendKeys(text);
    }

    protected void backClick() {
        String platformName = DriverThread.getDriver().getPlatformName();
        if (platformName != null && platformName.equalsIgnoreCase("android")) {
            AndroidDriver<MobileElement> driver = (AndroidDriver<MobileElement>) DriverThread.getDriver();
            driver.pressKey(new KeyEvent(AndroidKey.BACK));
        } else {
            IOSDriver<MobileElement> driver = (IOSDriver<MobileElement>) DriverThread.getDriver();

        }
    }

    public void testSeekBarToEnd(By locator) {
        //Locating seekbar using resource id
        MobileElement seek_bar_element = waitVisibleByLocator(locator);
        // get start co-ordinate of seekbar
        int start = seek_bar_element.getLocation().getX();
        //Get width of seekbar
        int padding = 250;// Couldn't scroll to the last part because of padding.
        int end = seek_bar_element.getSize().getWidth() + padding;
        //get location of seekbar vertically
        int y = seek_bar_element.getLocation().getY();

        // Select till which position you want to scroll the seekbar
        TouchAction action = new TouchAction(DriverThread.getDriver());

        //Move it will the end
        action.longPress(PointOption.point(start, y)).moveTo(PointOption.point(end, y)).release().perform();
    }


    public String getToastMessage() {
        WebElement toastView = null;
        String platformName = DriverThread.getDriver().getPlatformName();
        if (platformName != null && platformName.equalsIgnoreCase("android")) {
            AndroidDriver<MobileElement> driver = (AndroidDriver<MobileElement>) DriverThread.getDriver();
            toastView = driver.findElement(By.xpath("//android.widget.Toast[1]"));
        } else {
            IOSDriver<MobileElement> driver = (IOSDriver<MobileElement>) DriverThread.getDriver();

        }

        return toastView.getAttribute("name");
    }

    public static String getProperty(String title) {
        String result = null;
        try {
            Properties properties = readPropertyFile();
            result = properties.getProperty(title);
        } catch (Throwable th) {
            baseStepMethodLogger.error("src/main/resources altinda config.properties adli dosyanin olması gerekiyor! Aranan değer bulunamadı: " + title);
            baseStepMethodLogger.error(th);
        }
        return result;
    }

    private static Properties readPropertyFile() throws Throwable {
        Properties properties = new Properties();
        File file = new File(System.getProperty("user.dir") + "/src/main/resources/config.properties");
        InputStream inputStream = new FileInputStream(file);
        properties.load(inputStream);
        return properties;
    }

    public void resultTakeScreenShot(String scenarioName) {
        takeScreenShot(scenarioName, false);
    }

    public void errorMessage(String scenarioName, String message) {
        takeScreenShot(scenarioName, true);
        Assert.fail(message);
    }

    public void takeScreenShot(String methodName, boolean isFail) {
        AtomicBoolean isFinish = new AtomicBoolean(false);
        new Thread(() -> {
            try {
                String fail = isFail ? "FailTest" : "TestResult";
                SimpleDateFormat formatterDate = new SimpleDateFormat("yyyy-MM-dd_HH-mm");
                Date date = new Date(System.currentTimeMillis());
                String time = formatterDate.format(date);

                String screenshotLoc = System.getProperty("user.dir") + "\\ScreenshotFile\\ApiDemos\\" + fail + "\\" +
                        time + "_" + methodName.replaceAll(" ", "") + ".png";

                File srcFiler = ((TakesScreenshot) DriverThread.getDriver()).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(srcFiler, new File(screenshotLoc));
                isFinish.set(true);
            } catch (IOException e) {
                baseStepMethodLogger.error("Error occurred in screenshot file operations!", e);
            }
        }).start();

        while (true) {
            if (isFinish.get())
                break;
        }
    }
/*
    public static void takeScreenShotEk(String methodName, boolean isFail) {

        try {
            String fail = isFail ? "FailTest" : "TestResult";
            SimpleDateFormat formatterDate = new SimpleDateFormat("yyyy-MM-dd_HH-mm");
            String time = formatterDate.format(DriverThread.getDate());

            String screenshotLoc = System.getProperty("user.dir") + "\\ScreenshotFile\\" + fail + "\\" +
                    time + "_" + methodName.replaceAll(" ", "") + ".png";

            File srcFiler = ((TakesScreenshot) DriverThread.getDriver()).getScreenshotAs(OutputType.FILE);

            FileUtils.copyFile(srcFiler, new File(screenshotLoc));
        } catch (IOException e) {
            baseStepMethodLogger.error("Error occurred in screenshot file operations!", e);
        }
    }
*/

}
