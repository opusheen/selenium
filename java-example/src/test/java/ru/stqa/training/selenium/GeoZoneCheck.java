package ru.stqa.training.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;


public class GeoZoneCheck extends TestBase {
    @Test
    public void testGeoZones() throws InterruptedException {
        loginToadmin();
        wd.get("http://localhost:8081/litecart/admin/?app=geo_zones&doc=geo_zones");

        Thread.sleep(5000);
        List<WebElement> countries = new ArrayList<>();
        List<String> countrynames = new ArrayList<>();
        countries = wd.findElements(By.className("row"));
        for (WebElement country : countries) {
            String countryname = country.findElement(By.cssSelector("td a")).getAttribute("firstChild.data");
            countrynames.add(countryname);


        }
        System.out.println(countrynames.toString());
    }
}

