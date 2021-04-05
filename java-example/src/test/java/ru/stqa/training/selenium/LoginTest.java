package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LoginTest extends  TestBase{


        @Test
        public void myFirstTest() {
            wd.get("http://localhost:8081/litecart/admin/");
            wd.findElement(By.name("username")).click();
            wd.findElement(By.name("username")).clear();
            wd.findElement(By.name("username")).sendKeys("admin");
            wd.findElement(By.name("password")).click();
            wd.findElement(By.name("password")).clear();
            wd.findElement(By.name("password")).sendKeys("admin");
            wd.findElement(By.name("login")).click();
            wd.findElement((By.xpath("//img[@alt='My Store']"))).isDisplayed();
        }

    }
