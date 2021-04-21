package ru.stqa.training.selenium;

import org.openqa.selenium.By;

public class GeoZoneCheck extends TestBase{
    public void testGeoZones(){
        loginToadmin();
        wd.findElement(By.name("Geo Zones")).click();
        wd.findElement(By.linkText("Canada")).getText();
    }
}
