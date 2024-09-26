import org.junit.jupiter.api.AfterEach;
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
    @AfterEach
    void teardown() {
        driver.close();
    }


    @Test
    public void testSuccessfulCheckout(){
        String firstItem="Sauce Labs Backpack";
        String secondItem="Sauce Labs Bike Light";

        String priceItemXpath="//div[contains(text(),'%s')]/ancestor::div[@class='inventory_item']/descendant::div[@class='inventory_item_price']";
        String addToCartItemXpath="//div[contains(text(),'%s')]/ancestor::div[@class='inventory_item']/descendant::button";

        //ARRANGE
        login("standard_user","secret_sauce");

        //ACT
        WebElement priceItem= driver.findElement(By.xpath(String.format(priceItemXpath,firstItem)));
        WebElement addToCartItem= driver.findElement(By.xpath(String.format(priceItemXpath,firstItem)));

        System.out.println(priceItem.getText());


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
}
