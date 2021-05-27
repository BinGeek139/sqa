package com.ptit.sqa.selelium;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;
public class BrowserSetting {
    public WebDriver BrowserSettings() {

        WebdriverSetting  wds = new WebdriverSetting();
        WebDriver driver = wds.driverSettings();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        return driver;
    }
}
