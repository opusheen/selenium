package ru.stqa.training.selenium;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class Cart extends TestBase{

    @Test
    public void testWorkWithCart() throws InterruptedException {
        wd.get("http://localhost:8081/litecart/");
        Thread.sleep(2000);
        int i;
        for (i= 1; i<4; i++) {
            wd.findElement(By.cssSelector("#box-most-popular li:nth-child(1)")).click();
            try {
                if (wd.findElement(By.name("options[Size]")).isDisplayed()) {
                    WebElement select = wd.findElement(By.name("options[Size]"));
                    new Select(select).selectByVisibleText("Small");
                }
            } catch (NoSuchElementException e) {
            }
            ;
            wd.findElement(By.name("add_cart_product")).click();
            WebDriverWait wait = new WebDriverWait(wd, 10);
            wait.until(textToBe(By.cssSelector("#cart > a.content > span.quantity"), String.valueOf(i)));
            wd.findElement(By.linkText("Home")).click();
        }
        wd.findElement(By.linkText("Checkout »")).click();
        //
        Thread.sleep(1000);
        int count = wd.findElements(By.cssSelector((".dataTable .item"))).size();
        try{
            WebElement inact = wd.findElement(By.cssSelector(".inact act"));
            inact.click();
            WebDriverWait wait = new WebDriverWait(wd, 5);
            wait.until(stalenessOf(inact));}
        catch (NoSuchElementException e){
            WebElement shortcut = wd.findElement(By.cssSelector(".shortcut a"));
            shortcut.click();
            WebDriverWait wait = new WebDriverWait(wd, 5);
            wait.until(stalenessOf(shortcut));
        }
        while (count > 1) {
            wd.findElement(By.name("remove_cart_item")).click();
            WebDriverWait wait = new WebDriverWait(wd, 5);
            wait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.cssSelector(".dataTable .item"), count ));
            count --;

        }
        WebDriverWait wait = new WebDriverWait(wd, 5);
        wait.until(textToBePresentInElementLocated(By.id("checkout-cart-wrapper"), "There are no items in your cart."));
        }
        }




