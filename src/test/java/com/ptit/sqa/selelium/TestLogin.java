package com.ptit.sqa.selelium;

import com.ptit.sqa.selelium.page.LoginPage;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertEquals;

public class TestLogin {
    @Test
    public void testLoginSuccess(){
        BrowserSetting bs = new BrowserSetting();

        WebDriver driver = bs.BrowserSettings();
        driver.get("http://localhost:8888/login");
        LoginPage loginPage=new LoginPage(driver);
        loginPage.getUsername().sendKeys("giaovien");
        loginPage.getPassword().sendKeys("giaovien");
        loginPage.getSubmit().click();
        assertEquals("http://localhost:8888/home",driver.getCurrentUrl());

    }

    @Test
    public void testFalse(){
        BrowserSetting bs = new BrowserSetting();
        WebDriver driver = bs.BrowserSettings();
        driver.get("http://localhost:8888/login");
        LoginPage loginPage=new LoginPage(driver);
        loginPage.getUsername().sendKeys("giaovien1");
        loginPage.getPassword().sendKeys("giaovien");
        loginPage.getSubmit().click();
        assertEquals("http://localhost:8888/login",driver.getCurrentUrl());
    }


}
