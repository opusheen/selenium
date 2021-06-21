package ru.stqa.training.selenium;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.support.ui.ExpectedCondition;

import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;

public class OpenLinks extends TestBase {
    @Before
    public void prepareToTes() {
        loginToadmin();
    }

    private ExpectedCondition<String> anyWindowOtherThen(Set<String> oldWindows) {
        return new ExpectedCondition<String>() {
            @NullableDecl
            @Override
            public String apply(@NullableDecl WebDriver input) {
                Set<String> handles = wd.getWindowHandles();
                handles.removeAll(oldWindows);
                return handles.size() > 0 ? handles.iterator().next() : null;
            }
        };
    }

    @Test
    public void testLinksAreOpened() {
        wd.get("http://localhost:8081/litecart/admin/?app=countries&doc=countries");
        String mainWindow = wd.getWindowHandle();
        Set<String> oldWindows = wd.getWindowHandles();
        wd.findElement(By.xpath("//a[text()=' Add New Country']")).click();
        int countLink = wd.findElements(By.className("fa-external-link")).size();
        for (int i = 0; i < countLink; i++) {
            wd.findElements(By.className("fa-external-link")).get(i).click();
            WebDriverWait wait = new WebDriverWait(wd, 5);
            String newWindow = wait.until(anyWindowOtherThen(oldWindows));
            wd.switchTo().window(newWindow);
            wd.close();
            wd.switchTo().window(mainWindow);
        }
    }
}




