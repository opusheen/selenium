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
        List<WebElement> elements = wd.findElements(By.id("app-"));
        for (WebElement element: elements) {
            Thread.sleep(500);
            element.click();
            WebDriverWait wait = new WebDriverWait(wd, 10);

            wait.until(ExpectedConditions.visibilityOf(wd.findElement((By.cssSelector("h1")))));
            List <WebElement> subtitls = element.findElements(By.cssSelector("ul.docs > li"));
            if (subtitls.size() > 0)
            {
                for (WebElement subtitl : subtitls) {
                    element.click();
                    wait.until(ExpectedConditions.visibilityOf(wd.findElement((By.cssSelector("h1")))));

                }
            }
        }}


}

