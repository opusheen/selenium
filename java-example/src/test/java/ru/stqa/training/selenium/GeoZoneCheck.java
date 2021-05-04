package ru.stqa.training.selenium;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.domstorage.model.Item;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class GeoZoneCheck extends TestBase {
    @Before
     public void prepareToTes(){
        loginToadmin();
    }

    @Test
    public void testCountries() throws InterruptedException {
        wd.get("http://localhost:8081/litecart/admin/?app=countries&doc=countries");
        Thread.sleep(1000);
        int counter = wd.findElements(By.cssSelector("td:nth-child(5)")).size();
        List countryNames = new ArrayList();
        List zoneNames = new ArrayList();
        for (int i = 0; i < counter;  i ++) {
            String countryName = wd.findElements(By.cssSelector("td:nth-child(5)")).get(i).getAttribute("outerText");
            countryNames.add(countryName);

            if (! wd.findElements(By.cssSelector("td:nth-child(6)")).get(i).getText().equals("0")) {

                wd.findElements(By.cssSelector((" td:nth-child(5) > a"))).get(i).click();
                Thread.sleep(500);
                int counter2 = wd.findElements(By.cssSelector("td:nth-child(3) > input[type=hidden]")).size();
                for (int j = 0; j<counter2; j++) {
                    String zoneName = wd.findElements(By.cssSelector("td:nth-child(3) > input[type=hidden]")).get(j).getAttribute("outerText");
                    zoneNames.add(zoneName);

                }
                wd.get("http://localhost:8081/litecart/admin/?app=countries&doc=countries");
            }
        }

        List sortedListCountries = new ArrayList<>();
        sortedListCountries = countryNames;
        Collections.sort(sortedListCountries);
        Assert.assertEquals(countryNames,sortedListCountries);
        List sortedListZones = new ArrayList();
        sortedListZones = zoneNames;
        Collections.sort(sortedListZones);
        Assert.assertEquals(zoneNames, sortedListZones);

    }



    @Test
    public void testGeoZones() throws InterruptedException {
        wd.get("http://localhost:8081/litecart/admin/?app=geo_zones&doc=geo_zones");
        Thread.sleep(1000);
        int counter =  wd.findElements(By.cssSelector("td:nth-child(3) > a")).size();
        List zoneNames = new ArrayList();
        for (int i = 0; i < counter;  i ++) {
            wd.findElements(By.cssSelector("td:nth-child(3) > a")).get(i).click();
            Thread.sleep(500);
            for (WebElement element : wd.findElements(By.cssSelector("td:nth-child(3) > select [selected= \"selected\"]"))) {
                zoneNames.add(element.getAttribute("outerText"));
            }
            List sortedList = new ArrayList<>();
            sortedList = zoneNames;
            Collections.sort(sortedList);
            Assert.assertEquals(zoneNames, sortedList);
            wd.get("http://localhost:8081/litecart/admin/?app=geo_zones&doc=geo_zones");
        }
    }
}

