import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PurchaseTest {

    WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterEach
    void teardown() {
        driver.close();
    }


    @Test
    public void testSuccessfulCheckout() {
        List<String> itemNames = List.of("Sauce Labs Backpack", "Sauce Labs Bike Light");

        login("standard_user", "secret_sauce");

        List<Double> productPrices = addProductsToCart(itemNames);

    }

    public void login(String username, String password) {
        driver.get("https://www.saucedemo.com/");
        WebElement inputUser = driver.findElement(By.id("user-name"));
        WebElement inputPassword = driver.findElement(By.id("password"));
        WebElement buttonLogin = driver.findElement(By.id("login-button"));
        inputUser.sendKeys(username);
        inputPassword.sendKeys(password);
        buttonLogin.click();

        WebElement pageTitle = driver.findElement(By.xpath("//span[@data-test='title']"));
        assertEquals("Products", pageTitle.getText(), "Login fallido o incorrecta redirección");
    }

    public List<Double> addProductsToCart(List<String> itemNames) {
        List<Double> productPrices = new ArrayList<>();
        String priceItemXpath = "//div[contains(text(),'%s')]/ancestor::div[@class='inventory_item']/descendant::div[@class='inventory_item_price']";
        String addToCartItemXpath = "//div[contains(text(),'%s')]/ancestor::div[@class='inventory_item']/descendant::button";

        for (String itemName : itemNames) {
            WebElement itemAddToCart = driver.findElement(By.xpath(String.format(addToCartItemXpath, itemName)));
            WebElement itemPriceElement = driver.findElement(By.xpath(String.format(priceItemXpath, itemName)));

            itemAddToCart.click();

            double itemPrice = Double.parseDouble(itemPriceElement.getText().replace("$", ""));
            productPrices.add(itemPrice);
        }

        return productPrices;
    }
}