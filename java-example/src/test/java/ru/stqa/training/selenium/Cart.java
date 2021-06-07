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

import static org.openqa.selenium.support.ui.ExpectedConditions.textToBe;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElementLocated;

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
        // корзина  на что обратить внимание?
        Thread.sleep(1000);
        int count = wd.findElements(By.cssSelector((".dataTable .item"))).size();
        try{
            wd.findElement(By.cssSelector(".inact act")).click();}
        catch (NoSuchElementException e){wd.findElement(By.cssSelector(".shortcut a")).click();}
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




