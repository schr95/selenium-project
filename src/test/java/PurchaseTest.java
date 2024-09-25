import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PurchaseTest {

    WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testSuccessfulCheckout(){
        //ARRANGE
        driver.get("https://www.saucedemo.com/");
        WebElement inputUser = driver.findElement(By.id("user-name"));
        WebElement inputPassword = driver.findElement(By.id("password"));
        WebElement buttonLogin = driver.findElement(By.id("login-button"));
        inputUser.sendKeys("standard_user");
        inputPassword.sendKeys("secret_sauce");
        buttonLogin.click();
        WebElement pageTitle = driver.findElement(By.xpath("//span[@data-test='title']"));
        assertEquals("Products", pageTitle.getText(),"Login fallido");

        //ACT
        String firstItem="Sauce Labs Backpack";
        String secondItem="Sauce Labs Bike Light";

        String priceItemXpath="//div[contains(text(),'%s')]/ancestor::div[@class='inventory_item']/descendant::div[@class='inventory_item_price']";
        String addToCartItemXpath="//div[contains(text(),'%s')]/ancestor::div[@class='inventory_item']/descendant::button";


        WebElement priceItem= driver.findElement(By.xpath(String.format(priceItemXpath,firstItem)));
        WebElement addToCartItem= driver.findElement(By.xpath(String.format(priceItemXpath,firstItem)));


    }
}
