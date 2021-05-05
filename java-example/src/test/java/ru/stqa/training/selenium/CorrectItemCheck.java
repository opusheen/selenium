package ru.stqa.training.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.Color;

import java.util.Objects;

import static org.junit.Assert.*;

public class CorrectItemCheck extends TestBase {
    public class Duck {
        private String text;
        private int newPrice, oldPrice;



        public Duck duck(String text, int normalPrice, int discountPrice) {
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

        public boolean colorIsGrey(String rgba) {
            Color color = Color.fromString(rgba);
            int r = color.getColor().getRed();
            int g = color.getColor().getGreen();
            int b = color.getColor().getBlue();
            return r == g && r == b;
        }

        public boolean colorIsRed(String rgba) {
            Color color = Color.fromString(rgba);
            int g = color.getColor().getGreen();
            int b = color.getColor().getBlue();
            return g == 0 && b == 0;

        }

    }


    @Test
    public void testCorrectItemIsDisplayed() {
        wd.get("http://localhost:8081/litecart/en/");
        Duck duck = new Duck();
        duck.text = wd.findElement(By.cssSelector("#box-campaigns > div > ul > li > a.link > div.name")).getText();
        duck.newPrice = Integer.parseInt(wd.findElement(By.cssSelector("#box-campaigns > div > ul > li > a.link > div.price-wrapper > strong")).getText().substring(1));
        duck.oldPrice = Integer.parseInt(wd.findElement(By.cssSelector("#box-campaigns > div > ul > li > a.link > div.price-wrapper > s")).getText().substring(1));

        // price is grey and text is strikethrough
        String textStyle = wd.findElement(By.cssSelector("#box-campaigns > div > ul > li > a.link > div.price-wrapper > s")).getCssValue("text-decoration-line");
        assertEquals(textStyle, "line-through");
        duck.colorIsGrey(wd.findElement(By.cssSelector("#box-campaigns > div > ul > li > a.link > div.price-wrapper > s")).getCssValue("color"));

        //Size
        Double sizeNewPrice = Double.parseDouble(wd.findElement(By.cssSelector("#box-campaigns > div > ul > li > a.link > div.price-wrapper > strong")).getCssValue("font-size").substring(0, 2));
        Double sizeOldPrice = Double.parseDouble(wd.findElement(By.cssSelector("#box-campaigns > div > ul > li > a.link > div.price-wrapper > s")).getCssValue("font-size").substring(0, 2));
        assertTrue(sizeNewPrice > sizeOldPrice);

        // price is red and bold
        duck.colorIsRed(wd.findElement(By.cssSelector("#box-campaigns > div > ul > li > a.link > div.price-wrapper > strong")).getCssValue("color"));
        String fontStyle = wd.findElement(By.cssSelector("#box-campaigns > div > ul > li > a.link > div.price-wrapper > strong")).getCssValue("font-weight");
        assertEquals("700", fontStyle);

        // go to the item page
        wd.findElement(By.cssSelector("#box-campaigns > div > ul > li > a.link")).click();
        Duck duck1 = new Duck();
        duck1.text = wd.findElement(By.cssSelector("#box-product > div:nth-child(1) > h1")).getText();
        duck1.newPrice = Integer.parseInt(wd.findElement(By.cssSelector("#box-product > div.content > div.information > div.price-wrapper > strong")).getText().substring(1));
        duck1.oldPrice = Integer.parseInt(wd.findElement(By.cssSelector("#box-product > div.content > div.information > div.price-wrapper > s")).getText().substring(1));
        duck1.equals(duck);
        assertTrue(duck1.equals(duck));

        // price is grey and text is strikethrough
        String textStyleProductPage = wd.findElement(By.cssSelector("#box-product > div.content > div.information > div.price-wrapper > s")).getCssValue("text-decoration-line");
        assertEquals(textStyle, "line-through");
        duck.colorIsGrey(wd.findElement(By.cssSelector("#box-product > div.content > div.information > div.price-wrapper > s")).getCssValue("color"));

        //Size
        Double sizeNewPriceProductPage = Double.parseDouble(wd.findElement(By.cssSelector("#box-product > div.content > div.information > div.price-wrapper > strong")).getCssValue("font-size").substring(0, 2));
        Double sizeOldPriceProductPage = Double.parseDouble(wd.findElement(By.cssSelector("#box-product > div.content > div.information > div.price-wrapper > s")).getCssValue("font-size").substring(0, 2));
        assertTrue(sizeNewPriceProductPage > sizeOldPriceProductPage);

        // price is red and bold
        duck.colorIsRed(wd.findElement(By.cssSelector("#box-product > div.content > div.information > div.price-wrapper > strong")).getCssValue("color"));
        String fontStyleProductPage = wd.findElement(By.cssSelector("#box-product > div.content > div.information > div.price-wrapper > strong")).getCssValue("font-weight");
        assertEquals("700", fontStyleProductPage);
    }
}






