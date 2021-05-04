package ru.stqa.training.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.Color;

import java.util.Objects;

import static org.junit.Assert.assertEquals;

public class CorrectItemCheck extends TestBase{
    public class Duck {
        private String text;
        private int newPrice, oldPrice;
        private String normalPriceTextStyle;
        private String normalPriceColor;


        public  Duck duck(String text, int normalPrice, int discountPrice) {
            this.text = text;
            this.newPrice = normalPrice;
            this.oldPrice = discountPrice;
            return this;
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Duck duck = (Duck) o;
            return newPrice == duck.newPrice && oldPrice == duck.oldPrice && Objects.equals(text, duck.text);
        }

        @Override
        public int hashCode() {
            return Objects.hash(text, newPrice, oldPrice);
        }
    }


    @Test
    public void testCorrectItemIsDisplayed() {
        wd.get("http://localhost:8081/litecart/en/");
        Duck duck = new Duck();
        duck.text = wd.findElement(By.cssSelector("#box-campaigns > div > ul > li > a.link > div.name")).getText();
        duck.newPrice = Integer.parseInt(wd.findElement(By.cssSelector("#box-campaigns > div > ul > li > a.link > div.price-wrapper > strong")).getText().substring(1));
        duck.oldPrice = Integer.parseInt(wd.findElement(By.cssSelector("#box-campaigns > div > ul > li > a.link > div.price-wrapper > s")).getText().substring(1));

        // price is grey and strikethrough
        String textStyle = wd.findElement(By.cssSelector("#box-campaigns > div > ul > li > a.link > div.price-wrapper > s")).getCssValue("text-decoration-line");
        assertEquals(textStyle, "line-through");
        String color = wd.findElement(By.cssSelector("#box-campaigns > div > ul > li > a.link > div.price-wrapper > s")).getCssValue("color");
        System.out.println(color);

        Double sizeNewPrice =  Double.parseDouble(wd.findElement(By.cssSelector("#box-campaigns > div > ul > li > a.link > div.price-wrapper > strong")).getCssValue("font-size"));
  //      Double sizeOldPrice = Double.parseDouble(wd.findElement(By.cssSelector("#box-campaigns > div > ul > li > a.link > div.price-wrapper > s")).getCssValue("font-size").substring(0,  sizeOldPrice.lenght() - 2));
 //       System.out.println(sizeNewPrice + "     ne" + sizeOldPrice);
        // price is red and bold
        // go to the item page
       wd.findElement(By.cssSelector("#box-campaigns > div > ul > li > a.link")).click();
       Duck duck1 = new Duck();
       duck1.text =  wd.findElement(By.cssSelector("#box-product > div:nth-child(1) > h1")).getText();
       duck1.newPrice = Integer.parseInt(wd.findElement(By.cssSelector("#box-product > div.content > div.information > div.price-wrapper > s")).getText().substring(1));
       duck.oldPrice = Integer.parseInt(wd.findElement(By.cssSelector("#box-product > div.content > div.information > div.price-wrapper > strong")).getText().substring(1));

        duck.equals(duck1);


   /*     public void determine_color(String rgba) {
            Color color = Color.fromString(rgba);
            int r = color.getColor().getRed();
            int g = color.getColor().getGreen();
            int b = color.getColor().getBlue();

        } */
    } }






