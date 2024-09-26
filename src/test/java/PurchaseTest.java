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
        String checkoutCompleteMessage="Thank you for your order!";

        login("standard_user", "secret_sauce");
        List<Double> productPrices = addProductsToCart(itemNames);
        checkout("Fredik","Backman","05034");
        verifyTotal(productPrices);
        finishProcess(checkoutCompleteMessage);

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
        assertEquals("Products", pageTitle.getText(), "Login failed");
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

    public void checkout(String firstName, String lastName, String postalCode) {
        WebElement cartButton =
                driver.findElement(By.xpath("//div[@class='shopping_cart_container']/child::a[@data-test='shopping-cart-link']"));
        cartButton.click();

        WebElement checkoutButton = driver.findElement(By.id("checkout"));
        checkoutButton.click();

        WebElement firstNameField = driver.findElement(By.id("first-name"));
        WebElement lastNameField = driver.findElement(By.id("last-name"));
        WebElement postalCodeField = driver.findElement(By.id("postal-code"));

        firstNameField.sendKeys(firstName);
        lastNameField.sendKeys(lastName);
        postalCodeField.sendKeys(postalCode);

        WebElement continueButton = driver.findElement(By.id("continue"));
        continueButton.click();
    }

    public void verifyTotal(List<Double> productPrices) {
        WebElement itemTotalElement = driver.findElement(By.xpath("//div[@data-test='subtotal-label']"));
        WebElement taxElement = driver.findElement(By.className("summary_tax_label"));
        WebElement totalElement = driver.findElement(By.className("summary_total_label"));

        double itemTotal = Double.parseDouble(itemTotalElement.getText().replace("Item total: $", ""));
        double tax = Double.parseDouble(taxElement.getText().replace("Tax: $", ""));
        double total = Double.parseDouble(totalElement.getText().replace("Total: $", ""));

        double expectedItemTotal = productPrices.stream().mapToDouble(Double::doubleValue).sum();
        double expectedTotal = expectedItemTotal + tax;

        assertEquals(itemTotal, expectedItemTotal, "Total price is not correct");
        assertEquals(total, expectedTotal, "Total price + taxes is not correct");
    }

    public void finishProcess(String expectedMessage) {
        WebElement finishButton = driver.findElement(By.id("finish"));
        finishButton.click();

        WebElement checkoutCompleteMessage =
                driver.findElement(By.xpath("//button[@data-test='back-to-products']/preceding-sibling::h2"));
        assertEquals(checkoutCompleteMessage.getText(),expectedMessage,
                "Error: The final checkout message displayed was '" + checkoutCompleteMessage.getText()
                        + "', but the expected message was '" + expectedMessage + "'. Please verify the flow.");
    }
}