package com.ptit.sqa.selelium;

import com.ptit.sqa.selelium.page.LoginPage;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class EntryPoint {
    @Test
    public void nhapDiem(){
        BrowserSetting  bs = new BrowserSetting();
        WebDriver driver = bs.BrowserSettings();
        LoginPage loginPage=new LoginPage(driver);
        loginPage.getUsername().sendKeys("giaovien");
        loginPage.getPassword().sendKeys("giaovien");
        loginPage.getSubmit().click();

    }
}
