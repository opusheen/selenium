package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestBase {
    public WebDriver wd;
    @Before
    public void start(){
        wd = new FirefoxDriver();
    }
    @After
    public void stop(){
        wd.quit();
        wd = null;
    }
}
