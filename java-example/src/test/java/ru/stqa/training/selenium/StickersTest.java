package ru.stqa.training.selenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class StickersTest extends TestBase{
    @Test
    public void testStickers() throws InterruptedException {
        wd.get("http://localhost:8081/litecart/");
        Thread.sleep(2000);
        int count = 0;
        List<WebElement> elements = wd.findElements(By.className("image-wrapper"));
        for (WebElement element : elements ){
              List stickers = element.findElements(By.cssSelector("div [class *= 'sticker']"));
              assertEquals(stickers.size(), 1);
        }


    }
}
