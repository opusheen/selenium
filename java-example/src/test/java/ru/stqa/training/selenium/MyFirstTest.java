package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class MyFirstTest {
    private WebDriver wd;

    @Before
    public void start(){
        wd = new FirefoxDriver();
    }
    @Test
    public void myFirstTest(){
        wd.get("https://yandex.ru/");
        wd.findElement(By.linkText("Погода")).isDisplayed();
    }

    @After
    public void stop(){
        wd.quit();
        wd = null;
    }



}
