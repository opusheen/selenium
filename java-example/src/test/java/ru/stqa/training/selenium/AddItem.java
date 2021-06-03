package ru.stqa.training.selenium;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.openqa.selenium.Keys.ARROW_UP;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class AddItem  extends TestBase {

    @Test
    public void testAddItem() throws InterruptedException {
        loginToadmin();
        WebDriverWait wait = new WebDriverWait(wd, 5/*seconds*/);
        WebElement catalog = wait.until(presenceOfElementLocated(By.xpath("//span[contains(text(),'Catalog')]")));
        catalog.click();
        Thread.sleep(2000);
        wd.findElement(By.linkText("Add New Product")).click();
        Thread.sleep(1000);
        // radio button status
        wd.findElement(By.xpath("//input[@name='status']")).click();
        type(By.name("name[en]"), "Duck Donald");
        type(By.name("code"), "5327532");
        //product groups - select all
        wd.findElement(By.xpath("(//input[@name='product_groups[]'])[1]")).click();
        wd.findElement(By.xpath("(//input[@name='product_groups[]'])[2]")).click();
        wd.findElement(By.xpath("(//input[@name='product_groups[]'])[3]")).click();
        wd.findElement(By.name("new_images[]")).sendKeys(System.getProperty("user.dir") + "/src/test/resources/pic.jpg");
        type(By.name("quantity"), "1");
        wd.findElement(By.name("date_valid_from")).sendKeys("262021");
        wd.findElement(By.name("date_valid_to")).sendKeys("1262021");
        //Information
        wd.findElement(By.linkText("Information")).click();
        wd.findElement(By.name("manufacturer_id")).click();
        new Select(wd.findElement(By.name("manufacturer_id"))).selectByVisibleText("ACME Corp.");
        type(By.name("keywords"), "duck");
        type(By.name("short_description[en]"), "duck, yellow");
        type(By.name("short_description[en]"), "duck, yellow");
        type(By.className("trumbowyg-editor"), "This duck is yellow");
        type(By.name("head_title[en]"), "Duck");
        type(By.name("meta_description[en]"), "duck");
        //Prices
        wd.findElement(By.linkText("Prices")).click();
        wd.findElement(By.name("purchase_price")).sendKeys(ARROW_UP);
        type(By.name("prices[USD]"), "100");
        wd.findElement(By.name("save")).click();
        // check the presence of an item
        Thread.sleep(1000);
        wd.findElement(By.xpath("//a[contains(text(),'Duck Donald')]")).isDisplayed();
    }
}
