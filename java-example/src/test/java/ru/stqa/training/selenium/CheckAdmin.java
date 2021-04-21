package ru.stqa.training.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Instant;
import java.util.List;

import static org.openqa.selenium.By.cssSelector;

public class CheckAdmin extends TestBase{
    @Test
    public void testAdminAllPages() throws InterruptedException {
        loginToadmin();
        wd.findElement((By.xpath("//img[@alt='My Store']"))).isDisplayed();
        Thread.sleep(1000);
        List<WebElement> elements = wd.findElements(By.id("app-"));
        for (int i = 0; i < elements.size(); i ++) {
            Thread.sleep(500);
            wd.findElements(By.id("app-")).get(i).click();
            WebDriverWait wait = new WebDriverWait(wd, 10);
            wait.until(ExpectedConditions.visibilityOf(wd.findElement((By.cssSelector("h1")))));
            List <WebElement> subtitles = wd.findElements(By.cssSelector(".docs li"));
               if (subtitles.size() > 0)
            {
                for (int j = 0; j < subtitles.size(); j ++) {
                    Thread.sleep(2000);
                    wd.findElements(By.cssSelector(".docs li")).get(j).click();
                    wait.until(ExpectedConditions.visibilityOf(wd.findElement((By.cssSelector("h1")))));
                }
            }
        }} }




