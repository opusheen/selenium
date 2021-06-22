package ru.stqa.training.selenium;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class CheckLogMessage extends TestBase {

    @Test
    public void testConsole() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(wd, 5/*seconds*/);
        WebElement catalog = wait.until(presenceOfElementLocated(By.xpath("//span[contains(text(),'Catalog')]")));
        Thread.sleep(2000);
        catalog.click();
        while (wd.findElements(By.cssSelector("td i+a")).size() > 0) {
            wd.findElement(By.cssSelector("td i+a")).click();
        }
        int product_count = wd.findElements(By.cssSelector(".row td img + a")).size();
        for (int i = 0; i < product_count; i ++) {
            wait.until(presenceOfElementLocated(By.cssSelector(".row td img + a")));
            wd.findElements(By.cssSelector(".row td img + a")).get(i).click();
            System.out.println(i);
            wd.navigate().back();
            for (LogEntry l : wd.manage().logs().get("browser").getAll()) {
                System.out.println(l);
                assertTrue(l == null);
            }
        }
    }

}

