package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class MyFirstTest extends TestBase{


    @Test
    public void myFirstTest(){
        wd.get("https://yandex.ru/");
        wd.findElement(By.linkText("Погода")).isDisplayed();
    }




}
