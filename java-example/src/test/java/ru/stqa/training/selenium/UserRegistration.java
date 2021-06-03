package ru.stqa.training.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.util.Random;

public class UserRegistration extends TestBase {
    //public String email;
    Random rand =new Random ();
    int upperbound = 11111111;
    int int_random = rand.nextInt(upperbound);
    String email = int_random + "@mail.ru";
    String password= "password123";
    @Test
    public void testUserRegistration() throws InterruptedException {
        wd.get("http://localhost:8081/litecart/");
        Thread.sleep(2000);
        wd.findElement(By.linkText("New customers click here")).click();
        Thread.sleep(1000);
        type(By.name("firstname"), "Ivanka");
        type(By.name("lastname"),"Trump");
        type(By.name("address1"), "Norway street");
        type(By.name("postcode"), "12345");
        type(By.name("city"), "Naismith");
        wd.findElement(By.className("select2")).click();
        wd.findElement(By.className("select2-search__field")).sendKeys("United states" + Keys.ENTER);
        type(By.name("phone"), "+164873987324");
        type(By.name("email"), email);
        type(By.name("password"), password);
        type(By.name("confirmed_password"), password);
        wd.findElement(By.name("create_account")).click();
        Thread.sleep(1000);
        wd.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();
        Thread.sleep(1000);
        type(By.name("email"),email);
        type(By.name("password"), password);
        wd.findElement(By.name("login")).click();


    }
    }

